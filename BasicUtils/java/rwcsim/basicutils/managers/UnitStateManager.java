package rwcsim.basicutils.managers;

import rwcsim.basicutils.ActionType;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Manager;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.states.*;
import rwcsim.basicutils.concepts.State;
import rwcsim.utils.statistics.DieStatisticCounter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitStateManager implements Manager {
    public boolean isAlive = true;
    public Formation formation;
    public Unit unit;

    DieStatisticCounter dsc = new DieStatisticCounter();
    public static UnavailableState UNAVAILABLE_STATE = new UnavailableState();
    public static List<State> UNAVAILABLE_STATE_LIST = new ArrayList<>();
    static { UNAVAILABLE_STATE_LIST.add(UNAVAILABLE_STATE); }
    int[] regen = new int[16];


    List<State> activeCircumstances = new ArrayList<>();


    public UnitStateManager() {
        activeCircumstances.add(new AliveState());
    }

    public boolean checkCircumstance(State circumstance) {
        return activeCircumstances.contains(circumstance);
    }

    public State getFirstState(State state) {
        if (activeCircumstances.contains(state)) {
            return activeCircumstances.get(activeCircumstances.indexOf(state));
        }
        return UNAVAILABLE_STATE;
    }
    public List<State> getAllStates(State circumstance) {
        if (checkCircumstance(circumstance)) {
            return activeCircumstances.stream().filter(c -> c.getState() == circumstance.getState()).collect(Collectors.toList());
        }
        return UNAVAILABLE_STATE_LIST;
    }

    public static List<ActionType> processPool(Map<Die,List<DieFace>> interpretPools) {
        List<ActionType> resultsPool = new ArrayList<>();

        for (Map.Entry<Die,List<DieFace>> entry:interpretPools.entrySet()) {
            for (DieFace df : entry.getValue()) {
                resultsPool.addAll(df.getActions());
            }
        }

        return resultsPool;
    }

    public void recordResults(Map<Die,List<DieFace>> dieRoll) {
        recordResults(dieRoll, false);
    }
    public void recordResults(Map<Die,List<DieFace>> dieRoll, boolean reroll) {
        List<ActionType> processedPool = processPool(dieRoll);

        int b = (int) processedPool.stream().filter(actionType -> actionType == ActionType.BLANK).count();
        int a = (int) processedPool.stream().filter(actionType -> actionType == ActionType.ACCURACY).count();
        int h = (int) processedPool.stream().filter(actionType -> actionType == ActionType.HIT).count();
        int m = (int) processedPool.stream().filter(actionType -> actionType == ActionType.MORALE).count();
        int ms = (int) processedPool.stream().filter(actionType -> actionType == ActionType.MORTAL_STRIKE).count();
        int s = (int) processedPool.stream().filter(actionType -> actionType == ActionType.SURGE).count();

        dsc.incrementBy(DieStatisticCounter.BLANK, b);
        dsc.incrementBy(DieStatisticCounter.ACCURACY, a);
        dsc.incrementBy(DieStatisticCounter.HIT, h);
        dsc.incrementBy(DieStatisticCounter.MORALE, m);
        dsc.incrementBy(DieStatisticCounter.MORTALSTRIKE, ms);
        dsc.incrementBy(DieStatisticCounter.SURGE, s);

        if (reroll) {
            dsc.incrementBy(DieStatisticCounter.REROLL_BLANK, b);
            dsc.incrementBy(DieStatisticCounter.REROLL_ACCURACY, a);
            dsc.incrementBy(DieStatisticCounter.REROLL_HIT, h);
            dsc.incrementBy(DieStatisticCounter.REROLL_MORALE, m);
            dsc.incrementBy(DieStatisticCounter.REROLL_MORTALSTRIKE, ms);
            dsc.incrementBy(DieStatisticCounter.REROLL_SURGE, s);
        }
    }

    public void recordRegeneration(int round, int maxregens, int remainingregens) {
        regen[round]=maxregens;
        regen[round+8]=remainingregens;
    }

    public void setAliveState(boolean isAlive) { this.isAlive = isAlive; }
    public void setUnit(Unit unit) { this.unit = unit; }
    public void setFormation(Formation formation) { this.formation = formation; }
    public DieStatisticCounter getDsc() { return dsc; }
    public int[] getRegen() { return regen; }
}

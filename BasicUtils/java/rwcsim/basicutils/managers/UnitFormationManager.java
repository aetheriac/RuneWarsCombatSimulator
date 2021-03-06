package rwcsim.basicutils.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Abilities;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.abilities.Regenerate;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.stages.EndOfActivation;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.basicutils.dice.DiePool;

import java.util.*;

public class UnitFormationManager implements Manager {
    private static final Logger log = LogManager.getLogger(UnitFormationManager.class);

    public DeployableUnit getDeployableUnit() {
        return deployableUnit;
    }

    /**
     * 1: [0]  1x2:  [0]    1x3:  [0]
     *               [1]          [1]
     *                            [3]
     *
     * 2x1: [0] [1]   2x2: [0] [1]   2x3: [0] [1]
     *                     [2] [3]        [2] [3]
     *                                    [4] [5]
     *
     * 3x1: [0] [1] [2]  3x2: [0] [1] [2]  3x3: [0] [1] [2]
     *                        [3] [4] [5]       [3] [4] [5]
     *                                          [6] [7] [8]
     *
     * 4x1: [0] [1] [2] [3]   4x2: [0] [1] [2] [3]   4x3: [0] [1] [2]  [3]
     *                             [4] [5] [6] [7]        [4] [5] [6]  [7]
     *                                                    [8] [9] [10] [11]
     *
     * Additional trays are added as needed following the same patterns
     *
     *
     */

    DeployableUnit deployableUnit;
    Unit unit;
    Formation formation;
    List<Tray> trayLayout;
    int totalTrayCount;

    public UnitFormationManager(DeployableUnit deployableUnit) {
        this.deployableUnit = deployableUnit;
        this.deployableUnit.setUnitFormationManager(this);
        this.unit = deployableUnit.getUnit();
        this.formation = deployableUnit.getFormation();
        totalTrayCount = this.formation.getTrayCount();
        trayLayout = new LinkedList<>();
        initializeTrays();
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void initializeTrays() {
        for (int i = 0; i<totalTrayCount; i++) {
            trayLayout.add(i, unit.getTray());
        }
    }

    public int getThreat() {
        log.debug("getThreat()");
        int threat = formation.getThreat();
        Ability brutal;
        if (unit.getAbilities().containsKey(Abilities.BRUTAL)) {
            brutal = unit.getAbilities().get(Abilities.BRUTAL);
            log.debug("Brutal ["+threat+"]("+brutal.getValue()+")");
            threat += brutal.getValue();
            log.debug("Brutal ["+threat+"]");
        }
        return threat;
    }

    public int getCurrentRanks() {
        log.debug(this+" TotalTrayCount: "+ totalTrayCount);
        log.debug(this+" CurrentTrayCount: "+ trayLayout.size());
        log.debug(this+" Formation Threat: "+ formation.getThreat());
        log.debug(this+" HasEmptySlots: "+ hasEmptySlots());

        int currentRanks = trayLayout.size() / formation.getThreat();
        if (currentRanks>0 && hasEmptySlots()) { currentRanks--; }

        if (this.deployableUnit.getUnit().getAbilities().containsKey(Abilities.PRECISE)) {
            log.debug("Ranks: "+ currentRanks);
            currentRanks += this.deployableUnit.getUnit().getAbilities().get(Abilities.PRECISE).getValue();
            log.debug("Precise: "+ currentRanks);
        }

        return currentRanks;
    }

    public boolean hasPartialRank() {
        return (trayLayout.size() - formation.getThreat()) % formation.getThreat() > 0;
    }

    public DiePool getDiePool(AttackType type) {
        return this.deployableUnit.getDiePool(type);
    }

    public boolean canReroll() {
        boolean result = false;
        result = this.deployableUnit.canReroll();

        if (this.deployableUnit.getUnit().getAbilities().containsKey(Abilities.PRECISE)) {
            result = true;
        }

        return result;
    }

    public int getRerollDieCount() {
        return this.deployableUnit.getRerollDieCount();
    }

    public void setFigureUpgrade(int tray, int trayLocation, FigureUpgrade figureUpgrade) {
        // TODO:  Get Trady support in tray
//        if (figureUpgrade.replaceTray()) {
//            trayLayout.add(tray, figureUpgrade.getTray());
//        }
        trayLayout.get(tray).setFigureUpgrade(trayLocation, figureUpgrade);
    }


    public void applyHits(int count) {
        int curTray = trayLayout.size()-1;
        int remainingHits = count;
        Tray tmp;

        while (remainingHits>0) {
            log.debug("Remaining Hits: " + remainingHits);
            if (trayLayout.size()>0) {
                tmp = trayLayout.get(curTray);
                log.debug("Tray: "+ tmp);
                remainingHits = tmp.applyDamage(remainingHits);
                if (tmp.isEmpty()) {
                    trayLayout.remove(curTray);
                    curTray--;
                }
            } else { return; }
         }
    }

    public void applyMortalStrikes(int count) {
        int curTray = trayLayout.size()-1;
        int remainingStrikes = count;
        Tray tmp;

        while (remainingStrikes>0) {
            if (trayLayout.size()>0) {
                tmp = trayLayout.get(curTray);
                remainingStrikes = tmp.applyMortalStrikes(remainingStrikes);
                if (tmp.isEmpty()) {
                    trayLayout.remove(curTray);
                    curTray--;
                }
            } else { return; }
        }
    }

    public boolean isAlive() {
        if (trayLayout.size()==0) return false;

        for (Tray t : trayLayout) {
            if (!t.isEmpty()) {
                return true;
            }
        }
        return true;
    }

    public boolean hasEmptySlots() {
        for (Tray t : trayLayout) {
            if (t.hasEmptySlots()) {
                return true;
            }
        }
        return false;
    }


    public int figuresRemaining() {
        int result = 0;
        for (Tray t:trayLayout) {
            result += t.getFigureCount();
        }
        return result;
    }

    public void recordDieRoll(Map<Die,List<DieFace>> dieRoll) {
        deployableUnit.unitStateManager.recordResults(dieRoll);
    }
    public void recordDieRoll(Map<Die,List<DieFace>> dieRoll, boolean reroll) {
        deployableUnit.unitStateManager.recordResults(dieRoll, reroll);
    }


    public UnitStateManager setUSMForStats(UnitStateManager usm) {
        usm.setAliveState(isAlive());
        usm.setFormation(formation);
        usm.setUnit(unit);
        return usm;
    }


    public void endActivationPhase(int round) {
        if (isAlive() && hasEmptySlots() && RuleSetManager.isEnabled("REGENERATION")) {
            Integer key = new Regenerate(0).getKey();
            if (unit.getAbilities().keySet().contains(key)) {
                int regenCount = unit.getAbilities().get(key).getValue();
                int startingRegens = regenCount;
                if (regenCount>0) {
                    for (Tray t : trayLayout) {
                        regenCount = t.refillEmptySlots(unit, regenCount);
                    }
                }
                deployableUnit.unitStateManager.recordRegeneration(round, startingRegens, regenCount);
            }
        }
    }


    public void reconfigure() {
//        for (Ability<?> ability : unit.getAbilities().values()) {
//            if (ability.getStage() instanceof EndOfActivation) {
//
//            }
//        }
    }
}

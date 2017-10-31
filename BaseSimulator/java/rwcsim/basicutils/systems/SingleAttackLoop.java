package rwcsim.basicutils.systems;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.ruleset.AutomaticallyRerollBlanks;
import rwcsim.basicutils.ruleset.Regeneration;
import rwcsim.basicutils.ruleset.Reroll;
import rwcsim.basicutils.ruleset.RerollFromDialog;
import rwcsim.factions.daqan.OathswornCavalry;
import rwcsim.factions.neutral.upgrades.equipment.TemperedSteel;
import rwcsim.factions.waiqar.Reanimates;

public class SingleAttackLoop {
    SimSetup ss;
    SimulationAttackLoop simAttackLoop;

    public void setup() {
        ss = new SimSetup(new OathswornCavalry(), Formation.TWO_BY_ONE, new Reanimates(), Formation.THREE_BY_TWO);
        simAttackLoop = new SimulationAttackLoop(ss, null);
    }

    public void startSingleSimulation() throws Exception {
        ss.getFirst().getUnit().populateFormations();
        ss.getFirst().getUnit().populateSlots(ss.getFirst().getFormation());

        ss.getSecond().getUnit().populateFormations();
        ss.getSecond().getUnit().populateSlots(ss.getSecond().getFormation());
        simAttackLoop.call();
    }

    public static void init() {
        RuleSetManager.addRule(new Reroll(), false);
        RuleSetManager.addRule(new AutomaticallyRerollBlanks(), false);
        RuleSetManager.addRule(new RerollFromDialog(), true);
        RuleSetManager.addRule(new Regeneration(), true);
        RuleSetManager.addRule(new TemperedSteel(), true);
    }

    public static void main(String[] args) throws Exception {
        init();
        SingleAttackLoop sal = new SingleAttackLoop();
        sal.setup();
        sal.startSingleSimulation();
    }
}

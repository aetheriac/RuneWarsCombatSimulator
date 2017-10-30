package rwcsim.basicutils.systems;

import rwcsim.basicutils.Formation;
import rwcsim.factions.daqan.OathswornCavalry;
import rwcsim.factions.waiqar.Reanimates;

public class SingleAttackLoop {
    SimSetup ss;
    SimulationAttackLoop simAttackLoop;

    public void setup() {
        ss = new SimSetup(new OathswornCavalry(), Formation.TWO_BY_ONE, new Reanimates(), Formation.THREE_BY_TWO);
//        simAttackLoop = new SimulationAttackLoop(ss);
    }

    public void startSingleSimulation() throws Exception {
        simAttackLoop.call();
    }

    public static void main(String[] args) throws Exception {
        SingleAttackLoop sal = new SingleAttackLoop();
        sal.setup();
        sal.startSingleSimulation();
    }
}

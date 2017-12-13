package rwcsim.basicutils.systems;

import rwcsim.basicutils.dials.CommandCache;

public class FullActivationLoop {

//    activationPhase(); [
//    count initiative {
//        revealCommandDial();
//        performAction();
//        performBonusAction();
//    }
//            ]


    public void setup(SimSetup ss) {

    }



    public static void main(String[] args) {
        FullActivationLoop fal = new FullActivationLoop();

        SimSetup ss = SimSetup.getSetup();
        ss.setCommandCaches(SimulationCommandCaches.getChargeAttack(ss.getFirst()), SimulationCommandCaches.getSetAttack(ss.getSecond()));
        fal.setup(ss);
    }
}

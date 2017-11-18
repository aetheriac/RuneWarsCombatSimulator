package rwcsim.basicutils.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.ruleset.RerollFromDialog;
import rwcsim.basicutils.unit.DeployableUnit;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

public class SimulationState {
    private static Logger log = LogManager.getLogger(SimulationState.class);

    // 0 based counter
    public static final int MAX_ROUNDS = 8;
    private int round = 0;

    private SimSetup simSetup;
    private SimulationPlayer leftPlayer;
    private SimulationPlayer rightPlayer;

    // Left player
    private DeployableUnit leftUnit;
    private UnitFormationManager leftFormation;
    private RerollBehavior leftBehavior;

    // Right player
    private DeployableUnit rightUnit;
    private UnitFormationManager rightFormation;
    private RerollBehavior rightBehavior;

    public SimulationState(){}
    public SimulationState(SimSetup ss) {
        this.simSetup = ss;
        initialize();
    }

    public void initialize() {
        leftUnit = simSetup.getFirst();
        leftFormation = new UnitFormationManager(leftUnit);
        leftBehavior = new RerollFromDialog();

        rightUnit = simSetup.getSecond();
        rightFormation = new UnitFormationManager(rightUnit);
        rightBehavior = new RerollFromDialog();
    }

    public SimulationPlayer getFirstPlayer() {
        return(round%2==0)?leftPlayer:rightPlayer;
    }

    public SimulationPlayer getSecondPlayer() {
        return (round%2==1)?rightPlayer:leftPlayer;
    }
}

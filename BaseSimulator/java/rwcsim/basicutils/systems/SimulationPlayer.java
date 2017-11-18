package rwcsim.basicutils.systems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

public class SimulationPlayer {
    private static Logger log = LogManager.getLogger(SimulationPlayer.class);

    private Unit unit;
    private Formation formation;
    private RerollBehavior behavior;


    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public RerollBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(RerollBehavior behavior) {
        this.behavior = behavior;
    }
}

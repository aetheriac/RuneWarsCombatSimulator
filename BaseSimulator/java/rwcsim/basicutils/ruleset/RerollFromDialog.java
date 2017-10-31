package rwcsim.basicutils.ruleset;

import rwcsim.basicutils.concepts.Rule;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.interactions.ai.behaviors.RerollBehavior;
import rwcsim.interactions.behaviors.Behavior;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RerollFromDialog implements Rule<RerollFromDialog>, RerollBehavior {
    public static final String name = "REROLL_FROM_DIALOG";

    public String name() {return name;}

    public boolean isEnabled() {
        return RuleSetManager.isEnabled(name);
    }

    public RerollFromDialog getRule() {
        return new RerollFromDialog();
    }

    @Override
    public RerollFromDialog getBehavior() {
        return this;
    }

    Map<Integer, HashSet<DieFace>> rerollFaces = new HashMap<>();

    public void setFaces(int key, HashSet<DieFace> faces) {
        rerollFaces.put(key, faces);
    }

    public Map<Integer, HashSet<DieFace>> getRerollFaces() {
        return rerollFaces;
    }

}

package rwcsim.basicutils.ruleset;

import rwcsim.basicutils.concepts.Rule;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.interactions.ai.behaviors.RerollBehavior;
import rwcsim.interactions.behaviors.Behavior;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RerollFromDialog implements Rule<RerollFromDialog>, RerollBehavior {
    public static final String name = "REROLL_FROM_DIALOG";

    private static final Map<Integer, HashSet<DieFace>> defaultRerollDieFaces;

    private static final DieFace[] defaultRedDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE
    };

    private static final DieFace[] defaultBlueDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_ACCURACY, DieFace.HIT_SURGE,
    };

    private static final DieFace[] defaultWhiteDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE, DieFace.HIT_ACCURACY, DieFace.MORTAL_STRIKE,
    };


    static {
        defaultRerollDieFaces = new HashMap<>();
        defaultRerollDieFaces.put(DiePool.RED_DIE, new HashSet<>(Arrays.asList(defaultRedDieSelection)));
        defaultRerollDieFaces.put(DiePool.BLUE_DIE, new HashSet<>(Arrays.asList(defaultBlueDieSelection)));
        defaultRerollDieFaces.put(DiePool.WHITE_DIE, new HashSet<>(Arrays.asList(defaultWhiteDieSelection)));
    }

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

    public Map<Integer, HashSet<DieFace>> getDefaultRerollFaces() { return defaultRerollDieFaces; }

}

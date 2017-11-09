package rwcsim.basicutils.ruleset;

import rwcsim.basicutils.concepts.Rule;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.interactions.ai.behaviors.RerollBehavior;
import rwcsim.interactions.behaviors.Behavior;

import java.util.*;

public class RerollFromDialog implements Rule<RerollFromDialog>, RerollBehavior {
    public static final String name = "REROLL_FROM_DIALOG";

    private  Map<Integer, HashSet<DieFace>> rerollDieFaces;

    public RerollFromDialog() {
        rerollFaces = new HashMap<>();
        rerollDieFaces.put(DiePool.RED_DIE, new HashSet<>(Arrays.asList(defaultRedDieSelection)));
        rerollDieFaces.put(DiePool.BLUE_DIE, new HashSet<>(Arrays.asList(defaultBlueDieSelection)));
        rerollDieFaces.put(DiePool.WHITE_DIE, new HashSet<>(Arrays.asList(defaultWhiteDieSelection)));
    }

    private final DieFace[] defaultRedDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE
    };

    private final DieFace[] defaultBlueDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_ACCURACY, DieFace.HIT_SURGE,
    };

    private final DieFace[] defaultWhiteDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE, DieFace.HIT_ACCURACY, DieFace.MORTAL_STRIKE,
    };
//
//
//    static {
//        defaultRerollDieFaces = new HashMap<>();
//        defaultRerollDieFaces.put(DiePool.RED_DIE, new HashSet<>(Arrays.asList(defaultRedDieSelection)));
//        defaultRerollDieFaces.put(DiePool.BLUE_DIE, new HashSet<>(Arrays.asList(defaultBlueDieSelection)));
//        defaultRerollDieFaces.put(DiePool.WHITE_DIE, new HashSet<>(Arrays.asList(defaultWhiteDieSelection)));
//    }

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

    @Override
    public void update(Map<Integer, HashSet<DieFace>> dieFaces) {
        rerollDieFaces.putAll(dieFaces);
    }

    @Override
    public boolean shouldReroll(int die, DieFace faceToReroll) {
        if (rerollFaces.get(die).contains(faceToReroll)) {
            return false;
        }
        return true;
    }
}

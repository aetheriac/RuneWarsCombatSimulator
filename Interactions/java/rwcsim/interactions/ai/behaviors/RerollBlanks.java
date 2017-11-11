package rwcsim.interactions.ai.behaviors;

import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.dice.DiePool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RerollBlanks implements RerollBehavior {
    @Override
    public RerollBehavior getBehavior() {
        return this;
    }

    @Override
    public Map<Integer, HashSet<DieFace>> getRerollFaces() {
        Map<Integer, HashSet<DieFace>> blanks = new HashMap<>();

        blanks.put(DiePool.RED_DIE, new HashSet<>());
        blanks.get(DiePool.RED_DIE).add(DieFace.BLANK);

        blanks.put(DiePool.BLUE_DIE, new HashSet<>());
        blanks.get(DiePool.BLUE_DIE).add(DieFace.BLANK);

        blanks.put(DiePool.WHITE_DIE, new HashSet<>());
        blanks.get(DiePool.WHITE_DIE).add(DieFace.BLANK);

        return blanks;
    }

//    @Override
//    public Map<Integer, HashSet<DieFace>> getDefaultRerollFaces() {
//        return null;
//    }

    @Override
    public void update(Map<Integer, HashSet<DieFace>> dieFaces) {

    }

    @Override
    public boolean shouldReroll(int die, DieFace faceToCheck) {
        return false;
    }
}

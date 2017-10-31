package rwcsim.interactions.ai.behaviors;

import rwcsim.basicutils.dice.DieFace;
import rwcsim.interactions.behaviors.Behavior;

import java.util.HashSet;
import java.util.Map;

public interface RerollBehavior extends Behavior<RerollBehavior> {
    Map<Integer, HashSet<DieFace>> getRerollFaces();
    Map<Integer, HashSet<DieFace>> getDefaultRerollFaces();
}

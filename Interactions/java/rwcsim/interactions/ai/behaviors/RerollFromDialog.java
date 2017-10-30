package rwcsim.interactions.ai.behaviors;

import rwcsim.basicutils.dice.DieFace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RerollFromDialog implements RerollBehavior {
    Map<Integer, List<DieFace>> rerollFaces = new HashMap<>();

    public void setFaces(int key, List<DieFace> faces) {
        rerollFaces.put(key, faces);
    }

    public Map<Integer, List<DieFace>> getRerollFaces() {
        return rerollFaces;
    }

    @Override
    public RerollBehavior getBehavior() {
        return this;
    }
}

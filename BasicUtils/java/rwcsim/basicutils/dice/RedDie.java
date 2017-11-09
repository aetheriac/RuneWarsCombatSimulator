package rwcsim.basicutils.dice;

/**
 * Created by dsayles on 5/14/15.
 */
public class RedDie implements Die {
    private static RedDie _instance = new RedDie();
    private int dieType;
    private DieFace[] faces;

    private RedDie() {
        setFaces();
        dieType = DiePool.RED_DIE;
    }

    public static RedDie get() {
        return _instance;
    }

    @Override
    public DieFace[] getFaces() {
        return _instance.faces;
    }

    @Override
    public void setFaces() {
        faces = new DieFace[8];
        faces[0] = DieFace.BLANK;
        faces[1] = DieFace.BLANK;
        faces[2] = DieFace.HIT;
        faces[3] = DieFace.HIT;
        faces[4] = DieFace.MORALE;
        faces[5] = DieFace.HIT_HIT;
        faces[6] = DieFace.HIT_MORALE;
        faces[7] = DieFace.HIT_SURGE;
    }

    @Override
    public DieFace result(int value) {
        return _instance.faces[value];
    }

    @Override
    public int getDieType() {
        return _instance.dieType;
    }
}

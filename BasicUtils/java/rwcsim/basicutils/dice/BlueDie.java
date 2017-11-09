package rwcsim.basicutils.dice;

/**
 * Created by dsayles on 5/14/15.
 */
public class BlueDie implements Die {
    private static BlueDie _instance = new BlueDie();
    private int dieType;
    private DieFace[] faces;

    private BlueDie() {
        setFaces();
        dieType = DiePool.BLUE_DIE;
    }

    public static BlueDie get() {
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
        faces[1] = DieFace.HIT;
        faces[2] = DieFace.HIT;
        faces[3] = DieFace.SURGE;
        faces[4] = DieFace.ACCURACY;
        faces[5] = DieFace.HIT_SURGE;
        faces[6] = DieFace.HIT_ACCURACY;
        faces[7] = DieFace.SURGE_SURGE;
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

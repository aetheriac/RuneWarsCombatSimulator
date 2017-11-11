package rwcsim.basicutils.dice;

/**
 * Created by dsayles on 5/14/15.
 */
public class WhiteDie  implements Die {
    private static WhiteDie _instance = new WhiteDie();
    private int dieType;
    private DieFace[] faces;

    private WhiteDie() {
        setFaces();
        dieType = DiePool.WHITE_DIE;
    }

    public static WhiteDie get() {
        return _instance;
    }

    @Override
    public DieFace[] getFaces() {
        return _instance.faces;
    }
    @Override
    public void setFaces() {
        faces = new DieFace[12];
        faces[0] = DieFace.BLANK;
        faces[1] = DieFace.MORTAL_STRIKE;
        faces[2] = DieFace.HIT;
        faces[3] = DieFace.HIT;
        faces[4] = DieFace.HIT;
        faces[5] = DieFace.HIT_HIT;
        faces[6] = DieFace.HIT_SURGE;
        faces[7] = DieFace.HIT_SURGE;
        faces[8] = DieFace.HIT_ACCURACY;
        faces[9] = DieFace.HIT_MORALE;
        faces[10] = DieFace.SURGE_SURGE;
        faces[11] = DieFace.SURGE_MORALE;
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

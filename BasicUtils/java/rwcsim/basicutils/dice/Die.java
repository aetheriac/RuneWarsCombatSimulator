package rwcsim.basicutils.dice;

/**
 * Created by dsayles on 5/14/15.
 */
public interface Die {
    void setFaces();
    DieFace[] getFaces();
    DieFace result(int value);
    int getDieType();
}

package rwcsim.basicutils.dice;

public class ModifierDie implements Die {
    @Override
    public void setFaces() {

    }

    @Override
    public DieFace[] getFaces() {
        return new DieFace[0];
    }

    @Override
    public DieFace result(int value) {
        return null;
    }

    @Override
    public int getDieType() {
        return 0;
    }
}

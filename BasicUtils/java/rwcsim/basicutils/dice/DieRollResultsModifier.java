package rwcsim.basicutils.dice;

public class DieRollResultsModifier {
    DieFace face;
    int count;

    public DieRollResultsModifier(DieFace face, int count) {
        this.face = face;
        this.count = count;
    }

    public DieFace getFace() {
        return face;
    }

    public void setFace(DieFace face) {
        this.face = face;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}

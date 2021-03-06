package rwcsim.basicutils;

/**
 * Created by dsayles on 8/17/17.
 */
public enum Formation {
    ONE(1,1),
    ONE_BY_TWO(1,2),
    ONE_BY_THREE(1,3),
    TWO_BY_ONE(2,1),
    TWO_BY_TWO(2,2),
    TWO_BY_TWO_2(2,2),
    TWO_BY_THREE(2,3),
    THREE_BY_ONE(3,1),
    THREE_BY_TWO(3,2),
    THREE_BY_THREE(3,3),
    FOUR_BY_ONE(4,1),
    FOUR_BY_TWO(4,2),
    FOUR_BY_THREE(4,3);

    private int columns;
    private int rows;
    private int trays;

    Formation(int c, int r) {
        columns = c;
        rows = r;
        trays = c * r;
    }

    public int getTrayCount() {
        return trays;
    }

    public int getThreat() {
        return columns;
    }

    public int getRanks() {
        return rows-1;
    }
}

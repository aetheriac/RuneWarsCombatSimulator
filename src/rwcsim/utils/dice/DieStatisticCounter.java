package rwcsim.utils.dice;

import java.util.Arrays;

public class DieStatisticCounter {
    public static final int STAT_SIZE = 100;

    // Die Icons
    public static final int ACCURACY = 0;
    public static final int HIT = 1;
    public static final int MORALE = 2;
    public static final int MORTALSTRIKE = 3;
    public static final int SURGE = 4;
    public static final int BLANK = 5;
    public static final int REROLLS = 6;

    // Die Faces
    public static final int FACE_BLANK = 10;
    public static final int FACE_HIT = 11;
    public static final int FACE_ACCURACY = 12;
    public static final int FACE_MORALE = 13;
    public static final int FACE_SURGE = 14;
    public static final int FACE_HIT_HIT = 15;
    public static final int FACE_HIT_SURGE = 16;
    public static final int FACE_HIT_ACCURACY = 17;
    public static final int FACE_HIT_MORALE = 18;
    public static final int FACE_SURGE_SURGE = 19;
    public static final int FACE_SURGE_MORALE = 20;
    public static final int FACE_MORTAL_STRIKE = 21;

    int[] statistics = new int[STAT_SIZE];

    public void increment(int statid) {
        statistics[statid]++;
    }

    public void incrementBy(int statid, int count) {
        statistics[statid] += count;
    }

    public void showStats() {
        System.out.println("A,H,M,MS,S,B,RR: "+ Arrays.toString(statistics));
    }

    public int[] getStats() {
        return statistics;
    }

}

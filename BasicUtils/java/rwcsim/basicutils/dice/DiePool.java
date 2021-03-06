package rwcsim.basicutils.dice;

import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.runes.RuneManager;

/**
 * Created by dsayles on 6/10/15.
 */
public class DiePool {
    public static final int RED_DIE = 0;
    public static final int BLUE_DIE = 1;
    public static final int WHITE_DIE = 2;


    public static final int DIE_TYPE_COUNT = 3;
    private int[] attackDice = new int[DIE_TYPE_COUNT];
    private RuneFaces[] attackRune = new RuneFaces[DIE_TYPE_COUNT];

    public DiePool(int r, int b, int w) {
        this(r, null, b, null, w, null);
    }
    public DiePool(int r, RuneFaces rr, int b, RuneFaces rb, int w, RuneFaces rw) {
        this.attackDice[RED_DIE] = r;
        this.attackDice[BLUE_DIE] = b;
        this.attackDice[WHITE_DIE] = w;

        this.attackRune[RED_DIE] = rr;
        this.attackRune[BLUE_DIE] = rb;
        this.attackRune[WHITE_DIE] = rw;
    }

    public int size() {
        int[] pool = getAttackPool();
        int result = pool[RED_DIE];
        result += pool[BLUE_DIE];
        result += pool[WHITE_DIE];
        return result;
    }

    public int[] getAttackPool() { return getAttackPool(0, 0,0);}
    public int[] getAttackPool(int[] dice) {
        return getAttackPool(dice[RED_DIE], dice[BLUE_DIE], dice[WHITE_DIE]);
    }
    public int[] getAttackPool(int red, int blue, int white) {
        int[] ret = new int[DIE_TYPE_COUNT];
        System.arraycopy(attackDice, 0, ret, 0, attackDice.length);

        for (int i = 0; i < DIE_TYPE_COUNT; i++) {
            if (attackRune[i] != null) {
                ret[i] = RuneManager.getInstance().currentRuneCount(attackRune[i]);
            }
        }

        ret[RED_DIE] += red;
        ret[BLUE_DIE]+= blue;
        ret[WHITE_DIE] += white;
        return ret;
    }

    public void setAttackPool(int[] pool) {
        if (pool.length == DIE_TYPE_COUNT) {
            System.arraycopy(pool, 0, attackDice, 0, pool.length);
        }
    }

    public void setAttackPool(int red, int blue, int white) {
        attackDice[RED_DIE] = red;
        attackDice[BLUE_DIE] = blue;
        attackDice[WHITE_DIE] = white;
    }

//    public int[] getTemporaryPool(int red, int blue, int white) {
//        int[] tPool = new int[DIE_TYPE_COUNT];
//        System.arraycopy(attackDice, 0, tPool, 0, attackDice.length);
//        tPool[RED_DIE] += red;
//        tPool[BLUE_DIE] += blue;
//        tPool[WHITE_DIE] += white;
//        return tPool;
//    }

    public void setAttackPool(RuneFaces rred, RuneFaces rblue, RuneFaces rwhite) {
        attackRune[RED_DIE] = rred;
        attackRune[BLUE_DIE] = rblue;
        attackRune[WHITE_DIE] = rwhite;
    }

    public void setAttackPool(int red, RuneFaces rred, int blue, RuneFaces rblue, int white, RuneFaces rwhite) {
        setAttackPool(red, blue, white);
        setAttackPool(rred, rblue, rwhite);
    }
}

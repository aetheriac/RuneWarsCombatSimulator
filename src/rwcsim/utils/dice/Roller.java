package rwcsim.utils.dice;

import rwcsim.base.ActionType;
import rwcsim.base.RuleSetManager;
import rwcsim.test.CoreUnit;
import rwcsim.utils.interaction.InteractionManager;

import java.util.*;

/**
 * Created by dsayles on 5/14/15.
 */
public class Roller {
    static RedDie redDie = new RedDie();
    static WhiteDie whiteDie = new WhiteDie();
    static BlueDie blueDie = new BlueDie();

    public static DieFace rollRedDie() {
        return roll(redDie);
    }
    public static DieFace rollBlueDie() {
        return roll(blueDie);
    }
    public static DieFace rollWhiteDie() {
        return roll(whiteDie);
    }

    public static DieFace roll(Die die) {
        return die.result(RuleSetManager.getBoxedInt(die.faces.length));
    }

    public static Map<Die,List<DieFace>> rollPool(DiePool pool) {
        return rollPool(pool.getAttackPool());
    }
    public static Map<Die,List<DieFace>> rollPool(int[] pool) {
//        List<ActionType> actionPool = new ArrayList<>();
        Map<Die, List<DieFace>> resultsPools = new HashMap<>();

        resultsPools.put(redDie, rollDice(redDie,pool[DiePool.RED_DIE]));
        resultsPools.put(blueDie, rollDice(blueDie,pool[DiePool.BLUE_DIE]));
        resultsPools.put(whiteDie, rollDice(whiteDie,pool[DiePool.WHITE_DIE]));

//        actionPool.addAll(processPool(resultsPools, unit, roller));

        return resultsPools;
    }

//    public static List<ActionType> processPool(Map<Die,List<DieFace>> interpretPools, CoreUnit unit, InteractionManager roller) {
//        List<ActionType> resultsPool = new ArrayList<>();
//
//        for (Map.Entry<Die,List<DieFace>> entry:interpretPools.entrySet()) {
//            for (DieFace df : entry.getValue()) {
//                resultsPool.addAll(df.getActions());
//            }
//        }
//
//        return resultsPool;
//    }





    public static List<DieFace> rollDice(Die die, int count) {
        List<DieFace> resultPool = new ArrayList<>();
        for (int i=0; i<count; i++) {
            resultPool.add(roll(die));
        }
        return resultPool;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.println("RedD: "+rollRedDie());
        }
        System.out.println("");
        for (int i = 0; i < 1; i++) {
            System.out.println("WhtD: "+rollWhiteDie());
        }
        System.out.println("");
        for (int i = 0; i < 1; i++) {
            System.out.println("BluD: "+rollBlueDie());
        }
    }
}

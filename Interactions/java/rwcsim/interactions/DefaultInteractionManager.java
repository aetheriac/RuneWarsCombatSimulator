package rwcsim.interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.dice.*;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.concepts.Upgrade;
import rwcsim.factions.neutral.upgrades.equipment.TemperedSteel;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultInteractionManager extends BaseInteractionManager {
    private static final Logger log = LogManager.getLogger(DefaultInteractionManager.class);
    private static DefaultInteractionManager dim = new DefaultInteractionManager();

    public static InteractionManager instance() {
        return dim;
    }

    @Override
    public int[] defineFlankingPool() {
        return new int[]{0,1,0};
    }

    // TODO:  Hook up die reroll dialog to this method


    // Base reroll method
    @Override
    public Map<Die, List<DieFace>> rerollFromDialog(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker,
                                                    Map<Die, List<DieFace>> results, AttackType type, RerollBehavior rerollBehavior) {
        // reroll all dice up to the number of ranks
        Map<Die,List<DieFace>> rerolledResults = fullRankRerollFromDialog(results, rerollBehavior);
        if (rerollPartialRank) {
            rerolledResults = partialRankRerollFromDialog(rerolledResults, rerollBehavior);
        }
        return rerolledResults;
    }


    public Map<Die,List<DieFace>> partialRankRerollFromDialog(Map<Die, List<DieFace>> results, RerollBehavior rerollBehavior) {
        log.debug("Partial Rank Reroll");
        /// build pool of dice to reroll
        Map<Die, List<DieFace>> working = results.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(), e -> new ArrayList<>(e.getValue())));
        int[] rerollPool = new int[]{0,0,0};

        boolean oneDieSelected = false;

        for (Map.Entry<Die, List<DieFace>> entry : working.entrySet()) {
            for (DieFace face : entry.getValue()) {
                if (!oneDieSelected && rerollBehavior.shouldReroll(entry.getKey().getDieType(), face)) {
                    // add a die to the reroll diepool
                    rerollPool[entry.getKey().getDieType()]++;
                    // remove original face
                    results.get(entry.getKey()).remove(face);
                    oneDieSelected = true;
                }
            }
        }

        // reroll pool
        results = rerollPool(results, rerollPool);
        return results;
    }


    public Map<Die, List<DieFace>> fullRankRerollFromDialog(Map<Die, List<DieFace>> results, RerollBehavior rerollBehavior) {
        log.debug("Full Rank Dice Reroll");
        return rerollPotentiallyAllDice(results, rerollBehavior);
    }

    public Map<Die, List<DieFace>> rerollPotentiallyAllDice(Map<Die, List<DieFace>> results, RerollBehavior rerollBehavior) {
        if (null == rerollBehavior) {
            return results;
        }
        Map<Die, List<DieFace>> working = results.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(), e -> new ArrayList<>(e.getValue())));

        int[] rerollPool = new int[]{0,0,0};

        // setup the pool to be rerolled, and remove the DieFaces that are being rerolled
        // RED, BLUE, WHITE
        // for each die, check the faces that are in there
        //    if face is in the saved list for the die being checked, skip and move on to next face
        for (Map.Entry<Die, List<DieFace>> entry : working.entrySet()) {
            for (DieFace face : entry.getValue()) {
                if (rerollBehavior.shouldReroll(entry.getKey().getDieType(), face)) {
                    // add a die to the reroll diepool
                    rerollPool[entry.getKey().getDieType()]++;
                    // remove original face
                    results.get(entry.getKey()).remove(face);
                }
            }
        }

        // reroll the pool
        results = rerollPool(results, rerollPool);
        return results;
    }



    public Map<Die, List<DieFace>> rerollPool(Map<Die,List<DieFace>> results, int[] pool) {
        Map<Die, List<DieFace>> rerolledResults = Roller.rollPool(pool);
        for (Map.Entry<Die, List<DieFace>> entry : rerolledResults.entrySet()) {
            if (results.containsKey(entry.getKey())) {
                if (results.get(entry.getKey()) != null) {
                    results.get(entry.getKey()).addAll(entry.getValue());
                } else {
                    results.put(entry.getKey(), entry.getValue());
                }
            } else {
                results.put(entry.getKey(),entry.getValue());
            }
        }
        return results;
    }



//    @Override
//    public Map<Die, List<DieFace>> rerollFromDialog(int rerollRankCount, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type, RerollBehavior rerollBehavior) {
//        if (null != rerollBehavior) {
//            Map<Die, List<DieFace>> working = results.entrySet().stream()
//                    .collect(Collectors.toMap(
//                            e -> e.getKey(), e -> new ArrayList<>(e.getValue())));
//
//            int rerollDieCount = rerollRankCount;
//            int[] rerollPool = new int[]{0,0,0};
//
//            // Reroll things
//            // RED, BLUE, WHITE
//            // for each die, check the faces that are in there
//            //    if face is in the saved list for the die being checked, skip and move on to next face
//            for (Map.Entry<Die, List<DieFace>> entry : working.entrySet()) {
//                for (DieFace face : entry.getValue()) {
//                    if (rerollDieCount > 0 &&
////                        rerollBehavior.getRerollFaces().size() > 0 &&
////                        !rerollBehavior.getRerollFaces().get(entry.getKey().getDieType()).contains(face)
//                            rerollBehavior.shouldReroll(entry.getKey().getDieType(), face)) {
//                        // add a die to the reroll diepool
//                        rerollPool[entry.getKey().getDieType()]++;
//                        // remove original face
//                        results.get(entry.getKey()).remove(face);
//                        rerollDieCount--;
//                    }
//                }
//            }
//
//            if (rerollDieCount - 1 > 0) {
//                results = rerollFromDialog(rerollDieCount - 1, false, attacker, results, type, rerollBehavior);
//            }
//
//
//            Map<Die, List<DieFace>> rerolledResults = Roller.rollPool(rerollPool);
//            for (Map.Entry<Die, List<DieFace>> entry : rerolledResults.entrySet()) {
//                if (results.containsKey(entry.getKey())) {
//                    if (results.get(entry.getKey()) != null) {
//                        results.get(entry.getKey()).addAll(entry.getValue());
//                    } else {
//                        results.put(entry.getKey(), entry.getValue());
//                    }
//                } else {
//                    results.put(entry.getKey(),entry.getValue());
//                }
//            }
//        }
//        return results;
//    }





        @Override
    public Map<Die, List<DieFace>> reroll(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type) {
        Map<Die, List<DieFace>> working;
//        results.forEach(working::putIfAbsent);
//        working.putAll(results);
        working = results.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(), e -> new ArrayList<DieFace>(e.getValue())));

        /* default reroll of blanks if possible */
        int rerollDieCount =  rerollRankCount;

        int[] rerollPool = new int[working.keySet().size()];

        if (containsNotHits(working)) {
            for (Map.Entry<Die, List<DieFace>> e : working.entrySet()) {
                long r = e.getValue().stream().filter(
                        f -> !f.hasHit()
                ).count();
                if (r > 0) rerollPool[e.getKey().getDieType()] = (int) r;
                for ( DieFace df : e.getValue()) {
                    if (!df.dealsDamage()) {
                        log.debug("Removing " + df.name() + " face from " + e.getKey().toString());
                        results.get(e.getKey()).remove(df);
                    }
                }
            }

            Map<Die, List<DieFace>> rerollResult = Roller.rollPool(rerollPool);
            for (Map.Entry<Die, List<DieFace>> e : working.entrySet()) {
                if (rerollResult.containsKey(e.getKey())) {
                    e.getValue().addAll(rerollResult.get(e.getKey()));
                }
            }


            if (rerollDieCount - 1 > 0 && containsBlanks(results)) {
                results = reroll(rerollDieCount-1, false, attacker, results, type);
            }
        }

        if (containsBlanks(results) && rerollPartialRank) {
            results = rerollBlanks(results, rerollPartialRank);
        }

        return results;
    }



//    @Override
//    public void modifyAttackRollResults(UnitFormationManager attacker, Map<Die, List<DieFace>> rerollResults) {
//        for (Upgrade upgrade : attacker.getUnit().getUpgrades(UpgradeSlot.Equipment)) {
//            if (upgrade.getUpgradeName().compareTo("TemperedSteel")==0
//                    && (containsFace(DieFace.SURGE, rerollResults))) {
////                        || containsFace(DieFace.HIT_SURGE, rerollResults)
////                        || containsFace(DieFace.SURGE, rerollResults))) {
//                TemperedSteel ts = (TemperedSteel)upgrade;
//                if (!ts.isExhausted()) {
//                    ts.exhaust();
//                    removeNFaces(rerollResults);
//                }
//            }
//        }
//    }



    @Override
    public void applyMortalStrikes(UnitFormationManager unit, int count) {
        log.debug("applyMortalStrikes: "+ unit.toString() + ":"+ count);

        // apply mortal strikes to defender
        unit.applyMortalStrikes(count);


    }


    @Override
    public void assignAccuracies(UnitFormationManager unit, int count) {
        log.debug("assignAccuracies: "+ unit.toString() + ":"+ count);
    }


    @Override
    public void applyHits(UnitFormationManager unit, int count) {
        log.debug("applyHits: "+ unit.toString() + ":"+ count);

        // apply hits here
        unit.applyHits(count);
    }

    @Override
    public void applyMorale(UnitFormationManager unit, int count) {
        log.debug("applyMorale: "+ unit.toString() + ":"+ count);
    }

    @Override
    public void applySurges(UnitFormationManager attackingUnit, UnitFormationManager defendingUnit, int surgeCount, List<DieRollResultsModifier> modifiers, int round) {
        if (attackingUnit.getUnit().hasUpgrades()) {
            for (Upgrade upgrade : attackingUnit.getUnit().getUpgrades(UpgradeSlot.Equipment)) {
                if (RuleSetManager.isEnabled("Tempered Steel") && upgrade.getUpgradeName().compareTo("Tempered Steel") == 0) {
                    TemperedSteel ts = (TemperedSteel) upgrade;
                    if (!ts.isExhausted()) {
                        log.debug("Exhausting Tempered Steel");
                        ts.exhaust();
                        log.debug("Adding HIT");
                        modifiers.add(new DieRollResultsModifier(DieFace.HIT, 1));
                        log.debug("Removing SURGE");
                        modifiers.add(new DieRollResultsModifier(DieFace.SURGE, -1));
                        attackingUnit.getDeployableUnit().getUnitStateManager().recordTemperedSteel(round);
                    }
                }
            }
        }
    }


    public boolean containsSingles(Map<Die, List<DieFace>> results) {
        for (Map.Entry<Die,List<DieFace>> e : results.entrySet()) {
            for ( DieFace f : e.getValue()) {
                if (f.getSymbolCount()==1) { return true; }
            }
        }
        return false;
    }



    public Map<Die,List<DieFace>> rerollBlanks(Map<Die,List<DieFace>> results, boolean rerollPartialRank) {
        // reroll white
        if (results.get(Roller.whiteDie).contains(DieFace.BLANK) && rerollPartialRank) {
            results.get(Roller.whiteDie).remove(DieFace.BLANK);
            Map<Die,List<DieFace>> r = Roller.rollPool(new int[]{0,0,1});
            results.get(Roller.whiteDie).addAll(r.get(Roller.whiteDie));
            rerollPartialRank = false;
        }

        // reroll red
        if (results.get(Roller.redDie).contains(DieFace.BLANK) && rerollPartialRank) {
            results.get(Roller.redDie).remove(DieFace.BLANK);
            Map<Die,List<DieFace>> r = Roller.rollPool(new int[]{1,0,0});
            results.get(Roller.redDie).addAll(r.get(Roller.redDie));
            rerollPartialRank = false;
        }

        // reroll blue
        if (results.get(Roller.blueDie).contains(DieFace.BLANK) && rerollPartialRank) {
            results.get(Roller.blueDie).remove(DieFace.BLANK);
            Map<Die,List<DieFace>> r = Roller.rollPool(new int[]{0,1,0});
            results.get(Roller.blueDie).addAll(r.get(Roller.redDie));
            rerollPartialRank = false;
        }
        return results;
    }

    public boolean containsBlanks(Map<Die, List<DieFace>> results) {
        for (Map.Entry<Die,List<DieFace>> e : results.entrySet()) {
            if (e.getValue().contains(DieFace.BLANK)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNotHits(Map<Die, List<DieFace>> results) {
        for (Map.Entry<Die,List<DieFace>> e : results.entrySet()) {
            for (DieFace df : e.getValue()) {
                if (!df.hasHit() && !df.hasMortalStrike()) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean containsFace(DieFace dieFace, Map<Die, List<DieFace>> results) {
        for (Map.Entry<Die,List<DieFace>> e : results.entrySet()) {
            if (e.getValue().contains(dieFace)) {
                return true;
            }
        }
        return false;
    }


    public Map<Die, List<DieFace>> removeNFaces(DieFace dieFace, int count, Map<Die, List<DieFace>> results) {
        for (int i = 0; i<count; i++) {
            for (Map.Entry<Die,List<DieFace>> entry : results.entrySet()) {
                if (entry.getValue().contains(dieFace)) {
                    entry.getValue().remove(dieFace);
                    continue;
                }
            }
        }
        return results;
    }

}

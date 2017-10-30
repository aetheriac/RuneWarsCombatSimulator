package rwcsim.interactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.dice.DieRollResultsModifier;
import rwcsim.basicutils.managers.RuleSetManager;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.dice.Roller;
import rwcsim.basicutils.runes.RuneManager;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.upgrades.Upgrade;
import rwcsim.factions.neutral.upgrades.equipment.TemperedSteel;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultInteractionManager extends BaseInteractionManager {
    private static final Logger logger = LogManager.getLogger(DefaultInteractionManager.class);
    private static DefaultInteractionManager dim = new DefaultInteractionManager();

    public static InteractionManager instance() {
        return dim;
    }

    @Override
    public int[] defineFlankingPool() {
        return new int[]{0,1,0};
    }

    // TODO:  Hook up die reroll dialog to this method

    @Override
    public Map<Die, List<DieFace>> rerollFromDialog(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type, RerollBehavior rerollBehavior) {
        Map<Die, List<DieFace>> working;
//        results.forEach(working::putIfAbsent);
//        working.putAll(results);
        working = results.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(), e -> new ArrayList<DieFace>(e.getValue())));

        /* default reroll of blanks if possible */
        int rerollDieCount = rerollRankCount;

        int[] rerollPool = new int[working.keySet().size()];




        return results;
    }





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
                        logger.debug("Removing " + df.name() + " face from " + e.getKey().toString());
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
        logger.debug("applyMortalStrikes: "+ unit.toString() + ":"+ count);

        // apply mortal strikes to defender
        unit.applyMortalStrikes(count);


    }


    @Override
    public void assignAccuracies(UnitFormationManager unit, int count) {
        logger.debug("assignAccuracies: "+ unit.toString() + ":"+ count);
    }


    @Override
    public void applyHits(UnitFormationManager unit, int count) {
        logger.debug("applyHits: "+ unit.toString() + ":"+ count);

        // apply hits here
        unit.applyHits(count);
    }

    @Override
    public void applyMorale(UnitFormationManager unit, int count) {
        logger.debug("applyMorale: "+ unit.toString() + ":"+ count);
    }

    @Override
    public void applySurges(UnitFormationManager attackingUnit, UnitFormationManager defendingUnit, int surgeCount, List<DieRollResultsModifier> modifiers) {
        if (attackingUnit.getUnit().hasUpgrades())
        for (Upgrade upgrade : attackingUnit.getUnit().getUpgrades(UpgradeSlot.Equipment)) {
            if (RuleSetManager.isEnabled("TemperedSteel") && upgrade.getUpgradeName().compareTo("TemperedSteel") == 0) {
                TemperedSteel ts = (TemperedSteel)upgrade;
                if (!ts.isExhausted()) {
                    ts.exhaust();
                    modifiers.add(new DieRollResultsModifier(DieFace.HIT, 1));
                    modifiers.add(new DieRollResultsModifier(DieFace.SURGE, -1));
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

package rwcsim.interactions.ui;

import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.dice.DieRollResultsModifier;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.interactions.BaseInteractionManager;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

import java.util.List;
import java.util.Map;

public class UIInteractionManager extends BaseInteractionManager {
    @Override
    public int[] defineFlankingPool() {
        return new int[0];
    }

    @Override
    public Map<Die, List<DieFace>> reroll(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type) {
        return null;
    }

    @Override
    public Map<Die, List<DieFace>> rerollFromDialog(int rerollRankCount, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type, RerollBehavior rerollBehavior) {
        return null;
    }

    @Override
    public Map<Die, List<DieFace>> rerollFromDialog(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type, RerollBehavior rerollBehavior) {
        return null;
    }

    @Override
    public void applyMortalStrikes(UnitFormationManager unit, int count) {

    }

    @Override
    public void assignAccuracies(UnitFormationManager unit, int count) {

    }

    @Override
    public void applyHits(UnitFormationManager defendingUnit, int hitCount) {

    }

    @Override
    public void applyMorale(UnitFormationManager defendingUnit, int moraleCount) {

    }

    @Override
    public void applySurges(UnitFormationManager attackingUnit, UnitFormationManager defendingUnit, int surgeCount, List<DieRollResultsModifier> modifiers) {

    }
}

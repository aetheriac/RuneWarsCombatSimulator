package rwcsim.interactions.ai;

import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.dice.DieRollResultsModifier;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.interactions.BaseInteractionManager;

import java.util.List;
import java.util.Map;

public class AIInteractionManager extends BaseInteractionManager {
    @Override
    public int[] defineFlankingPool() {
        return new int[0];
    }

    @Override
    public Map<Die, List<DieFace>> reroll(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type) {
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

package rwcsim.interactions;

import rwcsim.basicutils.AttackType;
import rwcsim.basicutils.dice.DieRollResultsModifier;
import rwcsim.basicutils.managers.UnitFormationManager;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.runes.RuneManager;

import java.util.List;
import java.util.Map;

public interface InteractionManager {
    int[] defineFlankingPool();

    Map<Die,List<DieFace>> reroll(int rerollRankCount, boolean rerollPartialRank, UnitFormationManager attacker, Map<Die, List<DieFace>> results, AttackType type);

//    void modifyAttackRollResults(UnitFormationManager attacker, Map<Die, List<DieFace>> rerollResults);
    void applyMortalStrikes(UnitFormationManager unit, int count);
    void assignAccuracies(UnitFormationManager unit, int count);
    void applyHits(UnitFormationManager defendingUnit, int hitCount);
    void applyMorale(UnitFormationManager defendingUnit, int moraleCount);

    void applySurges(UnitFormationManager attackingUnit, UnitFormationManager defendingUnit, int surgeCount, List<DieRollResultsModifier> modifiers);
}


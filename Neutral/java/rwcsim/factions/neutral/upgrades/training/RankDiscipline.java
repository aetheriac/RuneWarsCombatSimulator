package rwcsim.factions.neutral.upgrades.training;

import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.upgrades.Training;

public class RankDiscipline implements Cost, Training, AddAbility<Precise> {
    @Override
    public int price() {
        return 4;
    }

    @Override
    public Precise getAbility() {
        return new Precise(1);
    }

    @Override
    public String getUpgradeName() {
        return "Rank Discipline";
    }
}

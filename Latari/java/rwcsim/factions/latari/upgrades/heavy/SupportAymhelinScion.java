package rwcsim.factions.latari.upgrades.heavy;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.stages.AfterActivation;
import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.abilities.Overgrow;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.latari.upgrades.Latari;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.runes.RuneManager;

public class SupportAymhelinScion extends BaseFigure implements FigureUpgrade, Cost, Latari, Heavy, SiegeUpgrade, OnSkill, AddAbility<Overgrow> {
    public SupportAymhelinScion() {
    }

    @Override
    public int price() {
        return 4;
    }

    @Override
    public Overgrow getAbility() {
        return new Overgrow(1);
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 1 + RuneManager.getInstance().currentRuneCount(RuneFaces.STABLE);
    }

    @Override
    public int getHealth() {
        return 3;
    }
}

package rwcsim.factions.daqan.upgrades.heavy;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.stages.WhileAttacking;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.daqan.upgrades.Daqan;
import rwcsim.basicutils.runes.RuneFaces;

public class FrontlineRuneGolem extends BaseFigure implements FigureUpgrade, Daqan, Heavy, SiegeUpgrade, AddAbility<Brutal>, Cost {
    public FrontlineRuneGolem() {
    }

    @Override
    public int price() {
        return 7;
    }

    @Override
    public Brutal getAbility() {
        return new Brutal(RuneFaces.STABLE);
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 4;
    }

    @Override
    public int getHealth() {
        return 2;
    }
}

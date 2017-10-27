package rwcsim.factions.waiqar.upgrades.champion;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.AvailableUpgrades;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class FallenHero extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Champion, InfantryUpgrade {
    public FallenHero() {
    }

    @Override
    public int price() {
        return 4;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 3;
    }

    @Override
    public int getHealth() {
        return 2;
    }
}

package rwcsim.factions.waiqar.upgrades.heavy;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class FrontLineCarrionLancer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Heavy, SiegeUpgrade {
    public FrontLineCarrionLancer() {}



    @Override
    public int price() {
        return 5;
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
        return 3;
    }
}

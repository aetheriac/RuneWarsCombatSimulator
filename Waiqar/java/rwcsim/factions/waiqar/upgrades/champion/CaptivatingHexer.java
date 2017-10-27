package rwcsim.factions.waiqar.upgrades.champion;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.Engaged;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class CaptivatingHexer extends BaseFigure implements Figure, FigureUpgrade, Cost, Waiqar, Champion, InfantryUpgrade {
    public CaptivatingHexer(){}

    @Override
    public int price() {
        return 5;
    }

    public int getArmor() {
        return 2;
    }

    @Override
    public Figure getFigure() {
        return null;
    }
}

package rwcsim.factions.waiqar.upgrades.heraldry;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.modifiers.InitiativeAdjustment;
import rwcsim.basicutils.upgrades.Heraldry;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class RavenStandardBearer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Heraldry, InfantryUpgrade, InitiativeAdjustment {
    public RavenStandardBearer() {

    }

    @Override
    public FaceColor affectedColor() {
        return FaceColor.RED;
    }

    @Override
    public int changeValue() {
        return -1;
    }

    @Override
    public int price() {
        return 3;
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
    public String getUpgradeName() {
        return "Raven Standard Bearer";
    }
}

package rwcsim.factions.daqan.upgrades.heraldry;

import rwcsim.basicutils.abilities.SteadfastFear;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.morale.MoraleType;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.abilities.SteadfastDoubt;
import rwcsim.basicutils.upgrades.Heraldry;
import rwcsim.factions.daqan.upgrades.Daqan;

public class LionStandardBearer extends BaseFigure implements FigureUpgrade, Cost, Daqan, Heraldry, InfantryUpgrade, AddAbility<SteadfastFear> {
    public LionStandardBearer() {

    }

    @Override
    public int price() {
        return 5;
    }

    @Override
    public SteadfastFear getAbility() {
        return new SteadfastFear(1);
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
    public String getUpgradeName() {
        return "Lion Standard Bearer";
    }
}

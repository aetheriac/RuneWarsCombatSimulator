package rwcsim.factions.daqan.upgrades.heraldry;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.stages.OnActivation;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Heraldry;
import rwcsim.factions.daqan.upgrades.Daqan;

public class RavenPennonBearer extends BaseFigure implements FigureUpgrade, Cost, Daqan, Heraldry, InfantryUpgrade, Exhaustable {
    public RavenPennonBearer() {

    }

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
}

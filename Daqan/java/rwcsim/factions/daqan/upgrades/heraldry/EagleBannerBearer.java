package rwcsim.factions.daqan.upgrades.heraldry;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.OnAttack;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Heraldry;
import rwcsim.factions.daqan.upgrades.Daqan;

public class EagleBannerBearer extends BaseFigure implements FigureUpgrade, Cost, Daqan, Heraldry, InfantryUpgrade {
    public EagleBannerBearer() {
    }

    @Override
    public int price() {
        return 4;
    }

    @Override
    public Figure getFigure() {
        return null;
    }
}

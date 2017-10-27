package rwcsim.factions.daqan.upgrades.champion;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.AfterThrowRunes;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.daqan.upgrades.Daqan;

public class GreyhavenChanneler extends BaseFigure implements FigureUpgrade, Champion, Daqan, InfantryUpgrade, Cost {
    public GreyhavenChanneler() {}

    @Override
    public int price() {
        return 6;
    }

    @Override
    public Figure getFigure() {
        return null;
    }
}

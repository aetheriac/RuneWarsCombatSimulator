package rwcsim.factions.waiqar.upgrades.music;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.OnDisengage;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Music;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class TacticalDrummer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Music, InfantryUpgrade {
    public TacticalDrummer(){
    }

    @Override
    public int price() {
        return 2;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 4;
    }
}

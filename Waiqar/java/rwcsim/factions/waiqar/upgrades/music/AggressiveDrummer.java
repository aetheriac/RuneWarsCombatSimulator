package rwcsim.factions.waiqar.upgrades.music;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.OnMarch;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Music;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class AggressiveDrummer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Music, InfantryUpgrade {

    public DialFace applyModifier() {
        return new DialFace(Face.MOVE_MOD_CHARGE);
    }

    @Override
    public int price() {
        return 5;
    }

    @Override
    public Figure getFigure() {
        return null;
    }
}

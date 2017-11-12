package rwcsim.factions.daqan.upgrades.music;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Music;
import rwcsim.factions.daqan.upgrades.Daqan;

public class RallyingCornicen extends BaseFigure implements FigureUpgrade, Cost, Daqan, Music, InfantryUpgrade, OnSkill {
    public RallyingCornicen() {}

    @Override
    public int price() {
        return 4;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public String getUpgradeName() {
        return "Rallying Cornicen";
    }
}

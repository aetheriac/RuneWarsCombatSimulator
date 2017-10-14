package rwcsim.factions.daqan.upgrades.champion;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.daqan.upgrades.Daqan;

public class UncontrolledGeomancer implements FigureUpgrade, Champion, Daqan, InfantryUpgrade, OnSkill, Cost {
    public UncontrolledGeomancer() {
    }

    @Override
    public int price() {
        return 6;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 2;
    }
}

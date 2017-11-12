package rwcsim.factions.daqan.upgrades.heavy;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.daqan.upgrades.Daqan;

public class SupportRuneGolem extends BaseFigure implements FigureUpgrade, Daqan, Heavy, SiegeUpgrade, OnSkill, Cost {
    public SupportRuneGolem() {
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
        return 4;
    }

    @Override
    public int getHealth() {
        return 2;
    }


    @Override
    public String getUpgradeName() {
        return "Support Rune Golem";
    }
}

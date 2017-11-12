package rwcsim.factions.waiqar.upgrades.heavy;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class SupportCarrionLancer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Heavy, SiegeUpgrade, OnSkill {
    public SupportCarrionLancer() {
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
        return 3;
    }

    @Override
    public int getHealth() {
        return 3;
    }

    @Override
    public String getUpgradeName() {
        return "Support Carrion Lancer";
    }
}

package rwcsim.factions.waiqar.upgrades.champion;

import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class Necromancer extends BaseFigure implements FigureUpgrade, Cost, Waiqar, Champion, InfantryUpgrade, OnSkill {
    public Necromancer() {
    }

    @Override
    public int price() {
        return 3;
    }

    @Override
    public int getArmor() {
        return 2;
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public String getUpgradeName() {
        return "Necromancer";
    }
}

package rwcsim.factions.waiqar.upgrades.champion;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.actions.OnSkill;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.CavalryUpgrade;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.HeroUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.waiqar.upgrades.Waiqar;

public class AnkaurMaroUpgrade extends BaseFigure implements Figure, FigureUpgrade, Cost, Waiqar, Champion, HeroUpgrade, InfantryUpgrade, CavalryUpgrade, Unique, OnSkill {
    public AnkaurMaroUpgrade() {
    }

    @Override
    public int price() {
        return 20;
    }

    @Override
    public int getArmor() {
        return 2;
    }

    @Override
    public int getHealth() {
        return 3;
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public String getUpgradeName() {
        return "Ankaur Maro Upgrade";
    }
}

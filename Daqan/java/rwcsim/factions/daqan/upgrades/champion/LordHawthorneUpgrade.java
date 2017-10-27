package rwcsim.factions.daqan.upgrades.champion;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.actions.AddDie;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.AfterAttacking;
import rwcsim.basicutils.upgrades.CavalryUpgrade;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.HeroUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.daqan.upgrades.Daqan;

public class LordHawthorneUpgrade extends BaseFigure implements FigureUpgrade, Champion, Daqan, HeroUpgrade, InfantryUpgrade, CavalryUpgrade, Unique, AddDie, Cost {
    public LordHawthorneUpgrade() {
    }

    @Override
    public int[] modifyDiePool() {
        return new int[]{0,0,1};
    }

    @Override
    public int price() {
        return 22;
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
        return 2;
    }
}

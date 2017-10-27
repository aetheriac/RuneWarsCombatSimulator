package rwcsim.factions.latari.upgrades.champion;

import rwcsim.basicutils.actions.AddDie;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.AddAbility;
import rwcsim.basicutils.stages.SpendSurges;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.HeroUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.abilities.Impact;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.latari.upgrades.Latari;

public class AlianaOfSummersongUpgrade extends BaseFigure implements FigureUpgrade, Champion, HeroUpgrade, InfantryUpgrade, Unique, AddDie, AddAbility<Impact>, Latari, Cost {
    public AlianaOfSummersongUpgrade() {
    }

    @Override
    public int[] modifyDiePool() {
        return new int[]{0,0,1};
    }

    @Override
    public int price() {
        return 12;
    }

    @Override
    public Impact getAbility() {
        return new Impact(1);
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

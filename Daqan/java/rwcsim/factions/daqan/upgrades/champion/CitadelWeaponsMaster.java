package rwcsim.factions.daqan.upgrades.champion;

import rwcsim.basicutils.actions.AddDie;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.daqan.upgrades.Daqan;

public class CitadelWeaponsMaster extends BaseFigure implements FigureUpgrade, Champion, Daqan, InfantryUpgrade, AddDie, Cost {

    public CitadelWeaponsMaster() {}

    public int[] modifyDiePool() {
        // add 1 red die
        return new int[]{1,0,0};
    }

    @Override
    public int price() {
        return 8;
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
    public String getUpgradeName() {
        return "Citadel Weapons Master";
    }
}

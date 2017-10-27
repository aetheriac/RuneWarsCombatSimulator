package rwcsim.factions.daqan.upgrades.champion;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.stages.AvailableUpgrades;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.InfantryUpgrade;
import rwcsim.basicutils.upgrades.Champion;
import rwcsim.factions.daqan.upgrades.Daqan;

public class ArtifactBearer extends BaseFigure implements FigureUpgrade, Champion, Daqan, InfantryUpgrade, Cost {
    public ArtifactBearer() {
    }

    @Override
    public int price() {
        return 4;
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

package rwcsim.factions.latari.upgrades.heavy;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.modifiers.AddStages;
import rwcsim.basicutils.modifiers.Exhaustable;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.concepts.FigureUpgrade;
import rwcsim.basicutils.upgrades.SiegeUpgrade;
import rwcsim.basicutils.upgrades.Heavy;
import rwcsim.factions.latari.upgrades.Latari;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.runes.RuneManager;

public class FrontlineAymhelinScion extends BaseFigure implements FigureUpgrade, Cost, Latari, Heavy, SiegeUpgrade, AddStages, Exhaustable {
    public FrontlineAymhelinScion() {
    }

    @Override
    public int price() {
        return 6;
    }


    @Override
    public void registerStages(Unit unit) {
        //unit.registerUpgrade(new OnAttack(), this);//<OnAttack,SpendSurges>
    }

    @Override
    public Figure getFigure() {
        return null;
    }

    @Override
    public int getArmor() {
        return 1 + RuneManager.getInstance().currentRuneCount(RuneFaces.STABLE);
    }

    @Override
    public int getHealth() {
        return 3;
    }

    @Override
    public boolean isExhausted() {
        return false;
    }

    @Override
    public void exhaust() {

    }

    @Override
    public void reset() {

    }
}

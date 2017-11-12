package rwcsim.factions.latari.upgrades.unique;

import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.modifiers.AddStages;
import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.HeroSpecific;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.factions.latari.AlianaOfSummersong;
import rwcsim.factions.latari.upgrades.Latari;

public class WildcallsInstinct implements Cost, Latari, HeroSpecific<AlianaOfSummersong>, Unique, AddStages {

    public DialFace applyModifier() {
        return new DialFace(Face.MOVE_MOD_TURN);
    }

    @Override
    public int price() {
        return 2;
    }

    @Override
    public void registerStages(Unit unit) {
        //<OnMarch, OnShift>
    }

    @Override
    public String getUpgradeName() {
        return "Wildcall's Instinct";
    }
}

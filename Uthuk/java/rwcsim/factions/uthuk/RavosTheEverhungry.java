package rwcsim.factions.uthuk;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.abilities.SteadfastFear;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.unit.UthukUnit;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.trays.HeroTray;
import rwcsim.factions.neutral.figures.HeroFigure;

import java.util.ArrayList;
import java.util.List;

public class RavosTheEverhungry extends UthukUnit implements Hero, Siege, Unique {

    public RavosTheEverhungry() {
        super();
    }
//    public RavosTheEverhungry(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Ravos the Everhungry"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, RuneFaces.UNSTABLE));
        actionFaces.add(new DialFace(6, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(8, Face.MARCH, FaceColor.BLUE, 4));
        actionFaces.add(new DialFace(2, Face.RALLY, FaceColor.GREEN));
        actionFaces.add(new DialFace(7, Face.SHIFT, FaceColor.GREEN, 2));
        actionFaces.add(new DialFace(3, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(6, Face.ATTACK_MELEE, FaceColor.RED));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.GREEN));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.GREEN));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 2));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(1, 1, 1));
        setRangedAttackPool(new DiePool(0,0,0));
    }
    public void populateFormations() {
        legalFormations.add(Formation.ONE);
    }


    @Override
    public void populateSlots(Formation formation) {
        legalUpgrades.add(UpgradeSlot.Artifact);
        legalUpgrades.add(UpgradeSlot.Unique);
    }

    @Override
    public Tray getTray() {
        return new HeroTray(this);
    }

    @Override
    public void setAbilities() {
        addAbility(new Brutal(1));
        addAbility(new Precise(1));
        addAbility(new SteadfastFear(2));
    }

    @Override
    public Figure getFigure() {
        return new HeroFigure(2,7);
    }
}

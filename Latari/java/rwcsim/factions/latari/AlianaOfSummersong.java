package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Impact;
import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.unit.LatariUnit;
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

public class AlianaOfSummersong extends LatariUnit implements Hero, Cavalry {

    public AlianaOfSummersong() {
        super();
    }
//    public AlianaOfSummersong(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Aliana of Summersong"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(2, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 4));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.GREEN, 2));
        actionFaces.add(new DialFace(7, Face.MARCH, FaceColor.GREEN, 4));
        actionFaces.add(new DialFace(3, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(2, Face.SKILL, FaceColor.YELLOW));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.BLUE, 1));
        modifierFaces.add(new DialFace(Face.SHIFT, FaceColor.GREEN, RuneFaces.NATURAL));
        modifierFaces.add(new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.WHITE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.WHITE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.WHITE, -1));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.WHITE));

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
        addAbility(new Impact(2));
        addAbility(new Precise(1));
    }

    @Override
    public Figure getFigure() {
        return new HeroFigure(3,3);
    }
}

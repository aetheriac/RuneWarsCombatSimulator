package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Lethal;
import rwcsim.basicutils.abilities.Protected;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.unit.LatariUnit;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.upgrades.Unique;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.HeroTray;
import rwcsim.factions.neutral.figures.HeroFigure;

import java.util.ArrayList;
import java.util.List;

public class MaeganCyndewin extends LatariUnit implements Hero, Cavalry, Unique {

    public MaeganCyndewin() {
        super();
    }
//    public MaeganCyndewin(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Maegan Cyndewin"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(4, Face.REFORM, FaceColor.BLUE));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(7, Face.MARCH, FaceColor.BLUE, 4));
        actionFaces.add(new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(4, Face.ATTACK_RANGED, FaceColor.RED));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE, 0));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.BLUE, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_SURGE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.RALLY, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(2, 1, 1));
        setRangedAttackPool(new DiePool(0,1,1));
    }

    public void populateFormations() {
        legalFormations.add(Formation.ONE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        legalUpgrades.add(UpgradeTypes.Artifact);
        legalUpgrades.add(UpgradeTypes.Unique);
    }

    @Override
    public Tray getTray() {
        return new HeroTray(this);
    }

    @Override
    public void setAbilities() {
        addAbility(new Lethal(RuneFaces.NATURAL));
        addAbility(new Protected(RuneFaces.STABLE));
    }

    @Override
    public Figure getFigure() {
        return new HeroFigure(2,4);
    }
}

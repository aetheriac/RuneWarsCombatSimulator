package rwcsim.factions.daqan;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.unit.DaqanUnit;
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

public class KariWraithstalker extends DaqanUnit implements Hero, Infantry, Unique {

    public KariWraithstalker() {
        super();
    }
//    public KariWraithstalker(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Kari Wraithstalker"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(2, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(2, Face.SKILL, FaceColor.GREEN));
        actionFaces.add(new DialFace(2, Face.ATTACK_RANGED, FaceColor.GREEN));
        actionFaces.add(new DialFace(5, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.GREEN));
        modifierFaces.add(new DialFace(Face.ENHANCE_SURGE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_ACCURACY, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.RALLY, FaceColor.WHITE));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(0, 1, 1));
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
        addAbility(new Brutal(1));
        addAbility(new Precise(1));
    }

    @Override
    public Figure getFigure() {
        return new HeroFigure(3,4);
    }
}

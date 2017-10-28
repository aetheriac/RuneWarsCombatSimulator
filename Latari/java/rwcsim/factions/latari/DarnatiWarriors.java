package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.unit.LatariUnit;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.InfantryTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.InfantryFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class DarnatiWarriors extends LatariUnit implements Infantry {


    public DarnatiWarriors() {
        super();
    }
//    public DarnatiWarriors(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Darnati Warriors"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(5, Face.RALLY, FaceColor.BLUE));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.GREEN, 2));
        actionFaces.add(new DialFace(6, Face.MARCH, FaceColor.GREEN,3));
        actionFaces.add(new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.BLUE, 1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.GREEN));
        modifierFaces.add(new DialFace(Face.SHIFT, FaceColor.GREEN, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.WHITE));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(0, 0, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }

    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
        legalFormations.add(Formation.THREE_BY_THREE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
                legalUpgrades.add(UpgradeSlot.Heavy);
            case 2:
                legalUpgrades.add(UpgradeSlot.Heraldry);
                legalUpgrades.add(UpgradeSlot.Music);
            case 1:
                legalUpgrades.add(UpgradeSlot.Champion);
            case 0:
                legalUpgrades.add(UpgradeSlot.Equipment);
                legalUpgrades.add(UpgradeSlot.Training);
                break;
            default:
                return;
        }
    }

    @Override
    public void setAbilities() {}


    @Override
    public Tray getTray() {
        return new InfantryTray(this);
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(1,1);
    }
}

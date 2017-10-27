package rwcsim.factions.daqan;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.unit.DaqanUnit;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeType;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.InfantryTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.InfantryFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class Spearmen extends DaqanUnit implements Infantry {


    public Spearmen() {
        super();
    }
//    public Spearmen(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Spearmen"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(3, Face.RALLY, FaceColor.GREEN));
        actionFaces.add(new DialFace(4, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(7, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(3, Face.ATTACK_MELEE, FaceColor.YELLOW));
        actionFaces.add(new DialFace(8, Face.MARCH, FaceColor.YELLOW, 4));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.BLUE, -2));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.BLUE, 1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.GREEN, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(1, 1, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }

    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
        legalFormations.add(Formation.THREE_BY_THREE);
        legalFormations.add(Formation.FOUR_BY_ONE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
                legalUpgrades.add(UpgradeType.Heavy);
            case 2:
                legalUpgrades.add(UpgradeType.Heraldry);
            case 1:
                legalUpgrades.add(UpgradeType.Champion);
            case 0:
                legalUpgrades.add(UpgradeType.Equipment);
                legalUpgrades.add(UpgradeType.Music);
                break;
            default:
                return;
        }
    }

    @Override
    public void setAbilities() {

    }


    @Override
    public Tray getTray() {
        return new InfantryTray(this);
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(1,1);
    }
}

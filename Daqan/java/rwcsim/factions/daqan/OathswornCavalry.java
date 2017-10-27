package rwcsim.factions.daqan;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Impact;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Cavalry;
import rwcsim.basicutils.unit.DaqanUnit;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeType;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.CavalryTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.CavalryFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class OathswornCavalry extends DaqanUnit implements Cavalry {


    public OathswornCavalry() {
        super();
    }
//    public OathswornCavalry(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Oathsworn Cavalry"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(7, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(9, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(4, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(7, Face.RALLY, FaceColor.GREEN));
        actionFaces.add(new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE, 1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MARCH, FaceColor.BLUE, 2));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.ENHANCE_MORTAL_STRIKE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(2, 1, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }

    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.TWO_BY_THREE);
        legalFormations.add(Formation.THREE_BY_THREE);
        legalFormations.add(Formation.FOUR_BY_ONE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
            case 2:
                legalUpgrades.add(UpgradeType.Champion);
            case 1:
                legalUpgrades.add(UpgradeType.Heraldry);
            case 0:
                legalUpgrades.add(UpgradeType.Equipment);
                legalUpgrades.add(UpgradeType.Training);
                break;
            default:
                return;
        }
    }

    @Override
    public Tray getTray() {
        return new CavalryTray(this);
    }

    @Override
    public void setAbilities() {
        addAbility(new Impact(1));
    }

    @Override
    public Figure getFigure() {
        return new CavalryFigure(2,1);
    }

}

package rwcsim.factions.uthuk;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.SteadfastFear;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.unit.UthukUnit;
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
public class UthukBerserkers extends UthukUnit implements Infantry {


    public UthukBerserkers() {
        super();
    }
//    public UthukBerserkers(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Uthuk Berserkers"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(6, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(4, Face.RALLY, FaceColor.GREEN));
        actionFaces.add(new DialFace(5, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(7, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(Face.BLANK));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_WHEEL, FaceColor.BLUE, -2));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.GREEN, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_MORALE, FaceColor.RED));
        modifierFaces.add(new DialFace(Face.BLANK));

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
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
                legalUpgrades.add(UpgradeTypes.Heavy);
            case 2:
                legalUpgrades.add(UpgradeTypes.Music);
            case 1:
                legalUpgrades.add(UpgradeTypes.Champion);
            case 0:
                legalUpgrades.add(UpgradeTypes.Equipment);
                legalUpgrades.add(UpgradeTypes.Heraldry);
                break;
            default:
                return;
        }
    }


    @Override
    public Tray getTray() {
        return new InfantryTray(this);
    }

    @Override
    public void setAbilities() {
        addAbility(new SteadfastFear(1));
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(1,1);
    }
}

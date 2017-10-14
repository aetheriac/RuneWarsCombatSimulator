package rwcsim.factions.uthuk;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Steadfast;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.morale.MoraleType;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.unit.UthukUnit;
import rwcsim.basicutils.upgrades.UpgradeType;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.InfantryTray;
import rwcsim.basicutils.concepts.Tray;

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
        if (Unit.legalFormations.size()>0) return;
        Unit.legalFormations.add(Formation.TWO_BY_ONE);
        Unit.legalFormations.add(Formation.TWO_BY_TWO);
        Unit.legalFormations.add(Formation.THREE_BY_TWO);
        Unit.legalFormations.add(Formation.THREE_BY_THREE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = Unit.legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
                Unit.legalUpgrades.add(UpgradeType.Heavy);
            case 2:
                Unit.legalUpgrades.add(UpgradeType.Music);
            case 1:
                Unit.legalUpgrades.add(UpgradeType.Champion);
            case 0:
                Unit.legalUpgrades.add(UpgradeType.Equipment);
                Unit.legalUpgrades.add(UpgradeType.Heraldry);
                break;
            default:
                return;
        }
    }


    @Override
    public Tray getTray() {
        return new InfantryTray();
    }

    @Override
    public void setAbilities() {
        addAbility(Steadfast.asAbility(MoraleType.FEAR, 1));
    }
}

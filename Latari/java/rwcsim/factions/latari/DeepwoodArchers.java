package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Precise;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.unit.LatariUnit;
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
public class DeepwoodArchers extends LatariUnit implements Infantry {


    public DeepwoodArchers() {
        super();
    }
//    public DeepwoodArchers(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Deepwood Archers"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 1));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.BLUE,1));
        actionFaces.add(new DialFace(6, Face.RALLY, FaceColor.GREEN));
        actionFaces.add(new DialFace(7, Face.MARCH, FaceColor.GREEN, 2));
        actionFaces.add(new DialFace(8, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(5, Face.ATTACK_RANGED, FaceColor.RED));
        actionFaces.add(new DialFace(9 ,Face.ATTACK_MELEE, FaceColor.RED));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.SHIFT, FaceColor.GREEN, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_SURGE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.SKILL, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(0, 1, 0));
        setRangedAttackPool(new DiePool(0,2,0));
    }

    public void populateFormations() {
        if (Unit.legalFormations.size()>0) return;
        Unit.legalFormations.add(Formation.TWO_BY_ONE);
        Unit.legalFormations.add(Formation.TWO_BY_TWO);
        Unit.legalFormations.add(Formation.TWO_BY_TWO_2);
        Unit.legalFormations.add(Formation.THREE_BY_TWO);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = Unit.legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
                Unit.legalUpgrades.add(UpgradeType.Heavy);
            case 2:
                Unit.legalUpgrades.add(UpgradeType.Champion);
                Unit.legalUpgrades.add(UpgradeType.Music);
            case 0:
                Unit.legalUpgrades.add(UpgradeType.Equipment);
                Unit.legalUpgrades.add(UpgradeType.Training);
                break;
            case 1:
                Unit.legalUpgrades.add(UpgradeType.Equipment);
                Unit.legalUpgrades.add(UpgradeType.Heraldry);
                Unit.legalUpgrades.add(UpgradeType.Music);
                Unit.legalUpgrades.add(UpgradeType.Training);
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
        addAbility(Precise.asAbility(1));
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(1,1);
    }
}

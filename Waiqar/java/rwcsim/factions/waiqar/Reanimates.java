package rwcsim.factions.waiqar;

import org.apache.log4j.Logger;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Regenerate;
import rwcsim.basicutils.abilities.SteadfastDoubt;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.*;
import rwcsim.basicutils.concepts.Infantry;
import rwcsim.basicutils.morale.MoraleType;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.unit.WaiqarUnit;
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
public class Reanimates extends WaiqarUnit implements Infantry {
    private static final Logger logger = Logger.getLogger(Reanimates.class);
    Formation formation;

    public Reanimates() {
        super();
    }
//    public Reanimates(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }




    @Override
    public String getName() { return "Reanimates"; }



    @Override
    public void initializeUnit() {
        logger.debug("initializeUnit()");
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
        modifierFaces.add(new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(2, 0, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }


    public void populateFormations() {
        if (legalFormations.size() > 0) return;
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
        legalFormations.add(Formation.THREE_BY_THREE);
        legalFormations.add(Formation.FOUR_BY_THREE);
    }


    @Override
    public void populateUpgrades(Formation formation) {}

    @Override
    public void populateUpgrades(boolean listContainsArdus, Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        if (listContainsArdus) {
            if (legalFormationIndex < (legalFormations.size()-1)) {
                legalFormationIndex++;
            }
        }

        switch (legalFormationIndex) {
            case 4:
            case 3:
                legalUpgrades.add(UpgradeType.Heavy);
                legalUpgrades.add(UpgradeType.Training);
            case 2:
                legalUpgrades.add(UpgradeType.Champion);
            case 1:
                legalUpgrades.add(UpgradeType.Heraldry);
            case 0:
                legalUpgrades.add(UpgradeType.Music);
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
        addAbility(new Regenerate(RuneFaces.NATURAL));
        addAbility(new SteadfastDoubt(1));
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(1,1);
    }
}

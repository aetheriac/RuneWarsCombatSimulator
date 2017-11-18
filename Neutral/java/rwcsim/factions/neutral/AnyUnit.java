package rwcsim.factions.neutral;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.abilities.Regenerate;
import rwcsim.basicutils.abilities.SteadfastDoubt;
import rwcsim.basicutils.concepts.*;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.trays.InfantryTray;
import rwcsim.basicutils.trays.SiegeTray;
import rwcsim.basicutils.unit.*;
import rwcsim.factions.neutral.figures.InfantryFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class AnyUnit extends BaseUnit implements Infantry, Siege, Cavalry {
    private static final Logger logger = LogManager.getLogger(AnyUnit.class);
//    Formation formation;

    public AnyUnit() {
        super();
    }
//    public Reanimates(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }




    @Override
    public String getName() { return "Any Unit"; }



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
        legalFormations.add(Formation.ONE);
        legalFormations.add(Formation.ONE_BY_TWO);
        legalFormations.add(Formation.ONE_BY_THREE);
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.TWO_BY_TWO_2);
        legalFormations.add(Formation.TWO_BY_THREE);
        legalFormations.add(Formation.THREE_BY_ONE);
        legalFormations.add(Formation.THREE_BY_TWO);
        legalFormations.add(Formation.THREE_BY_THREE);
        legalFormations.add(Formation.FOUR_BY_ONE);
        legalFormations.add(Formation.FOUR_BY_TWO);
        legalFormations.add(Formation.FOUR_BY_THREE);
    }


    @Override
    public void populateSlots(Formation formation) {}

    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);

        switch (legalFormationIndex) {
            case 4:
            case 3:
                legalUpgrades.add(UpgradeSlot.Heavy);
                legalUpgrades.add(UpgradeSlot.Training);
            case 2:
                legalUpgrades.add(UpgradeSlot.Champion);
            case 1:
                legalUpgrades.add(UpgradeSlot.Heraldry);
            case 0:
                legalUpgrades.add(UpgradeSlot.Music);
                break;
            default:
                return;
        }
    }

    @Override
    public Tray getTray() {
        return new SiegeTray(this);
    }

    @Override
    public DaqanUnit getAsDaqanUnit() {
        return null;
    }

    @Override
    public LatariUnit getAsLatariUnit() {
        return null;
    }

    @Override
    public UthukUnit getAsUthukUnit() {
        return null;
    }

    @Override
    public WaiqarUnit getAsWaiqarUnit() {
        return null;
    }

    @Override
    public void setAbilities() {
        addAbility(new Brutal(RuneFaces.STABLE));
//        addAbility(new Regenerate(RuneFaces.NATURAL));
//        addAbility(new SteadfastDoubt(1));
    }

    @Override
    public Figure getFigure() {
        return new InfantryFigure(4,3);
    }
}

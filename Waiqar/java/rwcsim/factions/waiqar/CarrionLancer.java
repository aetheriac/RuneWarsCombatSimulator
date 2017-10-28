package rwcsim.factions.waiqar;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.SteadfastDoubt;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.Siege;
import rwcsim.basicutils.unit.WaiqarUnit;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.SiegeTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.SiegeFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class CarrionLancer extends WaiqarUnit implements Siege {


    public CarrionLancer() {
        super();
    }
//    public CarrionLancer(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }

    @Override
    public String getName() { return "Carrion Lancer"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(7, Face.MARCH, FaceColor.BLUE, 4));
        actionFaces.add(new DialFace(3, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(5, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(3, Face.RALLY, FaceColor.YELLOW));
        actionFaces.add(new DialFace(6, Face.SKILL, FaceColor.YELLOW));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.GREEN, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.YELLOW, 1));
        modifierFaces.add(new DialFace(Face.MARCH, FaceColor.YELLOW, 3));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(1, 2, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }

    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.ONE);
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
    }


    @Override
    public void populateUpgrades(Formation formation) {}

    @Override
    public void populateUpgrades(boolean listContainsArdus, Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        if (listContainsArdus) {
            if (legalFormationIndex < (legalFormations.size() - 1)) {
                legalFormationIndex++;
            }
        }

        switch(legalFormationIndex) {
            case 3:
            case 2:
                legalUpgrades.add(UpgradeTypes.Equipment);
            case 1:
            case 0:
                legalUpgrades.add(UpgradeTypes.Training);
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
    public void setAbilities() {
        addAbility(new SteadfastDoubt(1));
    }

    @Override
    public Figure getFigure() {
        return new SiegeFigure(3,3);
    }
}

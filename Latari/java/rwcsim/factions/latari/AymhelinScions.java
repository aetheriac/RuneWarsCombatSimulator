package rwcsim.factions.latari;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Immobilize;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.slots.UpgradeSlot;
import rwcsim.basicutils.unit.LatariUnit;
import rwcsim.basicutils.concepts.Siege;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.trays.SiegeTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.SiegeFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class AymhelinScions extends LatariUnit implements Siege {


    public AymhelinScions() {
        super();
    }
//    public AymhelinScions(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Aymhelin Scions"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, RuneFaces.NATURAL));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(5, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(3, Face.SKILL, FaceColor.GREEN));
        actionFaces.add(new DialFace(6, Face.ATTACK_RANGED, FaceColor.GREEN));
        actionFaces.add(new DialFace(5, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(3, Face.RALLY, FaceColor.YELLOW));
        actionFaces.add(new DialFace(6, Face.SHIFT, FaceColor.YELLOW, 1));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.GREEN));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.WHITE, 1));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(1, 2, 0));
        setRangedAttackPool(new DiePool(1,1,0));
    }


    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.ONE);
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
    }


    @Override
    public void populateSlots(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
            case 2:
            case 1:
                legalUpgrades.add(UpgradeSlot.Equipment);
            case 0:
                legalUpgrades.add(UpgradeSlot.Heraldry);
                legalUpgrades.add(UpgradeSlot.Artifact);
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
        addAbility(new Immobilize(1));
    }

    @Override
    public Figure getFigure() {
        return new SiegeFigure(2,3);
    }
}

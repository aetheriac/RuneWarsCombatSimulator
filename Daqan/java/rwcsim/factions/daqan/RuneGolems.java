package rwcsim.factions.daqan;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Brutal;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.unit.DaqanUnit;
import rwcsim.basicutils.concepts.Siege;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.upgrades.UpgradeType;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.SiegeTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.SiegeFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class RuneGolems extends DaqanUnit implements Siege {


    public RuneGolems() {
        super();
    }
//    public RuneGolems(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Rune Golems"; }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, RuneFaces.UNSTABLE));
        actionFaces.add(new DialFace(8, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(7, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(3, Face.SKILL, FaceColor.RED));
        actionFaces.add(new DialFace(5, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(Face.BLANK));
        actionFaces.add(new DialFace(Face.BLANK));
        actionFaces.add(new DialFace(Face.BLANK));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.RED, RuneFaces.STABLE));
        modifierFaces.add(new DialFace(Face.RALLY, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.REFORM, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(2, 0, 0));
        setRangedAttackPool(new DiePool(0,0,0));
    }

    public void populateFormations() {
        if (legalFormations.size()>0) return;
        legalFormations.add(Formation.ONE);
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.THREE_BY_TWO);
        legalFormations.add(Formation.FOUR_BY_ONE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        int legalFormationIndex = legalFormations.indexOf(formation);
        switch(legalFormationIndex) {
            case 3:
            case 2:
                legalUpgrades.add(UpgradeType.Heraldry);
            case 1:
                legalUpgrades.add(UpgradeType.Equipment);
            case 0:
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
    public Figure getFigure() {
        return new SiegeFigure(4,2);
    }

    @Override
    public void setAbilities() {
        addAbility(new Brutal(RuneFaces.STABLE));
    }
}

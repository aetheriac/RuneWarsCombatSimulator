package rwcsim.factions.waiqar;

import rwcsim.basicutils.Formation;
import rwcsim.basicutils.abilities.Impact;
import rwcsim.basicutils.abilities.SteadfastFear;
import rwcsim.basicutils.concepts.Cavalry;
import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.unit.WaiqarUnit;
import rwcsim.basicutils.dials.CommandTool;
import rwcsim.basicutils.dials.DialFace;
import rwcsim.basicutils.dials.Face;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.upgrades.UpgradeTypes;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.basicutils.trays.CavalryTray;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.factions.neutral.figures.CavalryFigure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsayles on 8/18/17.
 */
public class DeathKnights extends WaiqarUnit implements Cavalry {


    public DeathKnights() {
        super();
    }
//    public DeathKnights(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }


    @Override
    public String getName() { return "Death Knights"; }

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
        if (legalFormations.size() > 0) return;
        legalFormations.add(Formation.ONE);
        legalFormations.add(Formation.TWO_BY_ONE);
        legalFormations.add(Formation.TWO_BY_TWO);
        legalFormations.add(Formation.TWO_BY_THREE);

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
            case 2:
                legalUpgrades.add(UpgradeTypes.Heraldry);
            case 1:
                legalUpgrades.add(UpgradeTypes.Champion);
            case 0:
                legalUpgrades.add(UpgradeTypes.Artifact);
                legalUpgrades.add(UpgradeTypes.Training);
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
        addAbility(new Impact(RuneFaces.STABLE));
        addAbility(new SteadfastFear(1));
    }

    @Override
    public Figure getFigure() {
        return new CavalryFigure(3,1);
    }
}

package rwcsim.factions.daqan;

import rwcsim.base.Formation;
import rwcsim.base.dials.CommandTool;
import rwcsim.base.dials.DialFace;
import rwcsim.base.dials.Face;
import rwcsim.base.dials.FaceColor;
import rwcsim.factions.base.*;
import rwcsim.factions.base.upgrades.Unique;
import rwcsim.factions.base.upgrades.UpgradeType;
import rwcsim.test.CoreUnit;
import rwcsim.utils.dice.DiePool;
import rwcsim.utils.trays.HeroTray;
import rwcsim.utils.trays.Tray;

import java.util.ArrayList;
import java.util.List;

public class LordHawthorne extends DaqanUnit implements Hero, Cavalry, Unique {

    public LordHawthorne() {
        super();
    }
//    public LordHawthorne(Formation formation, int[] unitStats, DiePool diePool){
//        super(formation, unitStats, diePool);
//    }

    @Override
    public void initializeUnit() {
        this.commandTool = new CommandTool();
        List<DialFace> actionFaces = new ArrayList<>();
        List<DialFace> modifierFaces = new ArrayList<>();

        actionFaces.add(new DialFace(3, Face.MARCH, FaceColor.BLUE, 2));
        actionFaces.add(new DialFace(4, Face.MARCH, FaceColor.BLUE, 3));
        actionFaces.add(new DialFace(6, Face.MARCH, FaceColor.BLUE, 4));
        actionFaces.add(new DialFace(2, Face.REFORM, FaceColor.GREEN));
        actionFaces.add(new DialFace(5, Face.SHIFT, FaceColor.GREEN, 1));
        actionFaces.add(new DialFace(6, Face.ATTACK_MELEE, FaceColor.RED));
        actionFaces.add(new DialFace(2, Face.SKILL, FaceColor.YELLOW));
        actionFaces.add(new DialFace(2, Face.ATTACK_MELEE, FaceColor.YELLOW));

        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURN, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE));
        modifierFaces.add(new DialFace(Face.MOVE_MOD_TURNING_CHARGE, FaceColor.BLUE, -1));
        modifierFaces.add(new DialFace(Face.MARCH, FaceColor.BLUE, 2));
        modifierFaces.add(new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1));
        modifierFaces.add(new DialFace(Face.RALLY, FaceColor.WHITE));
        modifierFaces.add(new DialFace(Face.DEFEND, FaceColor.WHITE, 1));
        modifierFaces.add(new DialFace(Face.BLANK));

        commandTool.setActionDialFaces(actionFaces);
        commandTool.setModifierDialFaces(modifierFaces);

        setMeleeAttackPool(new DiePool(0, 1, 1));
        setRangedAttackPool(new DiePool(0,1,1));
    }

    public void populateFormations() {
        legalFormations.add(Formation.ONE);
    }


    @Override
    public void populateUpgrades(Formation formation) {
        legalUpgrades.add(UpgradeType.Artifact);
        legalUpgrades.add(UpgradeType.Unique);
    }

    @Override
    public Tray getTray() {
        return new HeroTray();
    }
}
package rwcsim.gui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.gui.view.UnitFormationPanel;
import rwcsim.gui.view.UnitUpgradePanel;

import javax.swing.*;

public class UnitUpgradePanelController {
    private final Logger log = LogManager.getLogger(UnitUpgradePanelController.class);

    public UnitFormationPanel ufp;
    public UnitUpgradePanel uup;
    private JComboBox artifactComboBox;
    private JComboBox championComboBox;
    private JComboBox equipmentComboBox;
    private JComboBox heavyComboBox;
    private JComboBox heraldryComboBox;
    private JComboBox musicComboBox;
    private JComboBox trainingComboBox;
    private JComboBox uniqueComboBox;



    public UnitUpgradePanelController(UnitUpgradePanel uup, UnitFormationPanel ufp) {
        initialize(uup, ufp);
    }

    public void initialize(UnitUpgradePanel uup, UnitFormationPanel ufp) {
        initializeComponents(uup, ufp);
    }

    public void initializeComponents(UnitUpgradePanel uup, UnitFormationPanel ufp) {
        this.ufp = ufp;
        this.uup = uup;

        this.artifactComboBox = uup.getArtifactComboBox();
        this.championComboBox = uup.getChampionComboBox();
        this.equipmentComboBox = uup.getEquipmentComboBox();
        this.heavyComboBox = uup.getHeavyComboBox();
        this.heraldryComboBox = uup.getHeraldryComboBox();
        this.musicComboBox = uup.getMusicComboBox();
        this.trainingComboBox = uup.getTrainingComboBox();
        this.uniqueComboBox = uup.getUniqueComboBox();

//        loadArtifacts();
//        loatChampions();
//        loadEquipment();
//        loadHeavy();
//        loadHeraldry();
//        loadMusic();
//        loadTraining();
//        loadUnique();
    }
}

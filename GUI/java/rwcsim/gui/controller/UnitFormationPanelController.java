package rwcsim.gui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rwcsim.basicutils.Factions;
import rwcsim.basicutils.Formation;
import rwcsim.basicutils.concepts.Unit;
import rwcsim.basicutils.managers.FactionManager;
import rwcsim.basicutils.managers.UnitManager;
import rwcsim.gui.beans.FactionComboBean;
import rwcsim.gui.view.UnitFormationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class UnitFormationPanelController {
    private final Logger log = LogManager.getLogger(UnitFormationPanelController.class);
    private UnitFormationPanel    unitFormationPanelFrame;
    private JPanel    unitFormationPanel;
    private JComboBox formationComboBox;
    private JComboBox unitComboBox;
    private JComboBox factionComboBox;

    public UnitFormationPanelController(UnitFormationPanel ufp) {
        initialize(ufp);
    }

    public void initialize(UnitFormationPanel ufp) {
        initComponents(ufp);
        initListeners();
    }


    public void initComponents(UnitFormationPanel ufp) {
        unitFormationPanelFrame = ufp;

        factionComboBox = unitFormationPanelFrame.getFactionComboBox();
        unitComboBox = unitFormationPanelFrame.getUnitComboBox();
        formationComboBox = unitFormationPanelFrame.getFormationComboBox();

        loadFactions();
        loadUnits();
        loadFormations();
    }

    public Unit getUnit() {
        UnitManager um = FactionManager.instance().getUnitManager(Factions.valueOfFromString((String)factionComboBox.getSelectedItem()));
        return um.getUnit(um.getIdFromName((String)unitComboBox.getSelectedItem()));
    }

    public Formation getFormation() {
        return Formation.valueOf((String)formationComboBox.getSelectedItem());
    }


    private void loadFactions() {
        log.info("loadFactions");
        String[] factions = FactionComboBean.getFactionNames();
        Arrays.sort(factions);
        for (String f: factions) {
            factionComboBox.addItem(f);
        }
    }


    private void clearUnits() {
        log.info("clearUnits");
        DefaultComboBoxModel model = (DefaultComboBoxModel) unitComboBox.getModel();
        if (model.getSize()>0) {
            model.removeAllElements();
        }
    }

    private void loadUnits() {
        log.info("Loading Units for: "+ factionComboBox.getSelectedItem());
        DefaultComboBoxModel model = (DefaultComboBoxModel) unitComboBox.getModel();
        UnitManager unitManager = FactionManager.instance().getUnitManager(Factions.valueOfFromString((String)factionComboBox.getSelectedItem()));
        String[] names = unitManager.getAvailableUnitNames();
        Arrays.sort(names);
        for (String n: names) {
            model.addElement(n);
        }
    }

    private void clearFormations() {
        log.info("clearFormations");
        DefaultComboBoxModel model = (DefaultComboBoxModel) formationComboBox.getModel();
        if (model.getSize()>0) {
            model.removeAllElements();
        }
    }

    private void loadFormations() {
        log.info("Load Formations for: "+ factionComboBox.getSelectedItem() + " " + unitComboBox.getSelectedItem());
        DefaultComboBoxModel model = (DefaultComboBoxModel) formationComboBox.getModel();
        UnitManager unitManager = FactionManager.instance().getUnitManager(Factions.valueOfFromString((String)factionComboBox.getSelectedItem()));
        String unit = (String)unitComboBox.getSelectedItem();
        log.info("Loading Formations for "+ unit);
        List<Formation> formations = unitManager.availableFormations(unit);
        for (Formation formation:formations) {
            model.addElement(formation.name());
        }
    }

    public void initListeners() {
        factionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("FactionComboBox "+e.getActionCommand()+ ": "+factionComboBox.getSelectedItem()+" "+ factionComboBox.getSelectedIndex());
                if (e.getActionCommand().compareTo("comboBoxChanged")==0) {
                    if (formationComboBox.getItemCount()>0) {
                        clearFormations();
                    }
                    if (unitComboBox.getItemCount()>0) {
                        clearUnits();
                    }
                    loadUnits();
                    loadFormations();
                }
            }
        });

        unitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("UnitComboBox "+e.getActionCommand() + ": "+unitComboBox.getSelectedItem()+" "+unitComboBox.getSelectedIndex());
                if (e.getActionCommand().compareTo("comboBoxChanged")==0) {
                    if (formationComboBox.getItemCount()>0) {
                        clearFormations();
                    }
                    if (unitComboBox.getItemCount()>0) {
                        loadFormations();
                    }
                }
            }
        });

        formationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("FormationComboBox "+e.getActionCommand()+" "+formationComboBox.getSelectedItem()+" "+formationComboBox.getSelectedIndex());

            }
        });
    }
}

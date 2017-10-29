package rwcsim.gui;

import rwcsim.basicutils.Configuration;
import rwcsim.gui.controller.SimulatorController;
import rwcsim.gui.view.SimulatorPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = Configuration.getInstance();
        SimulatorController simulatorController = new SimulatorController();

        SwingUtilities.invokeLater(() -> {
            SimulatorPanel frame = new SimulatorPanel(simulatorController, configuration);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setPreferredSize(new Dimension(900, 200));
            frame.pack();
            frame.setVisible(true);
        });
    }
}

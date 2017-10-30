package rwcsim.gui.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import rwcsim.basicutils.dice.DieFace;
import rwcsim.basicutils.dice.DiePool;
import rwcsim.interactions.ai.behaviors.RerollBehavior;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleRerollLogicDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel redDiePanel;
    private JPanel blueDiePanel;
    private JPanel whiteDiePanel;

    private JCheckBox redBlankCheckbox;
    private JCheckBox redHitCheckBox;
    private JCheckBox redMoraleCheckBox;
    private JCheckBox redHitMoraleCheckBox;
    private JCheckBox redHitSurgeCheckBox;
    private JCheckBox redHitHitCheckBox;

    private JCheckBox blueBlankCheckBox;
    private JCheckBox blueHitCheckBox;
    private JCheckBox blueSurgeCheckBox;
    private JCheckBox blueAccuracyCheckBox;
    private JCheckBox blueHitAccuracyCheckBox;
    private JCheckBox blueSurgeSurgeCheckBox;
    private JCheckBox blueHitSurgeCheckBox;

    private JCheckBox whiteBlankCheckBox;
    private JCheckBox whiteHitCheckBox;
    private JCheckBox whiteMortalStrikeCheckBox;
    private JCheckBox whiteHitHitCheckBox;
    private JCheckBox whiteHitAccuracyCheckBox;
    private JCheckBox whiteHitMoraleCheckBox;
    private JCheckBox whiteHitSurgeCheckBox;
    private JCheckBox whiteSurgeSurgeCheckBox;
    private JCheckBox whiteSurgeMoraleCheckBox;

    private JPanel bottomPanel;
    private JPanel buttonPanel;
    private JPanel diePanel;

    private HashMap<DieFace, JCheckBox> redDieFaceToCheckBoxMap = new HashMap<>();
    private HashMap<DieFace, JCheckBox> blueDieFaceToCheckBoxMap = new HashMap<>();
    private HashMap<DieFace, JCheckBox> whiteDieFaceToCheckBoxMap = new HashMap<>();

    private RerollBehavior results;


    private static final DieFace[] defaultRedDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE
    };

    private static final DieFace[] defaultBlueDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_ACCURACY, DieFace.HIT_SURGE,
    };

    private static final DieFace[] defaultWhiteDieSelection = new DieFace[]{
            DieFace.HIT, DieFace.HIT_HIT, DieFace.HIT_SURGE, DieFace.HIT_MORALE, DieFace.HIT_ACCURACY, DieFace.MORTAL_STRIKE,
    };

    public SimpleRerollLogicDialog() {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setTitle("Select Die Faces To Keep Before Rerolling");

        setColors();
        initializeMaps();
        setDefaults();

        this.pack();

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initializeMaps() {
        redDieFaceToCheckBoxMap.put(DieFace.BLANK, redBlankCheckbox);
        redDieFaceToCheckBoxMap.put(DieFace.HIT, redHitCheckBox);
        redDieFaceToCheckBoxMap.put(DieFace.MORALE, redMoraleCheckBox);
        redDieFaceToCheckBoxMap.put(DieFace.HIT_HIT, redHitHitCheckBox);
        redDieFaceToCheckBoxMap.put(DieFace.HIT_SURGE, redHitSurgeCheckBox);
        redDieFaceToCheckBoxMap.put(DieFace.HIT_MORALE, redHitMoraleCheckBox);

        blueDieFaceToCheckBoxMap.put(DieFace.BLANK, blueBlankCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.HIT, blueHitCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.SURGE, blueSurgeCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.ACCURACY, blueAccuracyCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.HIT_ACCURACY, blueHitAccuracyCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.HIT_SURGE, blueHitSurgeCheckBox);
        blueDieFaceToCheckBoxMap.put(DieFace.SURGE_SURGE, blueSurgeSurgeCheckBox);

        whiteDieFaceToCheckBoxMap.put(DieFace.BLANK, whiteBlankCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.HIT, whiteHitCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.MORTAL_STRIKE, whiteMortalStrikeCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.HIT_HIT, whiteHitHitCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.HIT_ACCURACY, whiteHitAccuracyCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.HIT_MORALE, whiteHitMoraleCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.HIT_SURGE, whiteHitSurgeCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.SURGE_SURGE, whiteSurgeSurgeCheckBox);
        whiteDieFaceToCheckBoxMap.put(DieFace.SURGE_MORALE, whiteSurgeMoraleCheckBox);
    }

    private void setDefaults() {
        for (DieFace face : defaultRedDieSelection) {
            redDieFaceToCheckBoxMap.get(face).setSelected(true);
        }
        for (DieFace face : defaultBlueDieSelection) {
            blueDieFaceToCheckBoxMap.get(face).setSelected(true);
        }
        for (DieFace face : defaultWhiteDieSelection) {
            whiteDieFaceToCheckBoxMap.get(face).setSelected(true);
        }
    }

    // Copies the background color of the panel to its children components
    private void setColors() {
        for (Component component : redDiePanel.getComponents()) {
            component.setBackground(redDiePanel.getBackground());
        }
        for (Component component : blueDiePanel.getComponents()) {
            component.setBackground(blueDiePanel.getBackground());
        }
        for (Component component : whiteDiePanel.getComponents()) {
            component.setBackground(whiteDiePanel.getBackground());
        }
    }

    private List<DieFace> getSelectedDieFaces(int diePoolColor) {
        HashMap<DieFace, JCheckBox> map;
        switch (diePoolColor) {
            case DiePool.RED_DIE:
                map = redDieFaceToCheckBoxMap;
                break;
            case DiePool.BLUE_DIE:
                map = blueDieFaceToCheckBoxMap;
                break;
            case DiePool.WHITE_DIE:
                map = whiteDieFaceToCheckBoxMap;
                break;
            default:
                throw new RuntimeException("Invalid diePoolColor = " + diePoolColor);
        }

        return map.entrySet().stream().filter((entry) -> entry.getValue().isSelected()).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public RerollBehavior showDialog() {
        this.setVisible(true);
        return results;
    }

    private void onOK() {
        final HashSet<DieFace> acceptedRedFaces = new HashSet<>(getSelectedDieFaces(DiePool.RED_DIE));
        final HashSet<DieFace> acceptedBlueFaces = new HashSet<>(getSelectedDieFaces(DiePool.BLUE_DIE));
        final HashSet<DieFace> acceptedWhiteFaces = new HashSet<>(getSelectedDieFaces(DiePool.WHITE_DIE));

        results = new RerollBehavior() {
            @Override
            public RerollBehavior getBehavior() {

                return null;
            }
        };

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        results = null;
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(bottomPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        bottomPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        bottomPanel.add(buttonPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        buttonPanel.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        buttonPanel.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        diePanel = new JPanel();
        diePanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(diePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        redDiePanel = new JPanel();
        redDiePanel.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        redDiePanel.setBackground(new Color(-868421));
        redDiePanel.setEnabled(true);
        diePanel.add(redDiePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        redDiePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Red Die", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, redDiePanel.getFont()), new Color(-16777216)));
        redBlankCheckbox = new JCheckBox();
        redBlankCheckbox.setText("Blank");
        redDiePanel.add(redBlankCheckbox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redHitCheckBox = new JCheckBox();
        redHitCheckBox.setText("Hit");
        redDiePanel.add(redHitCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redMoraleCheckBox = new JCheckBox();
        redMoraleCheckBox.setText("Morale");
        redDiePanel.add(redMoraleCheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redHitMoraleCheckBox = new JCheckBox();
        redHitMoraleCheckBox.setText("Hit-Morale");
        redDiePanel.add(redHitMoraleCheckBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redHitSurgeCheckBox = new JCheckBox();
        redHitSurgeCheckBox.setText("Hit-Surge");
        redDiePanel.add(redHitSurgeCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        redHitHitCheckBox = new JCheckBox();
        redHitHitCheckBox.setText("Hit-Hit");
        redDiePanel.add(redHitHitCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        redDiePanel.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        blueDiePanel = new JPanel();
        blueDiePanel.setLayout(new GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        blueDiePanel.setBackground(new Color(-4208910));
        diePanel.add(blueDiePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        blueDiePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Blue Die", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, blueDiePanel.getFont())));
        blueBlankCheckBox = new JCheckBox();
        blueBlankCheckBox.setText("Blank");
        blueDiePanel.add(blueBlankCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        blueDiePanel.add(spacer3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        blueHitCheckBox = new JCheckBox();
        blueHitCheckBox.setText("Hit");
        blueDiePanel.add(blueHitCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blueSurgeCheckBox = new JCheckBox();
        blueSurgeCheckBox.setText("Surge");
        blueDiePanel.add(blueSurgeCheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blueAccuracyCheckBox = new JCheckBox();
        blueAccuracyCheckBox.setText("Accuracy");
        blueDiePanel.add(blueAccuracyCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blueHitAccuracyCheckBox = new JCheckBox();
        blueHitAccuracyCheckBox.setText("Hit-Accuracy");
        blueDiePanel.add(blueHitAccuracyCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blueSurgeSurgeCheckBox = new JCheckBox();
        blueSurgeSurgeCheckBox.setText("Surge-Surge");
        blueDiePanel.add(blueSurgeSurgeCheckBox, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        blueHitSurgeCheckBox = new JCheckBox();
        blueHitSurgeCheckBox.setText("Hit-Surge");
        blueDiePanel.add(blueHitSurgeCheckBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteDiePanel = new JPanel();
        whiteDiePanel.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        whiteDiePanel.setBackground(new Color(-197380));
        diePanel.add(whiteDiePanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        whiteDiePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "White Die", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, whiteDiePanel.getFont())));
        whiteBlankCheckBox = new JCheckBox();
        whiteBlankCheckBox.setText("Blank");
        whiteDiePanel.add(whiteBlankCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        whiteDiePanel.add(spacer4, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        whiteHitCheckBox = new JCheckBox();
        whiteHitCheckBox.setText("Hit");
        whiteDiePanel.add(whiteHitCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteMortalStrikeCheckBox = new JCheckBox();
        whiteMortalStrikeCheckBox.setText("Mortal Strike");
        whiteDiePanel.add(whiteMortalStrikeCheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteHitHitCheckBox = new JCheckBox();
        whiteHitHitCheckBox.setText("Hit-Hit");
        whiteDiePanel.add(whiteHitHitCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteHitAccuracyCheckBox = new JCheckBox();
        whiteHitAccuracyCheckBox.setText("Hit-Accuracy");
        whiteDiePanel.add(whiteHitAccuracyCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteHitMoraleCheckBox = new JCheckBox();
        whiteHitMoraleCheckBox.setText("Hit-Morale");
        whiteDiePanel.add(whiteHitMoraleCheckBox, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteHitSurgeCheckBox = new JCheckBox();
        whiteHitSurgeCheckBox.setText("Hit-Surge");
        whiteDiePanel.add(whiteHitSurgeCheckBox, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteSurgeSurgeCheckBox = new JCheckBox();
        whiteSurgeSurgeCheckBox.setText("Surge-Surge");
        whiteDiePanel.add(whiteSurgeSurgeCheckBox, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        whiteSurgeMoraleCheckBox = new JCheckBox();
        whiteSurgeMoraleCheckBox.setText("Surge-Morale");
        whiteDiePanel.add(whiteSurgeMoraleCheckBox, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}

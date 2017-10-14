package rwcsim.basicutils.concepts;


public interface Tray {
    int TRAY_LOCATION_FRONT_LEFT = 0;
    int TRAY_LOCATION_FRONT_RIGHT = 1;
    int TRAY_LOCATION_REAR_LEFT = 2;
    int TRAY_LOCATION_REAR_RIGHT = 3;
    int TRAY_LOCATION_SOLO = 0;
    int TRAY_LOCATION_LEFT = 0;
    int TRAY_LOCATION_RIGHT = 1;

    void setFigureUpgrade(int trayLocation, FigureUpgrade figureUpgrade);
    boolean containsUpgradeFigure();
    int[] getUpgradeSlots();

    void setAccuracy(int trayLocation, int accuracyCount);
    void clearAccuracy();
    boolean hasAccuracy();
    int[] getAccuracySlots();
    boolean applyDamage(int count);
    boolean applyDamageToSlot(int slot, int count);

    boolean isEmpty();
    boolean hasEmptySlots();
    int[] getEmptySlots();

}

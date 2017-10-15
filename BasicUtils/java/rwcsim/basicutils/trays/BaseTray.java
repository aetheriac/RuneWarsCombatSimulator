package rwcsim.basicutils.trays;

import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.concepts.FigureUpgrade;

public abstract class BaseTray implements Tray {
    class DeadFigure implements Figure {

        @Override
        public boolean isAlive() {
            return false;
        }

        @Override
        public int applyDamage(int count) {
            return count;
        }

        @Override
        public void setInitialHealth() {}
    }

    protected int figureCount;
    protected Figure[] trayLayout;
    protected int[] accuracyCountPerSlot;
    protected int[] figureUpgradeSlots;


    BaseTray(int c) {
        figureCount = c;
        trayLayout = new Figure[c];
        accuracyCountPerSlot = new int[c];
        for (int i = 0; i < figureCount; i++) {
            trayLayout[i] = new BaseFigure();
        }
        figureUpgradeSlots = new int[c];
        clearAccuracy();
    }



    @Override
    public void setFigureUpgrade(int trayLocation, FigureUpgrade figureUpgrade) {
        trayLayout[trayLocation] = figureUpgrade.getFigure();
    }

    @Override
    public boolean containsUpgradeFigure() {
        for (int i = 0; i< trayLayout.length; i++) {
            if (trayLayout[i].isUpgrade()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int[] getUpgradeSlots() {
        return figureUpgradeSlots;
    }




    @Override
    public void setAccuracy(int trayLocation, int accuracyCount) {
        accuracyCountPerSlot[trayLocation] += accuracyCount;
    }


    @Override
    public void clearAccuracy() {
        for (int i = 0; i< accuracyCountPerSlot.length; i++) {
            accuracyCountPerSlot[i] = 0;
        }
    }

    @Override
    public boolean hasAccuracy() {
        for ( int i : accuracyCountPerSlot ) {
            if (i>0) { return true; }
        }
        return false;
    }

    @Override
    public int[] getAccuracySlots() {
        return accuracyCountPerSlot;
    }

    @Override
    public int applyDamage(int count) {
        int remainingDamage = count;

        for (int i = 0; i < figureCount; i++) {
            remainingDamage = trayLayout[i].applyDamage(remainingDamage);
            if (!trayLayout[i].isAlive()) {
                trayLayout[i] = new DeadFigure();
            }
            if (remainingDamage <= 0) break;
        }
        return remainingDamage;
    }

    @Override
    public int applyDamageToSlot(int slot, int count) {
        return trayLayout[slot].applyDamage(count);
    }



    @Override
    public boolean isEmpty() {
        for (Figure f:trayLayout) {
            if (f.isAlive()) return false;
        }
        return true;
    }

    @Override
    public boolean hasEmptySlots() {
        for ( Figure f : trayLayout ) {
            if (!f.isAlive()) return true;
        }
        return false;
    }

    @Override
    public int[] getEmptySlots() {
        int counter = 0;
        int j = 0;
        int[] empty;
        for (Figure f:trayLayout) if (!f.isAlive()) counter++;
        empty = new int[counter];
        for ( int i = 0; i < trayLayout.length; i++) {
            if (!trayLayout[i].isAlive()) {
                empty[j++] = i;
            }
        }
        return empty;
    }
}

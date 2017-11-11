package rwcsim.basicutils.trays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.unit.BaseUnit;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class TrayTest {


    /**
     * figures, DeadFigures and the like
     */

    Tray infantryTray;
    Tray siegeTray;
    Tray cavalryTray;
    Tray heroTray;

    @BeforeEach
    public void setup() {
        infantryTray = new InfantryTray(new BaseUnit.NullInfantryUnit());
        siegeTray = new SiegeTray(new BaseUnit.NullSiegeUnit());
        cavalryTray = new CavalryTray(new BaseUnit.NullCavalryUnit());
        heroTray = new HeroTray(new BaseUnit.NullHeroUnit());
    }

    @AfterEach
    public void teardown() {
        infantryTray = null;
        siegeTray = null;
        cavalryTray = null;
        heroTray = null;
    }


    @Test
    public void testIsEmpty() {
        assertFalse(infantryTray.isEmpty(), "InfantryTray is not empty.");
        assertEquals(0, infantryTray.applyDamage(1));
    }

    @Test
    public void testFullEmpty() {
        assertFalse(infantryTray.hasEmptySlots());
        assertEquals(infantryTray.getEmptySlots().length, 0);

        assertEquals(0, infantryTray.applyDamage(1));
        assertEquals(1, infantryTray.applyDamage(4));
    }

    @Test
    public void testPartialTray() {
        assertEquals(3, infantryTray.applyDamage(7));
        assertTrue(infantryTray.isEmpty(), "InfantryTray is empty.");
    }

    @Test
    public void testFigureUpgrades() {

    }

    @Test
    public void testAccuracy() {
        assertFalse(infantryTray.hasAccuracy(),"Infantry tray does not have accuracies assigned.");
    }

    @Test
    public void testSmallDamage() {
        assertEquals(0, infantryTray.applyDamage(2));
        assertEquals(2, infantryTray.getFigureCount());
    }

    @Test
    public void testSiegeDamage() {
        assertEquals(0, siegeTray.applyDamage(3));
        assertEquals(1, siegeTray.getFigureCount());
        assertEquals(0, siegeTray.applyDamage(6));
        assertEquals(0, siegeTray.getFigureCount());
    }

    @Test
    public void testCavalryDamage() {
        assertEquals(2, cavalryTray.getFigureCount());
        assertEquals(0, cavalryTray.applyDamage(2));
        assertEquals(1, cavalryTray.getFigureCount());
        assertEquals(true, cavalryTray.hasEmptySlots());
        assertEquals(false, cavalryTray.isEmpty());
        assertEquals(0, cavalryTray.applyDamage(2));
        assertEquals(0, cavalryTray.getFigureCount());
    }

    @Test
    public void testHeroDamage() {
        assertEquals(1, heroTray.getFigureCount());
        assertEquals(0, heroTray.applyDamage(2));
        assertEquals(1, heroTray.getFigureCount());
        assertEquals(0, heroTray.applyDamage(2));
        assertEquals(1, heroTray.getFigureCount());
        assertEquals(3, heroTray.applyDamage(15));
        assertEquals(0, heroTray.getFigureCount());
    }

    @Test
    public void testRefills() {
        assertFalse(infantryTray.hasEmptySlots());
        assertEquals(infantryTray.getEmptySlots().length, 0);

        assertEquals(0, infantryTray.applyDamage(2));
        assertTrue(infantryTray.hasEmptySlots());
        assertEquals(2, infantryTray.getEmptySlots().length);
        assertEquals(0, infantryTray.refillEmptySlots(new BaseUnit.NullInfantryUnit(),1));
        assertEquals(1, infantryTray.getEmptySlots().length);
        assertEquals(1, infantryTray.refillEmptySlots(new BaseUnit.NullInfantryUnit(),2));
    }


    @Test
    public void testApplyMortalStrikes() {
        assertFalse(infantryTray.hasEmptySlots());
        assertFalse(siegeTray.hasEmptySlots());

        assertEquals(0, infantryTray.applyMortalStrikes(1));
        assertEquals(0, siegeTray.applyMortalStrikes(1));
        assertTrue(infantryTray.hasEmptySlots());
        assertFalse(siegeTray.hasEmptySlots());

        assertEquals(1, siegeTray.applyMortalStrikes(3));
        assertTrue(siegeTray.hasEmptySlots());
    }

    @Test
    public void testUpgradeFigure() {
        int[] expectedResult = new int[]{0,0,0,0};

        assertFalse(infantryTray.containsUpgradeFigure());
        int[] actualResults = infantryTray.getUpgradeSlots();

        assertTrue(Arrays.equals(expectedResult, actualResults));



        // BEEF THIS UP WHEN UPGRADE FIGURES COME INTO PLAY
    }

    @Test
    public void testAccuracies() {
        int[] expectedResult = new int[]{0,0,0,2};

        assertFalse(infantryTray.hasAccuracy());
        infantryTray.setAccuracy(Tray.TRAY_LOCATION_REAR_RIGHT, 2);
        assertTrue(infantryTray.hasAccuracy());

        int[] actualResults = infantryTray.getAccuracySlots();
        assertTrue(Arrays.equals(expectedResult, actualResults));

        infantryTray.clearAccuracy();
        assertFalse(infantryTray.hasAccuracy());
    }

    @Test
    public void testSlotSpecificDamage() {
        int[] expectedInfantryResult = new int[]{Tray.TRAY_LOCATION_FRONT_RIGHT,Tray.TRAY_LOCATION_REAR_RIGHT};
        assertEquals(1, infantryTray.applyDamageToSlot(Tray.TRAY_LOCATION_FRONT_RIGHT, 2));
        assertEquals(0, infantryTray.applyDamageToSlot(Tray.TRAY_LOCATION_REAR_RIGHT, 1));

        int[] actualInfantryResult  = infantryTray.getEmptySlots();
        assertTrue(Arrays.equals(expectedInfantryResult, actualInfantryResult));

        int[] expectedCavalryResult = new int[]{Tray.TRAY_LOCATION_LEFT};
        assertEquals(1, cavalryTray.applyMortalStrikesToSlot(Tray.TRAY_LOCATION_LEFT, 2));

        int[] actualCavalryResult = cavalryTray.getEmptySlots();
        assertTrue(Arrays.equals(expectedCavalryResult, actualCavalryResult));
    }
}

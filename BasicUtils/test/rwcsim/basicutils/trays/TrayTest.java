package rwcsim.basicutils.trays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rwcsim.basicutils.concepts.Tray;
import rwcsim.basicutils.figure.BaseFigure;
import rwcsim.basicutils.unit.BaseUnit;

import static org.junit.jupiter.api.Assertions.*;


public class TrayTest {


    /**
     * TODO
     * Add comprehensive testing around tray damage, light, heavy
     * variety of types
     * each method
     * figures, DeadFigures and the like
     * BaseTray
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
}

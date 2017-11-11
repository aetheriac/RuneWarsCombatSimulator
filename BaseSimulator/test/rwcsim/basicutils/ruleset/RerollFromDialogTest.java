package rwcsim.basicutils.ruleset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rwcsim.basicutils.dice.BlueDie;
import rwcsim.basicutils.dice.Die;
import rwcsim.basicutils.dice.RedDie;
import rwcsim.basicutils.dice.WhiteDie;
import rwcsim.basicutils.managers.RuleSetManager;

import static org.junit.jupiter.api.Assertions.*;

public class RerollFromDialogTest {
    RerollFromDialog rerollFromDialog;

    @BeforeEach
    public void setup() {
        rerollFromDialog = new RerollFromDialog();
    }

    @AfterEach
    public void tearDown() {
        rerollFromDialog = null;
    }

    @Test
    public void testBasics() {
        assertEquals("REROLL_FROM_DIALOG", rerollFromDialog.name());
        assertFalse(rerollFromDialog.isEnabled());

        RuleSetManager.addRule(new RerollFromDialog(), true);
        assertTrue(rerollFromDialog.isEnabled());
    }

    @Test
    public void testShouldReroll() {
        Die die = RedDie.get();
        int count = 8;
        boolean[] redExpectedResults = new boolean[]{true, true, false, false, true, false, false, false};
        for (int i = 0; i<count; i++) {
            assertEquals(redExpectedResults[i], rerollFromDialog.shouldReroll(die.getDieType(), die.result(i)));
        }

        die = BlueDie.get();
        boolean[] blueExpectedResults = new boolean[]{true, false, false, true, true, false, false, true};
        for (int i = 0; i<count; i++) {
            assertEquals(blueExpectedResults[i], rerollFromDialog.shouldReroll(die.getDieType(), die.result(i)));
        }

        die = WhiteDie.get();
        boolean[] whiteExpectedResults = new boolean[]{true, false, false, false, false, false, false, false, false, false, true, true};
        for (int i = 0; i<count; i++) {
            assertEquals(whiteExpectedResults[i], rerollFromDialog.shouldReroll(die.getDieType(), die.result(i)));
        }
    }
}

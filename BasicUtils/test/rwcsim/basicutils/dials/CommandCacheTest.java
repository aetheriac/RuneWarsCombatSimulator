package rwcsim.basicutils.dials;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rwcsim.basicutils.dials.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommandCacheTest {

    private CommandCache scc;

    @BeforeEach
    public void setup() {
        scc = new CommandCache();
    }

    @AfterEach
    public void teardown() {
        scc = null;
    }

    @Test
    public void testCache() {
        // pulled from Ardus Ix'Erebus
        DialFace com1 = new DialFace(4, Face.MARCH, FaceColor.BLUE, 2);
        DialFace mod1 = new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE);
        Command command1 = new Command(com1, mod1);

        DialFace com2 = new DialFace(3, Face.ATTACK_MELEE, FaceColor.RED);
        DialFace mod2 = new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1);
        Command command2 = new Command(com2, mod2);

        scc.setCommand(0, command1);
        scc.setCommand(3, command2);

        assertEquals(command1, scc.getCommand(0));
        assertEquals(command1, scc.getCommand(1));
        assertEquals(command1, scc.getCommand(2));
        assertEquals(command2, scc.getCommand(3));
        assertEquals(command2, scc.getCommand(4));
        assertEquals(command2, scc.getCommand(5));
        assertEquals(command2, scc.getCommand(6));
        assertEquals(command2, scc.getCommand(7));

        scc.clearCache();
        assertEquals(null, scc.getCommand(7));
    }
}

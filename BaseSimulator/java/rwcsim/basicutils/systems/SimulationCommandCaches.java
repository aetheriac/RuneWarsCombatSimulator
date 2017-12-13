package rwcsim.basicutils.systems;

import rwcsim.basicutils.dials.*;
import rwcsim.basicutils.unit.DeployableUnit;

public class SimulationCommandCaches {
    public static CommandCache getChargeAttack(DeployableUnit unit) {
        CommandCache cc = new CommandCache();

        // pulled from Spearmen
        DialFace com1 = new DialFace(4, Face.MARCH, FaceColor.BLUE, 2);
        DialFace mod1 = new DialFace(Face.MOVE_MOD_CHARGE, FaceColor.BLUE);
        Command command1 = new Command(com1, mod1);

        DialFace com2 = new DialFace(7, Face.ATTACK_MELEE, FaceColor.RED);
        DialFace mod2 = new DialFace(Face.ENHANCE_HIT, FaceColor.RED, 1);
        Command command2 = new Command(com2, mod2);

        cc.setCommand(0, command1);
        cc.setCommand(1, command2);

        return cc;
    }

    public static CommandCache getSetAttack(DeployableUnit unit) {
        CommandCache cc = new CommandCache();

        // pulled from Reanimates
        DialFace com1 = new DialFace(4, Face.RALLY, FaceColor.GREEN);
        DialFace mod1 = new DialFace(Face.DEFEND, FaceColor.GREEN, 1);
        Command command1 = new Command(com1, mod1);

        DialFace com2 = new DialFace(4, Face.ATTACK_MELEE, FaceColor.RED);
        DialFace mod2 = new DialFace(Face.ENHANCE_MORALE, FaceColor.RED, 1);
        Command command2 = new Command(com2, mod2);

        cc.setCommand(0, command1);
        cc.setCommand(1, command2);

        return cc;
    }
}

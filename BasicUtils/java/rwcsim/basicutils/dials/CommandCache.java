package rwcsim.basicutils.dials;

public class CommandCache {
    public static final int MAX_COMMAND_DEPTH = 8;

    Command[] commands = new Command[MAX_COMMAND_DEPTH];

    public CommandCache(){}

    public void setCommand(int round, Command command) {
        commands[round] = command;
    }

    public Command getCommand(int round) {
        for (int r = round; r >= 0; r--) {
            if (null == commands[r]) {
                continue;
            }
            return commands[r];
        }
        return commands[round];
    }

    public void clearCache() {
        for (int i = 0; i<MAX_COMMAND_DEPTH; i++) {
            commands[i] = null;
        }
    }
}

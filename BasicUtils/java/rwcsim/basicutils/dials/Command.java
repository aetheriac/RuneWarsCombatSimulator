package rwcsim.basicutils.dials;

public class Command {
    DialFace command;
    DialFace modifier;

    public Command(DialFace command, DialFace modifier) {
        this.command = command;
        this.modifier = modifier;
    }

    public int getInitiative() {
        return command.getInitiative();
    }

    public DialFace getCommand() {
        return command;
    }

    public void setCommand(DialFace command) {
        this.command = command;
    }

    public DialFace getModifier() {
        return modifier;
    }

    public void setModifier(DialFace modifier) {
        this.modifier = modifier;
    }
}

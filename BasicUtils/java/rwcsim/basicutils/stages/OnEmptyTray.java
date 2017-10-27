package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class OnEmptyTray extends NullStage<OnEmptyTray> {
    public OnEmptyTray() {
        setKey(Stages.ON_EMPTY_TRAY);
    }
}

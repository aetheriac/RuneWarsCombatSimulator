package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;
import rwcsim.basicutils.dials.DialFace;

public class OnShift extends NullStage<OnShift> {
    public OnShift() {
        setKey(Stages.ON_SHIFT);
    }
}

package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class AfterCharge extends NullStage<AfterCharge> {
    public AfterCharge() {
        setKey(Stages.AFTER_CHARGE);
    }
}

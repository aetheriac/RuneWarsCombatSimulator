package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class AfterActivation extends NullStage<AfterActivation> {
    public AfterActivation() {
        setKey(Stages.AFTER_ACTIVATION);
    }
}

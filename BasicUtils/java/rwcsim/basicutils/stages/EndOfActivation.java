package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class EndOfActivation extends NullStage<EndOfActivation> {
    public EndOfActivation() {
        setKey(Stages.END_OF_ACTIVATION);
    }
}

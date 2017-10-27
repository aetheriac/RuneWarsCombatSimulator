package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class BeginningEndPhase extends NullStage<BeginningEndPhase> {
    public BeginningEndPhase() {
        setKey(Stages.BEGINNING_OF_END_PHASE);
    }
}

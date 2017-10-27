package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class AfterAttacking extends NullStage<AfterAttacking> {
    public AfterAttacking() {
        setKey(Stages.AFTER_ATTACKING);
    }
}

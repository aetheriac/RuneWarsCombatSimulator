package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class SpendHits extends NullStage<SpendHits> {
    public SpendHits() {
        setKey(Stages.SPEND_HITS);
    }
}
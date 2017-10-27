package rwcsim.basicutils.stages;

import rwcsim.basicutils.concepts.Stage;

public class Reconfigure extends NullStage<Reconfigure> {
    public Reconfigure() {
        setKey(Stages.RECONFIGURE);
    }
}
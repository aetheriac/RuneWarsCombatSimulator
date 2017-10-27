package rwcsim.basicutils.abilities;

import rwcsim.basicutils.runes.RuneFaces;
import rwcsim.basicutils.stages.EndOfActivation;

public class Regenerate extends NullAbility<Regenerate> implements EndOfActivation {
    public Regenerate(int v) {
        setValue(v);
        setKey(Abilities.REGENERATE);
    }
    public Regenerate(RuneFaces rf) {
        super(rf.ordinal());
        activateOther();
        setKey(Abilities.REGENERATE);
    }
}

package rwcsim.basicutils.modifiers;

import rwcsim.basicutils.concepts.Modifier;

public interface Exhaustable extends Modifier {
    public boolean isExhausted();
    public void exhaust();
    public void reset();
}

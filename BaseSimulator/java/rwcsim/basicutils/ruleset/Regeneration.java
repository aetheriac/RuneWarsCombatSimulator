package rwcsim.basicutils.ruleset;

import rwcsim.basicutils.concepts.Rule;
import rwcsim.basicutils.managers.RuleSetManager;

public class Regeneration implements Rule<Regeneration> {
    public static final String name = "REGENERATION";


    public String name() {return name;}

    public boolean isEnabled() {
        return RuleSetManager.isEnabled(name);
    }

    public Regeneration getRule() {
        return new Regeneration();
    }
}

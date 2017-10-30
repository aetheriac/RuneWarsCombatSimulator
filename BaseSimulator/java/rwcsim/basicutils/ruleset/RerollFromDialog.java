package rwcsim.basicutils.ruleset;

import rwcsim.basicutils.concepts.Rule;
import rwcsim.basicutils.managers.RuleSetManager;

public class RerollFromDialog implements Rule<RerollFromDialog> {
    public static final String name = "REROLL_FROM_DIALOG";


    public String name() {return name;}

    public boolean isEnabled() {
        return RuleSetManager.isEnabled(name);
    }

    public RerollFromDialog getRule() {
        return new RerollFromDialog();
    }
}

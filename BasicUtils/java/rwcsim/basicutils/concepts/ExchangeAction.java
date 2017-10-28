package rwcsim.basicutils.concepts;

import rwcsim.basicutils.ActionType;

public interface ExchangeAction {
    ActionType spend(ActionType action);
}

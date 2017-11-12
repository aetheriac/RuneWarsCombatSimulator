package rwcsim.factions.neutral.upgrades.music;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.upgrades.Music;

public class Trumpets implements Cost, Music {
    @Override
    public int price() {
        return 0;
    }

    @Override
    public String getUpgradeName() {
        return "Trumpets";
    }
}

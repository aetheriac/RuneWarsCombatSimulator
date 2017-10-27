package rwcsim.factions.neutral.upgrades.music;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.WhenEngagedEnemyIsDestroyed;
import rwcsim.basicutils.upgrades.Music;

public class TriumphantCry implements Cost, Music {
    @Override
    public int price() {
        return 3;
    }
}

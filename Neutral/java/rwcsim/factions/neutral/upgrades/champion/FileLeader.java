package rwcsim.factions.neutral.upgrades.champion;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.stages.AfterCharge;
import rwcsim.basicutils.upgrades.Champion;

public class FileLeader implements Cost, Champion {
    @Override
    public int price() {
        return 6;
    }
}

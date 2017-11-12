package rwcsim.factions.neutral.upgrades.heraldry;

import rwcsim.basicutils.concepts.Cost;
import rwcsim.basicutils.dials.FaceColor;
import rwcsim.basicutils.modifiers.InitiativeAdjustment;
import rwcsim.basicutils.upgrades.Heraldry;

public class RavenTabards implements Cost, Heraldry, InitiativeAdjustment {
    @Override
    public FaceColor affectedColor() {
        return FaceColor.BLUE;
    }

    @Override
    public int changeValue() {
        return -1;
    }

    @Override
    public int price() {
        return 2;
    }

    @Override
    public String getUpgradeName() {
        return "Raven Tabards";
    }
}

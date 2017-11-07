package rwcsim.basicutils.upgrades;

public interface Upgrade {
    default boolean isUpgrade() {
        return true;
    }
    default String getUpgradeName() { return null; }
}

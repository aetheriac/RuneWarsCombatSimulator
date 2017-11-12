package rwcsim.basicutils.concepts;

public interface Upgrade {
    default boolean isUpgrade() {
        return true;
    }
    String getUpgradeName(); // { return null; }
}

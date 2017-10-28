package rwcsim.basicutils.upgrades;

public enum UpgradeTypes {
    Artifact,
    Champion,
    Equipment,
    Heavy,
    Heraldry,
    Music,
    Training,
    Unique;

    private UpgradeTypes upgradeType;

    private UpgradeTypes() {}

//    public UpgradeSlot<?> getAsSlot(UpgradeTypes T) {
//        return (UpgradeSlot<T>) upgradeType;
//    }
}

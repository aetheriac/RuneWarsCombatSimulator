package rwcsim.basicutils.upgrades;

public enum UpgradeTypes {
    Artifact(rwcsim.basicutils.upgrades.Artifact.class),
    Champion(rwcsim.basicutils.upgrades.Champion.class),
    Equipment(rwcsim.basicutils.upgrades.Equipment.class),
    Heavy(rwcsim.basicutils.upgrades.Heavy.class),
    Heraldry(rwcsim.basicutils.upgrades.Heraldry.class),
    Music(rwcsim.basicutils.upgrades.Music.class),
    Training(rwcsim.basicutils.upgrades.Training.class),
    Unique(rwcsim.basicutils.upgrades.Unique.class);

    private Class clazz;
    private UpgradeTypes upgradeType;

    private UpgradeTypes(Class c) {
        this.clazz = c;
    }

    public Class getClazz() {
        return clazz;
    }
//    public UpgradeSlot<?> getAsSlot(UpgradeTypes T) {
//        return (UpgradeSlot<T>) upgradeType;
//    }
}

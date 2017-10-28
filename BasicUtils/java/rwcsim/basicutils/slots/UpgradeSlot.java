package rwcsim.basicutils.slots;

public enum UpgradeSlot implements UpgradeType {
    Artifact(rwcsim.basicutils.slots.Artifact.class),
    Champion(rwcsim.basicutils.slots.Champion.class),
    Equipment(rwcsim.basicutils.slots.Equipment.class),
    Heavy(rwcsim.basicutils.slots.Heavy.class),
    Heraldry(rwcsim.basicutils.slots.Heraldry.class),
    Music(rwcsim.basicutils.slots.Music.class),
    Training(rwcsim.basicutils.slots.Training.class),
    Unique(rwcsim.basicutils.slots.Unique.class);

    public final Class<?> clazz;

    UpgradeSlot(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class<?> getSlot() {
        return clazz;
    }
}

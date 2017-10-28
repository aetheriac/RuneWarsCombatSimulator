package rwcsim.basicutils.slots;


import rwcsim.basicutils.upgrades.UpgradeType;

public enum UpgradeSlot implements UpgradeType<?> {
    Artifact {
        @Override
        public Class<?> getSlot() {
            return null;
        }
    }
//    public static final UpgradeSlot<Artifact> Artifact = new UpgradeSlot<Artifact>() {
//        @Override
//        public Class<Artifact> getSlot() {
//            return Artifact.class;
//        }
//    };
//
//    public static final UpgradeSlot<Champion> Champion = new UpgradeSlot<Champion>() {
//        @Override
//        public Class<Champion> getSlot() {
//            return Champion.class;
//        }
//    };
//
//    public static final UpgradeSlot<Equipment> Equipment = new UpgradeSlot<Equipment>() {
//        @Override
//        public Class<Equipment> getSlot() {
//            return Equipment.class;
//        }
//    };
//
//    public static final UpgradeSlot<Heavy> Heavy = new UpgradeSlot<Heavy>() {
//        @Override
//        public Class<Heavy> getSlot() {
//            return Heavy.class;
//        }
//    };
//
//    public static final UpgradeSlot<Heraldry> Heraldry = new UpgradeSlot<Heraldry>() {
//        @Override
//        public Class<Heraldry> getSlot() {
//            return Heraldry.class;
//        }
//    };
//
//    public static final UpgradeSlot<Music> Music = new UpgradeSlot<Music>() {
//        @Override
//        public Class<Music> getSlot() {
//            return Music.class;
//        }
//    };
//
//    public static final UpgradeSlot<Training> Training = new UpgradeSlot<Training>() {
//        @Override
//        public Class<Training> getSlot() {
//            return Training.class;
//        }
//    };
//
//    public static final UpgradeSlot<Unique> Unique = new UpgradeSlot<Unique>() {
//        @Override
//        public Class<Unique> getSlot() {
//            return Unique.class;
//        }
//    };
//
//
//    private UpgradeSlot() {
//    }
}

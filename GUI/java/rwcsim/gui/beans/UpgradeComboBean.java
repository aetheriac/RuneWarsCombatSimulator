package rwcsim.gui.beans;

import rwcsim.basicutils.Factions;
import rwcsim.basicutils.upgrades.UpgradeTypes;

public class UpgradeComboBean {
    Factions faction;
    String[] artifacts;
    String[] champions;
    String[] equipment;
    String[] heavy;
    String[] heraldry;
    String[] music;
    String[] training;
    String[] unique;



    public String[] getUpgrades(Factions faction, UpgradeTypes type) {
        switch (type) {
            case Artifact:
                return this.artifacts;
            case Champion:
                return this.champions;
            case Equipment:
                return this.equipment;
            case Heavy:
                return this.heavy;
            case Heraldry:
                return this.heraldry;
            case Music:
                return this.music;
            case Training:
                return this.training;
            case Unique:
                return this.unique;
        }
        return null;
    }
}

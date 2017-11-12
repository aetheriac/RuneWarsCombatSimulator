package rwcsim.basicutils.concepts;

public interface EffectUpgrade extends Upgrade {
    default boolean isEffectUpgrade() {
        return true;
    }
}

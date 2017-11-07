package rwcsim.interactions;

public abstract class BaseInteractionManager implements InteractionManager {
    @Override
    public int modifyDamagePool(int damagePool) {
        return damagePool;
    }
}

package rwcsim.basicutils.figure;


import rwcsim.basicutils.concepts.Figure;
import rwcsim.basicutils.concepts.Tray;

public abstract class AbstractFigure implements Figure {
    public int currentHealth = 0;
    public int health;
    public int armor;

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    @Override
    public int applyDamage(int count) {
        int remainingHits = count;

        for (int i=0; i<getHealth();i++) {
            if (remainingHits>=this.getArmor()) {
                remainingHits -= this.getArmor();
                currentHealth--;
            } else {
                remainingHits=0;
            }
        }
        return remainingHits;
    }

    @Override
    public int applyMortalStrikes(int count) {
        int remainingStrikes = count;
        if (currentHealth>0) {
            if (remainingStrikes > currentHealth) {
                remainingStrikes -= currentHealth;
                currentHealth = 0;
            }

            if (currentHealth >= remainingStrikes) {
                currentHealth -= remainingStrikes;
                remainingStrikes = 0;
            }
        }
        return remainingStrikes;
    }

    @Override
    public int getArmor() { return armor;}
    @Override
    public int getHealth() { return health;}
}

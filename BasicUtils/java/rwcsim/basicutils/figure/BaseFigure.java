package rwcsim.basicutils.figure;


public class BaseFigure extends AbstractFigure {
    public BaseFigure(){}
    public BaseFigure(int armor, int health) {
        this.armor = armor;
        this.health = health;
        this.currentHealth = health;
    }

    public static class NullFigure extends BaseFigure {
        public NullFigure(){}
        public NullFigure(int armor, int health) {
            this.armor = armor;
            this.health = health;
            this.currentHealth = health;
        }
    }

//    @Override
//    public int getArmor() { return 1;}
//    @Override
//    public int getHealth() { return 1;}
//    @Override
//    public boolean replaceTray() {
//        return false;
//    }
//
//    @Override
//    public Tray getTray() {
//        return null;
//    }
}

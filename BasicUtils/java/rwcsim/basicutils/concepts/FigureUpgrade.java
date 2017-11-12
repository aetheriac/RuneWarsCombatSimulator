package rwcsim.basicutils.concepts;

public interface FigureUpgrade extends Figure, Upgrade {
    @Override
    default boolean isUpgrade() {
        return true;
    }
    Figure getFigure();


}

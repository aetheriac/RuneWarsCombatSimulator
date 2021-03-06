package rwcsim.basicutils.states;

import rwcsim.basicutils.concepts.State;
import rwcsim.basicutils.concepts.Unit;

public interface Flanking extends State<Flanking> {
    @Override
    default Flanking getState(Unit unit) { return new FlankingState(unit); }
}

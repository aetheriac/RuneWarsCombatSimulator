package rwcsim.basicutils.concepts;

public interface Stage<T> {
    int getKey();
    void setKey(int key);
//    Key<T> key();
//    default boolean hasStage(){return true;}
//    default T stageApplied() {return T.self();}
//    T self();
}

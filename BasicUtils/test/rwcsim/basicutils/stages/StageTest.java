//package rwcsim.basicutils.stages;
//
//import rwcsim.basicutils.concepts.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StageTest {
//
//    class One implements AfterActivation {}
//    class Two implements AfterAttacking {}
//    class Three implements AfterCharge {}
//
//    public void test() {
//
//        List<Stage.Key> list = new ArrayList<>();
//        list.add(new One().key());
//        list.add(new Two().key());
//        list.add(new Three().key());
//        list.add(new Three().key());
//
//        for (Object o : list) {
//            System.out.println(o);
//        }
//    }
//
//    public static void main(String[] args) {
//        StageTest st = new StageTest();
//        st.test();
//
//    }
//}

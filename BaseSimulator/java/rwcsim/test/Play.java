package rwcsim.test;

import java.util.LinkedList;
import java.util.List;

public class Play {
    public static void main(String[] args) {
        List list = new LinkedList();
        System.out.println(list.size());
        list.add(new Object());
        System.out.println(list.size());
        list.add(new Object());
        System.out.println(list.size());
        list.add(new Object());
        System.out.println(list.size());

        Object obj = new Object();
        list.add(obj);
        list.remove(2);
        System.out.println(list.size());
        list.remove(obj);
        System.out.println(list.size());
    }
}

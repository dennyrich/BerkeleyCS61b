import java.awt.*;

public class ArrayDequeTest {
    public static void main(String[] args) {
//        ArrayDeque<Integer> test = new ArrayDeque<>();
//
//        for (int i = 0; i <= 9; i ++) {
//            test.addLast(i);
//        }
//        test.printDeque();
//
//        System.out.println(test.removeLast() + " " + test.removeFirst());
//
//
//        System.out.println(test.removeLast());
//        test.printDeque();
//        System.out.println(test.removeLast());
//        System.out.println(test);
//        System.out.println(test.removeLast());
//        test.printDeque();
//        System.out.println(test.removeFirst());
//        System.out.println(test);
//
//
//        System.out.println("__________________________________");
//        ArrayDeque<Integer> test2 = new ArrayDeque<>();
//
//        test2.addFirst(0);
//        test2.addFirst(1);
//        System.out.println(test2);
//        System.out.println(test2.removeLast());
//        System.out.println(test2);
//        test2.printDeque();
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            test.addFirst(i);
        }
        for (int i = 99; i >= 0; i--) {
            if (i < 35) {
                System.out.println("---------------------------------------------");
                test.printDeque();
                System.out.println(test);
            }
            int val = test.removeFirst();
            if (val != i) {
                System.out.println("cheeseError" + val + " " + i);
            }
        }
        System.out.println("****************************************");
        ArrayDeque<Integer> testing = new ArrayDeque<>();




        testing.addLast(0);
        testing.addLast(1);
        testing.removeFirst();
        testing.addLast(3);
        testing.addLast(4);
        testing.addFirst(5);
        testing.removeFirst();
        testing.removeFirst();
        testing.removeFirst();
        testing.removeFirst();
        testing.addFirst(10);
        testing.removeLast();
        testing.addFirst(12);
        testing.get(0);


        System.out.println(testing);
        testing.printDeque();
        testing.addFirst(14);
        testing.printDeque();
        System.out.println(testing.removeLast());



        // now for the LinkedListDeque testing

        //linkTest();

    }
    public static void linkTest() {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();

        test.addFirst(4);
        test.printDeque();
        test.addFirst(3);
        test.addLast(5);

        LinkedListDeque<Integer> test2 = new LinkedListDeque<>(test);
        test2.printDeque();



    }
}

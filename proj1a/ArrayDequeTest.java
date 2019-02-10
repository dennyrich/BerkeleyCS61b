public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();

        for (int i = 0; i <= 9; i ++) {
            test.addLast(i);
        }
        test.printDeque();

        System.out.println(test.removeLast() + " " + test.removeFirst());


        System.out.println(test.removeLast());
        test.printDeque();
        System.out.println(test.removeLast());
        System.out.println(test);
        System.out.println(test.removeLast());
        test.printDeque();
        System.out.println(test.removeFirst());
        System.out.println(test);


        System.out.println("__________________________________");
        ArrayDeque<Integer> test2 = new ArrayDeque<>();

        test2.addFirst(0);
        test2.addFirst(1);
        System.out.println(test2);
        System.out.println(test2.removeLast());
        System.out.println(test2);
        test2.printDeque();



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

public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();

        for (int i = 0; i <= 9; i ++) {
            test.addLast(i);
        }
        test.printDeque();

        test.printRawItems();
        System.out.println(test.removeLast() + " " + test.removeFirst());
        test.printDeque();


        /*
        ArrayDeque<Integer> test2 = new ArrayDeque<>();

        test2.addFirst(0);
        test2.addFirst(1);
        test2.printRawItems();
        System.out.println(test2.removeLast());
        test2.printRawItems();
        test2.printDeque();
        */



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

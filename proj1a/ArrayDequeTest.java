public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();

        for (int i = 0; i <= 25; i ++) {
            test.addLast(i);
        }
        test.printDeque();

        for (int i = 0; i < 22; i ++) {
            test.removeFirst();
        }
        ArrayDeque<Integer> tester = new ArrayDeque(test);
        test.printDeque();
        tester.printDeque();
        System.out.println(test.get(2));
        System.out.println();

        // now for the LinkedListDeque testing

        linkTest();

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

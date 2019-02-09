public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        /*
        test.addFirst(1);
        test.addFirst(0);
        test.addLast(2);
        test.addLast(3);
        test.addFirst(-1);
        test.addFirst(-2);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.addFirst(-3);
        test.addFirst(-4);
        test.addLast(8);
        test.addLast(9);
        */
        for (int i = 0; i <= 25; i ++) {
            test.addLast(i);
        }
        test.printDeque();

        for (int i = 0; i < 22; i ++) {
            test.removeFirst();
        }

        test.printDeque();
        test.printNakedItems();
        System.out.println(test.get(2));


        System.out.println();

        LinkedListDeque<Integer> testing = new LinkedListDeque<>();
        testing.addFirst(2);
        testing.addFirst(1);
        testing.addFirst(0);
        System.out.println(testing.get(0) + " " + testing.get(1) + " " + testing.get(2));
        /*
        testing.printDeque();
        System.out.println(testing.removeFirst());
        System.out.println(testing.removeLast());
        testing.printDeque();
        */

    }
}

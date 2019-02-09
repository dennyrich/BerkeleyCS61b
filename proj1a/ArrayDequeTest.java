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
        System.out.println(test.get(2));


        System.out.println();

    }
}

import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        // copy and pasted from StudentArrayDequeLauncher -see @source
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String graderOutput = "";
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.1 && !sad1.isEmpty()) {
                graderOutput += "removeFirst()\n";
                assertEquals(graderOutput, sad1.removeFirst(), solution.removeFirst());
            } else if (numberBetweenZeroAndOne < 0.2 && !sad1.isEmpty()) {
                graderOutput += "removeLast()\n";
                assertEquals(graderOutput, sad1.removeLast(), solution.removeLast());
            }
            else if (numberBetweenZeroAndOne < 0.6) {
                graderOutput += "addFirst(" + i + ")\n";
                sad1.addFirst(i);
                solution.addFirst(i);
            }
            else {
                graderOutput += "addLast(" + i + ")\n";
                sad1.addLast(i);
                solution.addLast(i);
            }
            assertEquals(graderOutput, solution.size(), sad1.size());
            if (solution.size() > 0) {
                int index = (int) numberBetweenZeroAndOne * solution.size();
                assertEquals(graderOutput, solution.get(index), sad1.get(index));
            }
        }
        /*
        solution.printDeque();
        sad1.printDeque();
        assertEquals(solution.get(5), sad1.get(5));
        assertEquals(solution.size(), sad1.size());
        */
        while (!solution.isEmpty() && !sad1.isEmpty()) {
            assertEquals(solution.removeFirst(), sad1.removeFirst());
            assertEquals(solution.size(), sad1.size());
        }
    }
}

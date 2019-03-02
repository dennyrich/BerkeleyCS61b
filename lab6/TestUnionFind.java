import org.junit.Test;
import static org.junit.Assert.*;
public class TestUnionFind {
    @Test
    public void testUnionFind() {
        UnionFind test = new UnionFind(6);
        test.union(3,4);
        test.union(3, 5);
        System.out.println(test.find(5));
        System.out.println(test.find(3));
        assertEquals(3, test.sizeOf(5));
        assertEquals(3, test.find(5));
    }

}

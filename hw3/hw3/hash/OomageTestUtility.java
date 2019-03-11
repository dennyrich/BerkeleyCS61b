package hw3.hash;

//import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        int[] bucketSizes = new int[M];
        for (int i = 0; i < M; i++) {
            bucketSizes[i] = 0;
        }
        int N = oomages.size();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketSizes[bucketNum] += 1;
        }
        for (int s : bucketSizes) {
            if (s < N / 50 || s > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}

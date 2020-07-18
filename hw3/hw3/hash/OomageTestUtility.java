package hw3.hash;

import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        int N = oomages.size();
        double upperBound = (double) N / 2.5;
        double lowerBound = (double) N / 50;

        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (bucket[bucketNum] != 0) {
                bucket[bucketNum] += 1;
            } else {
                bucket[bucketNum] = 1;
            }
        }
        for (int n : bucket) {
            if (n > upperBound || N < lowerBound ) {
                return false;
            }
        }
        return true;
    }
}

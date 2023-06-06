import java.util.Collections; // can be useful
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class HashingExperimentUtils {
    final private static int k = 5;
    public static Pair<Double, Double> measureOperationsChained(double maxLoadFactor) {
        Random rand = new Random();
        int key = rand.nextInt();
        ModularHash intHash = new ModularHash();
        ChainedHashTable<Integer,Integer> tatum = new ChainedHashTable(intHash,k,maxLoadFactor);
        double searchTimes = 0;
        double insertTimes = 0;
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            key = rand.nextInt();
            nums.add(key);
        }
        Iterator<Integer> iter = nums.iterator();
        for (int i = 0; i < 50; i++) {
            key = iter.next();
            long insertStart = System.nanoTime();
            tatum.insert(key,key);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        iter = nums.iterator();
        for (int i = 0; i < 30; i++) {
            if (i<15) {
                key = iter.next();
                long searchStart = System.nanoTime();
                Integer res = tatum.search(key);
                searchTimes = searchTimes + (System.nanoTime() - searchStart);
                if (res == null) throw new RuntimeException("failed at point 2");
            } else {
                key = rand.nextInt();
                if (nums.contains(key)) {
                    i--;
                }
                else {
                    long searchStart = System.nanoTime();
                    Integer res = tatum.search(key);
                    searchTimes = searchTimes + (System.nanoTime() - searchStart);
                    if (res != null) throw new RuntimeException("failed at point 3");
                }
            }
        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/30 , insertTimes/30);
        return ans;
    }
    public static Pair<Double, Double> measureOperationsProbing(double maxLoadFactor) {
        ModularHash modularHash = new ModularHash();
        ProbingHashTable<Integer , Integer> probing = new ProbingHashTable(modularHash , k , maxLoadFactor);
        double insertTimes = 0;
        double searchTimes = 0;
        Random rand = new Random();
        int key = rand.nextInt();
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            key = rand.nextInt();
            nums.add(key);
        }
        Iterator<Integer> iter = nums.iterator();
        for (int i = 0; i < 30; i++) {
            key = iter.next();
            long insertStart = System.nanoTime();
            probing.insert(key,key);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        iter = nums.iterator();
        for (int i = 0; i < 30; i++) {
            if (i<15) {
                key = iter.next();
                long searchStart = System.nanoTime();
                Integer res = probing.search(key);
                searchTimes = searchTimes + (System.nanoTime() - searchStart);
                if (res == null) throw new RuntimeException("failed at point 2");
            } else {
                key = rand.nextInt();
                if (nums.contains(key)) {
                    i--;
                }
                else {
                    long searchStart = System.nanoTime();
                    Integer res = probing.search(key);
                    searchTimes = searchTimes + (System.nanoTime() - searchStart);
                    if (res != null) throw new RuntimeException("failed at point 3");
                }
            }
        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/30 , insertTimes/30);
        return ans;
    }
    public static Pair<Double, Double> measureLongOperations() {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }
    public static Pair<Double, Double> measureStringOperations() {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }

    public static void main(String[] args) {
        System.out.println("--------------1-------------");
        Pair chained = measureOperationsChained(1);
        System.out.println("search  -  " + chained.first());
        System.out.println("insert  -  " + chained.second());
    }
}

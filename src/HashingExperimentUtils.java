import java.util.*;

public class HashingExperimentUtils {
    final private static int k = 16;
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
        HashingUtils utils = new HashingUtils();
        MultiplicativeShiftingHash longHash = new MultiplicativeShiftingHash();
        ChainedHashTable<Long,Long> tatum = new ChainedHashTable(longHash,k,1);
        double searchTimes = 0;
        double insertTimes = 0;
        Long[]  nums = utils.genUniqueLong(10);

        for (int i = 0; i < 10; i++) {
            long insertStart = System.nanoTime();
            tatum.insert(nums[i],nums[i]);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        for (int i = 0; i < 10; i++) {
                long searchStart = System.nanoTime();
                Long res = tatum.search(nums[i]);
                searchTimes = searchTimes + (System.nanoTime() - searchStart);
                if (res == null) throw new RuntimeException("failed at point 2");

        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/10 , insertTimes/10);
        return ans;
    }
    public static Pair<Double, Double> measureStringOperations() {
        HashingUtils utils = new HashingUtils();
        StringHash longHash = new StringHash();
        ChainedHashTable<String,String> tatum = new ChainedHashTable(longHash,k,1);
        double searchTimes = 0;
        double insertTimes = 0;
        List strings = utils.genUniqueStrings(10,10, 20);
        Iterator<String> iter = strings.listIterator();
        for (int i = 0; i < 10; i++) {
            String Naomi = iter.next();
            long insertStart = System.nanoTime();
            tatum.insert(Naomi, Naomi);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        iter = strings.listIterator();
        for (int i = 0; i < 10; i++) {
            String Naomi = iter.next();
            long searchStart = System.nanoTime();
            String res = tatum.search(Naomi);
            searchTimes = searchTimes + (System.nanoTime() - searchStart);
            if (res == null) throw new RuntimeException("failed at point 2");

        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/10 , insertTimes/10);
        return ans;
    }

    public static void main(String[] args) {
        Pair mlo= measureLongOperations();
        System.out.println(mlo.first() + " = insert");
        System.out.println(mlo.second() + " = search");
        Pair mso= measureStringOperations();
        System.out.println(mso.first() + " = insert");
        System.out.println(mso.second() + " = search");
    }
}

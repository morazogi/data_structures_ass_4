import java.util.*;

public class HashingExperimentUtils {
    final private static int k = 16;
    public static Pair<Double, Double> measureOperationsChained(double maxLoadFactor) {
        HashingUtils utils = new HashingUtils();
        int key;
        ModularHash intHash = new ModularHash();
        ChainedHashTable<Integer,Integer> tatum = new ChainedHashTable(intHash,k,maxLoadFactor);
        double searchTimes = 0;
        double insertTimes = 0;
        Integer[] nums = utils.genUniqueIntegers((int) (2*Math.pow(2,k) * maxLoadFactor));
        for (int i = 0; i < nums.length/2 -1 ; i++) {
            key = nums[i];
            long insertStart = System.nanoTime();
            tatum.insert(key,key);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        for (int i = 0; i < nums.length/4; i++) {
            key = nums[i];
            long searchStart = System.nanoTime();
            Integer res = tatum.search(key);
            searchTimes = searchTimes + (System.nanoTime() - searchStart);
            if (res == null) throw new RuntimeException("failed at point 2");
        }
        for( int i = nums.length/2; i < (3/4)* nums.length; i++){
            key = nums[i];
            long searchStart = System.nanoTime();
            Integer res = tatum.search(key);
            searchTimes = searchTimes + (System.nanoTime() - searchStart);
            if (res == null) throw new RuntimeException("failed at point 2");
        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/(nums.length/2) , insertTimes/(nums.length/2));
        return ans;
    }
    public static Pair<Double, Double> measureOperationsProbing(double maxLoadFactor) {
        HashingUtils utils = new HashingUtils();
        ModularHash modularHash = new ModularHash();
        ProbingHashTable<Integer , Integer> probing = new ProbingHashTable(modularHash , k , maxLoadFactor);
        int key;
        double searchTimes = 0;
        double insertTimes = 0;
        Integer[] nums = utils.genUniqueIntegers((int) (2*Math.pow(2,k) * maxLoadFactor));
        for (int i = 0; i < nums.length/2; i++) {
            key = nums[i];
            long insertStart = System.nanoTime();
            probing.insert(key,key);
            insertTimes = insertTimes + (System.nanoTime() - insertStart);
        }
        for (int i = 0; i < nums.length/4; i++) {
            key = nums[i];
            long searchStart = System.nanoTime();
            Integer res = probing.search(key);
            searchTimes = searchTimes + (System.nanoTime() - searchStart);
            if (res == null) throw new RuntimeException("failed at point 2");
        }
        for( int i = nums.length/2; i < (3/4)* nums.length; i++){
            key = nums[i];
            long searchStart = System.nanoTime();
            Integer res = probing.search(key);
            searchTimes = searchTimes + (System.nanoTime() - searchStart);
            if (res == null) throw new RuntimeException("failed at point 2");
        }
        Pair<Double,Double> ans = new Pair<Double,Double>(searchTimes/(nums.length/2) , insertTimes/(nums.length/2));
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

        double avarageSearchChained = 0.0;
        double avarageInsertChained = 0.0;
//        for (int i = 0; i < 30; i++) {
//            Pair mlo= measureOperationsChained(0.5);
//            avarageSearchChained = avarageSearchChained + (double)mlo.second();
//            avarageInsertChained = avarageInsertChained + (double) mlo.first();
//        }
//        System.out.println("------------------0.5--------------");
//        System.out.println(avarageSearchChained/30  + " = search");
//        System.out.println(avarageInsertChained/30  + " = insert");
//
//        avarageSearchChained = 0.0;
//        avarageInsertChained = 0.0;
//        for (int i = 0; i < 30; i++) {
//            Pair mlo= measureOperationsChained(0.75);
//            avarageSearchChained = avarageSearchChained + (double)mlo.second();
//            avarageInsertChained = avarageInsertChained + (double) mlo.first();
//        }
//        System.out.println("------------------0.75--------------");
//        System.out.println(avarageSearchChained/30  + " = search");
//        System.out.println(avarageInsertChained/30  + " = insert");

        avarageSearchChained = 0.0;
        avarageInsertChained = 0.0;
        for (int i = 0; i < 30; i++) {
            Pair mlo= measureOperationsChained(1);
            avarageSearchChained = avarageSearchChained + (double)mlo.second();
            avarageInsertChained = avarageInsertChained + (double) mlo.first();
        }
        System.out.println("------------------1--------------");
        System.out.println(avarageSearchChained/30  + " = search");
        System.out.println(avarageInsertChained/30  + " = insert");

        avarageSearchChained = 0.0;
        avarageInsertChained = 0.0;
        for (int i = 0; i < 30; i++) {
            Pair mlo= measureOperationsChained(1.5);
            avarageSearchChained = avarageSearchChained + (double)mlo.second();
            avarageInsertChained = avarageInsertChained + (double) mlo.first();
        }
        System.out.println("------------------1.5--------------");
        System.out.println(avarageSearchChained/30  + " = search");
        System.out.println(avarageInsertChained/30  + " = insert");

        avarageSearchChained = 0.0;
        avarageInsertChained = 0.0;
        for (int i = 0; i < 30; i++) {
            Pair mlo= measureOperationsChained(2);
            avarageSearchChained = avarageSearchChained + (double)mlo.second();
            avarageInsertChained = avarageInsertChained + (double) mlo.first();
        }
        System.out.println("------------------2--------------");
        System.out.println(avarageSearchChained/30  + " = search");
        System.out.println(avarageInsertChained/30  + " = insert");


        /*
        import java.util.Collections; // can be useful
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

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
        for (int i = 0; i < 30; i++) {
            key = rand.nextInt();
            nums.add(key);
        }
        Iterator<Integer> iter = nums.iterator();
        for (int i = 0; i < 30; i++) {
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
        System.out.println("--------------0.5-------------");
        Pair probed = measureOperationsProbing(0.5);
        System.out.println("search  -  " + probed.first());
        System.out.println("insert  -  " + probed.second());
        System.out.println("--------------0.75-------------");
         probed = measureOperationsProbing(0.75);
        System.out.println("search  -  " + probed.first());
        System.out.println("insert  -  " + probed.second());
        System.out.println("--------------0.875-------------");
         probed = measureOperationsProbing(0.875);
        System.out.println("search  -  " + probed.first());
        System.out.println("insert  -  " + probed.second());
        System.out.println("--------------0.9375-------------");
        probed = measureOperationsProbing(0.9375);
        System.out.println("search  -  " + probed.first());
        System.out.println("insert  -  " + probed.second());


    }
}

         */
    }
}

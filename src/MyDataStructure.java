import java.util.*;

public class MyDataStructure {
    /*
     * You may add any members that you wish to add.
     * Remember that all the data-structures you use must be YOUR implementations,
     * except for the List and its implementation for the operation Range(low, high).
     */

    /***
     * This function is the Init function described in Part 4.
     *
     * @param N The maximal number of items expected in the DS.
     */
    private TreeSet<Integer> treeSet;
    private HashMap<Integer, Integer> hashMap;
    public MyDataStructure(int N) {
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>(N);
    }
    /*
     * In the following functions,
     * you should REMOVE the place-holder return statements.
     */
    public boolean insert(int val) {
        if (hashMap.containsKey(val))
            return false;

        treeSet.add(val);
        hashMap.put(val, 1);
        return true;
    }

    public boolean delete(int val) {
        if (!hashMap.containsKey(val))
            return false;

        treeSet.remove(val);
        hashMap.remove(val);
        return true;
    }

    public boolean contains(int val) {
        return hashMap.containsKey(val);
    }

    public int rank(int val) {
        return treeSet.headSet(val, true).size();
    }


    public int select(int index) {
        if (index < 1 || index > treeSet.size())
            throw new IllegalArgumentException("Invalid index");

        Iterator<Integer> iterator = treeSet.iterator();
        int count = 0;
        int result = 0;

        while (iterator.hasNext()) {
            count++;
            result = iterator.next();

            if (count == index)
                break;
        }

        return result;
    }

    public List<Integer> range(int low, int high) {
        SortedSet<Integer> subset = treeSet.subSet(low, true, high, true);
        return new ArrayList<>(subset);
    }

}

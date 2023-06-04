import java.util.*;

public class ProbingHashTable<K, V> implements HashTable<K, V> {
    final static int DEFAULT_INIT_CAPACITY = 4;
    final static double DEFAULT_MAX_LOAD_FACTOR = 0.75;
    final private HashFactory<K> hashFactory;
    final private double maxLoadFactor;
    private int capacity;
    private HashFunctor<K> hashFunc;
    private Pair<K,V>[] hash;
    private int size;
    /*
     * You should add additional private members as needed.
     */
    public ProbingHashTable(HashFactory<K> hashFactory) {
        this(hashFactory, DEFAULT_INIT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }
    public ProbingHashTable(HashFactory<K> hashFactory, int k, double maxLoadFactor) {
        this.hashFactory = hashFactory;
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = 1 << k;
        this.hashFunc = hashFactory.pickHash(k);
        this.size = 0;
        this.hash = new Pair[capacity];
    }
    public V search(K key) {
        V value = null;
        int locationInHash = this.hashFunc.hash(key);

    }
    public void insert(K key, V value) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }
    public boolean delete(K key) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }
    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }
    public int capacity() { return capacity; }
}

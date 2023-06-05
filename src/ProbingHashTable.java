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
    private HashingUtils utils;
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
        this.utils = new HashingUtils();
    }
    public V search(K key) {
        V value = null;
        int locationInHash = this.hashFunc.hash(key);
        if (hash[locationInHash]==null) return null;
        else
        {
            while (hash[locationInHash].second()==null
                    &&hash[locationInHash].first()!=key){
                locationInHash = utils.mod(locationInHash,capacity());
                if (hash[locationInHash]==null) return null;
            }
            value = hash[locationInHash].second();
        }
        return value;
    }
    public void insert(K key, V value) {
        Pair add = new Pair(key, value);
        int locationInHash = this.hashFunc.hash(key);
        if (hash[locationInHash] == null) hash[locationInHash] = add;
        else {
            while (hash[locationInHash] != null) {
                locationInHash = utils.mod(locationInHash, capacity());
                if (hash[locationInHash] == null) hash[locationInHash] = add;
            }
        }
        size++;
        this.rehash();
    }
    public void rehash(){
        ProbingHashTable rehashed = new ProbingHashTable(hashFactory,(int) (Math.log(capacity)+1), maxLoadFactor);
        for (int i = 0; i < hash.length; i++) {
            if (hash[i]!=null && hash[i].second()!=null){
                rehashed.insert(hash[i].first(),hash[i].second());
            }
        }
        this.capacity = rehashed.capacity();
        this.hashFunc = rehashed.getHashFunc();
        this.hash = rehashed.hash;
    }
    public boolean delete(K key) {
        int locationInHash = this.hashFunc.hash(key);
        if (hash[locationInHash]==null) return false;
        else
        {
            while (hash[locationInHash].second()==null
                    &&hash[locationInHash].first()!=key){
                locationInHash = utils.mod(locationInHash,capacity());
                if (hash[locationInHash]==null) return false;
            }
            hash[locationInHash] = new Pair<>(key,null);
        }
        return true;
    }
    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }
    public int capacity() { return capacity; }
}

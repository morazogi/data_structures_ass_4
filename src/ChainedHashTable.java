import java.util.*;

public class ChainedHashTable<K, V> implements HashTable<K, V> {
    final static int DEFAULT_INIT_CAPACITY = 4;
    final static double DEFAULT_MAX_LOAD_FACTOR = 2;
    final private HashFactory<K> hashFactory;
    final private double maxLoadFactor;
    private int capacity;
    private HashFunctor<K> hashFunc;
    private LinkedList<Pair<K,V>>[] hash;
    private int size;
    private int k;
    /*
     * You should add additional private members as needed.
     */
    public ChainedHashTable(HashFactory<K> hashFactory) {
        this(hashFactory, DEFAULT_INIT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }
    public ChainedHashTable(HashFactory<K> hashFactory, int k, double maxLoadFactor) {
        this.hashFactory = hashFactory;
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = 1 << k;
        this.hashFunc = hashFactory.pickHash(k);
        this.size = 0;
        this.hash = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hash[i] = new LinkedList<>();
        }
        this.k = k;
    }
    public V search(K key) {
        V value = null;
        int locationInHash = this.hashFunc.hash(key);
        if (hash[locationInHash].isEmpty()) return null;
        else
        {
            Iterator<Pair<K,V>> iter = hash[locationInHash].iterator();
            boolean stop = false;
            while(iter.hasNext()&!stop){
                Pair<K,V> pair = iter.next();
                if (key.equals(pair.first())){
                    stop = true;
                    value = pair.second();
                }
            }
        }
        return value;
    }
    public void insert(K key, V value) {
        int locationInHash = this.hashFunc.hash(key);
        Pair<K,V> add = new Pair<>(key,value);
        if (search(key)==null) {
            hash[locationInHash].addLast(add);
            this.size++;
        }
        else throw new RuntimeException("item already in the DS");
        //
        //to implement rehashing
        //
        boolean rehash = (size/capacity())>=maxLoadFactor;
        if (rehash){
            this.rehash();
        }
    }
    private void rehash(){
        ChainedHashTable rehashed = new ChainedHashTable(hashFactory,k+1, maxLoadFactor);
        for (int i = 0; i < hash.length; i++) {
            Iterator<Pair<K,V>> iter = hash[i].iterator();
            boolean stop = false;
            while(iter.hasNext()&!stop){
                Pair<K,V> pair = iter.next();
                rehashed.insert(pair.first(),pair.second());
            }
        }
        this.capacity = rehashed.capacity();
        this.hashFunc = rehashed.getHashFunc();
        this.hash = rehashed.hash;
    }
    public boolean delete(K key) {
        int locationInHash = this.hashFunc.hash(key);
        if (hash[locationInHash].isEmpty()) return false;
        else
        {
            Iterator<Pair<K,V>> iter = hash[locationInHash].iterator();
            boolean stop = false;
            while(iter.hasNext()&!stop){
                Pair<K,V> pair = iter.next();
                if (key==pair.first()){
                    stop = true;
                    hash[locationInHash].remove(pair);
                }
            }
        }
        size--;
        return true;
    }
    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }
    public int capacity() { return capacity; }
}

public class IndexableSkipList extends AbstractSkipList {
    final protected double probability;
    public IndexableSkipList(double probability) {
        super();
        this.probability = probability;
    }

    @Override
    public Node find(int key) {
        Node current =this.head;
        int is_key_in_level = current.height();
        while (key >= current.key() && is_key_in_level>0){
            if (current.getNext(is_key_in_level).key()>key || current.getNext(is_key_in_level) == tail){
                is_key_in_level--;
            } else if (current.getNext(is_key_in_level).key()<key) {
                current = current.getNext(is_key_in_level);
            } else {
                return current;
            }
        }
        return current;
    }

    @Override
    public int generateHeight() {
        double rand = Math.random();
        int ans = 1;
        while (rand<probability){
            ans++;
            rand = Math.random();
        }
        return ans;
//        throw new UnsupportedOperationException("Replace this by your implementation");
    }

    public int rank(int val) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }

    public int select(int index) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }
}

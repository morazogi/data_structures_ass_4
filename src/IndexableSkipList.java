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
                return current.getNext(is_key_in_level);
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

    }

    public int rank(int val) {
        int rank = 0;
        Node current =this.head;
        int level = current.height();
        while (val >= current.key() && level>0){
            if (current.getNext(level) == tail || current.getNext(level).key()>val){
                level--;
            } else if (current.getNext(level).key()<val) {
                current = current.getNext(level);
                rank += current.distance_skipped[level];
            } else {
                return rank;
            }
        }
        return -1;
    }

    public int select(int index) {
        int rank = 0;
        Node current =this.head;
        int level = current.height();
        while (rank<index){
            if (current.getNext(level) == tail || rank + current.getNext(level).distance_skipped[level]>index){
                level--;
            } else if (rank + current.getNext(level).distance_skipped[level]<index) {
                current = current.getNext(level);
                rank += current.distance_skipped[level];
            } else {
                return current.getNext(level).key();
            }
        }
        return -1;
    }
}

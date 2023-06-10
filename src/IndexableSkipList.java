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
        Node item = search(val);
        return item.distance_skipped[item.height()];
//        Node current = find(val);
//        if (current.key() == val) {
//            int rank = 0;
//            for (int i = 0; i <= current.height(); i++) {
//                Node prev = current.getPrev(i);
//                while (prev != head) {
//                    rank++;
//                    prev = prev.getPrev(i);
//                }
//            }
//            return rank;
//        }
//        return -1;
    }

    public int select(int index) {
        int count = -1;
        for (int i = 0; i <= head.height(); i++) {
            Node current = head.getNext(i);
            while (current != tail) {
                count++;
                if (count == index) {
                    return current.key();
                }
                current = current.getNext(i);
            }
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        IndexableSkipList test = new IndexableSkipList(0.5);
        test.insert(50);
        test.insert(40);
        test.insert(30);
        test.insert(20);
        test.insert(10);
    }

}
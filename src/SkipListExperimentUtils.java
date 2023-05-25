public class SkipListExperimentUtils {
    public static double measureLevels(double p, int x) {
        IndexableSkipList test = new IndexableSkipList(p);
        int[] exs = new int[x];
        for (int i = 0 ; i < x; i++){
            exs [i] = test.generateHeight();
        }
        double sum = 0;
        for (int i = 0; i < x; i++) {
            sum += exs[i];
        }
        double ans = sum/ (double) x;
        return ans;
    }

    /*
     * The experiment should be performed according to these steps:
     * 1. Create the empty Data-Structure.
     * 2. Generate a randomly ordered list (or array) of items to insert.
     *
     * 3. Save the start time of the experiment (notice that you should not
     *    include the previous steps in the time measurement of this experiment).
     * 4. Perform the insertions according to the list/array from item 2.
     * 5. Save the end time of the experiment.
     *
     * 6. Return the DS and the difference between the times from 3 and 5.
     */
    public static Pair<AbstractSkipList, Double> measureInsertions(double p, int size) {
        IndexableSkipList test = new IndexableSkipList(p);
        Double timeElapsed = 0.0;
        int[] exs = new int[size];
        boolean[] ex = new boolean[size];
        for (int i = 0 ; i < size; i++) {
            exs[i] = i * 2;
            ex[i] = false;
        }
        for (int i = 0 ; i < size; i++){
            int x = (int) (Math.random()*size);
            while (ex[x]){
                x = (int) (Math.random()*size);
            }
            long start = System.nanoTime();
            test.insert(exs[x]);
            long finish = System.nanoTime();
            timeElapsed = timeElapsed + (double) (finish - start);
            ex[x] = true;
        }
        return new Pair<>(test, timeElapsed/size);
    }

    public static double measureSearch(AbstractSkipList skipList, int size) {
        double timeElapsed = 0.0;
        boolean[] ex = new boolean[size];
        for (int i = 0 ; i < size; i++) {
            ex[i] = false;
        }
        for (int i = 0 ; i < size; i++){
            int x = (int) (Math.random()*size);
            while (ex[x]){
                x = (int) (Math.random()*size);
            }
            long start = System.nanoTime();
            skipList.search(2*x);
            long finish = System.nanoTime();
            timeElapsed = timeElapsed + (double) (finish - start);
            ex[x] = true;
        }
        return timeElapsed/size;
    }

    public static double measureDeletions(AbstractSkipList skipList, int size) {
        double timeElapsed = 0;
        boolean[] ex = new boolean[size];
        for (int i = 0 ; i < size; i++) {
            ex[i] = false;
        }
        for (int i = 0 ; i < size; i++){
            int x = (int) (Math.random()*size);
            while (ex[x]){
                x = (int) (Math.random()*size);
            }
            IndexableSkipList.Node node_to_remove = skipList.search(2*x);
            long start = System.nanoTime();
            skipList.delete(node_to_remove);
            timeElapsed = timeElapsed + (System.nanoTime()-start);
            ex[x] = true;
        }
        return timeElapsed/size;
    }

    public static void main(String[] args) {


//-----------
        int x = 1000;
        double p = 0.33;
        double insert_expirement_sum =0;
        double search_expirement_sum =0;
        double deletion_expirement_sum =0;
        for (int i = 0; i < 30; i++) {
            Pair<AbstractSkipList,Double> res = measureInsertions(p, x);
            insert_expirement_sum+=res.second();
            search_expirement_sum+=measureSearch(res.first(), x);
            deletion_expirement_sum+=measureDeletions(res.first(), x);
        }
        System.out.println("insert - "+ (insert_expirement_sum/30));
        System.out.println("search - "+ (search_expirement_sum/30));
        System.out.println("deletion - "+ (deletion_expirement_sum/30));

    }
}

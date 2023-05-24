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
        long start = System.currentTimeMillis();
        AbstractSkipList test = new IndexableSkipList(p);
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
            test.insert(exs[x]);
        }
        long finish = System.currentTimeMillis();
        Double timeElapsed = (double) (finish - start);
        Pair<AbstractSkipList, Double> ans = new Pair<AbstractSkipList, Double>(test,timeElapsed);
        return ans;
    }

    public static double measureSearch(AbstractSkipList skipList, int size) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }

    public static double measureDeletions(AbstractSkipList skipList, int size) {
        throw new UnsupportedOperationException("Replace this by your implementation");
    }

    public static void main(String[] args) {
//        double[] ans = new double[16];
//        double t = 0.75;
//        double ex = 0;
//        double temp1;
//        double temp2;
//        double temp3;
//        double temp4;
//        double temp5;
//        System.out.println(t+"*10 ---------------------");
//        temp1 = measureLevels(t,10);
//        System.out.print(temp1 +" - ");
//        ex = ex + temp1;
//        temp2 = measureLevels(t,10);
//        System.out.print(temp2 +" - ");
//        ex = ex + temp2;
//        temp3 = measureLevels(t,10);
//        System.out.print(temp3 +" - ");
//        ex = ex + temp3;
//        temp4 = measureLevels(t,10);
//        System.out.print(temp4 +" - ");
//        ex = ex + temp4;
//        temp5 = measureLevels(t,10);
//        System.out.println(temp5 +" - ");
//        ex = ex + temp5;
//        ex = ex*t;
//        System.out.println("second = "+ex);
//        double third =  ((ex-temp1) + (ex-temp2) + (ex-temp3) + (ex-temp4) + (ex-temp5));
//        third = third/5;
//        System.out.println("third = " + third);
//        ex = 0;
//
//        System.out.println(t+"*100 ---------------------");
//        temp1 = measureLevels(t,100);
//        System.out.print(temp1 +" - ");
//        ex = ex + temp1;
//        temp2 = measureLevels(t,100);
//        System.out.print(temp2 +" - ");
//        ex = ex + temp2;
//        temp3 = measureLevels(t,100);
//        System.out.print(temp3 +" - ");
//        ex = ex + temp3;
//        temp4 = measureLevels(t,100);
//        System.out.print(temp4 +" - ");
//        ex = ex + temp4;
//        temp5 = measureLevels(t,100);
//        System.out.println(temp5 +" - ");
//        ex = ex + temp5;
//        ex = ex*t;
//        System.out.println("second = "+ex);
//        third =  ((ex-temp1) + (ex-temp2) + (ex-temp3) + (ex-temp4) + (ex-temp5));
//        third = third/5;
//        System.out.println("third = " + third);
//        ex = 0;
//
//        System.out.println(t+"*1000 ---------------------");
//        temp1 = measureLevels(t,1000);
//        System.out.print(temp1 +" - ");
//        ex = ex + temp1;
//        temp2 = measureLevels(t,1000);
//        System.out.print(temp2 +" - ");
//        ex = ex + temp2;
//        temp3 = measureLevels(t,1000);
//        System.out.print(temp3 +" - ");
//        ex = ex + temp3;
//        temp4 = measureLevels(t,1000);
//        System.out.print(temp4 +" - ");
//        ex = ex + temp4;
//        temp5 = measureLevels(t,1000);
//        System.out.println(temp5 +" - ");
//        ex = ex + temp5;
//        double realex = ex*t;
//        System.out.println("second = "+realex);
//        third =  ((realex-temp1) + (realex-temp2) + (realex-temp3) + (realex-temp4) + (realex-temp5));
//        third = third/5;
//        System.out.println("third = " + third);
//        ex = 0;
//
//        System.out.println(t+"*10000 ---------------------");
//        temp1 = measureLevels(t,10000);
//        System.out.print(temp1 +" - ");
//        ex = ex + temp1;
//        temp2 = measureLevels(t,10000);
//        System.out.print(temp2 +" - ");
//        ex = ex + temp2;
//        temp3 = measureLevels(t,10000);
//        System.out.print(temp3 +" - ");
//        ex = ex + temp3;
//        temp4 = measureLevels(t,10000);
//        System.out.print(temp4 +" - ");
//        ex = ex + temp4;
//        temp5 = measureLevels(t,10000);
//        System.out.println(temp5 +" - ");
//        ex = ex + temp5;
//        ex = ex*t;
//        System.out.println("second = "+ex);
//        third =  ((ex-temp1) + (ex-temp2) + (ex-temp3) + (ex-temp4) + (ex-temp5));
//        third = third/5;
//        System.out.println("third = " + third);
//------------




//        System.out.println(measureLevels(0.5,10));
//        System.out.println(measureLevels(0.75,10));
//        System.out.println(measureLevels(0.9,10));

    }
}

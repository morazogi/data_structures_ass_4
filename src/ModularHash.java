import java.util.Random;

public class ModularHash implements HashFactory<Integer> {
    private HashingUtils utils ;
    public ModularHash() {
        this.utils = new HashingUtils();
    }

    @Override
    public HashFunctor<Integer> pickHash(int k) {
        Integer[] nums = utils.genUniqueIntegers(3);
        int a = nums[1];
        int b = nums[2];
        int m = nums[3];
        long p = utils.genLong(Integer.MAX_VALUE + 1, Long.MAX_VALUE);
        return new Functor(a,b,m,p);
    }

    public class Functor implements HashFunctor<Integer> {
        final private int a;
        final private int b;
        final private long p;
        final private int m;
        @Override
        public int hash(Integer key) {
            return (int) (utils.mod(utils.mod(a*key+b,p),m));
        }
        public Functor(int a, int b, int m, long p){
            this.a = a;
            this.b = b;
            this.m = m;
            this.p = p;
        }
        public int a() {
            return a;
        }

        public int b() {
            return b;
        }

        public long p() {
            return p;
        }

        public int m() {
            return m;
        }
    }
}

import java.util.Random;

public class MultiplicativeShiftingHash implements HashFactory<Long> {
    private HashingUtils utils;
    public MultiplicativeShiftingHash() {
        this.utils = new HashingUtils();
    }

    @Override
    public HashFunctor<Long> pickHash(int k) {
        long a = utils.genLong(2,Long.MAX_VALUE);
        return new Functor(a,k);
    }

    public class Functor implements HashFunctor<Long> {
        final public static long WORD_SIZE = 64;
        final private long a;
        final private long k;

        @Override
        public int hash(Long key) {
            return (int) (a*key)>>>(WORD_SIZE-k);
        }
        public long a() {
            return a;
        }
        public long k() {
            return k;
        }
        public Functor(long a, long k){
            this.a = a;
            this.k = k;
        }
    }
}

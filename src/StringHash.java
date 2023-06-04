import com.sun.jdi.IntegerType;

import java.util.Random;

public class StringHash implements HashFactory<String> {
    private HashingUtils utils;
    private Random random;
    public StringHash() {
        this.utils = new HashingUtils();
        this.random = new Random();
    }

    @Override
    public HashFunctor<String> pickHash(int k) {
        int q = random.nextInt(Integer.MAX_VALUE/2,Integer.MAX_VALUE);
        while (!utils.runMillerRabinTest(q, 10)) {
            q = random.nextInt(Integer.MAX_VALUE/2,Integer.MAX_VALUE);
        }
        int c = random.nextInt(2,q);
        return new Functor(c,q,k);
    }

    public class Functor implements HashFunctor<String> {
        final private HashFunctor<Integer> carterWegmanHash;
        final private int c;
        final private int q;
        public Functor(int c, int q, int k){
            this.c = c;
            this.q = q;
            this.carterWegmanHash = new ModularHash().pickHash(k);
        }
        @Override
        public int hash(String key) {
            int ans = 0;
            int k = key.length();
            for (int i = 1; i <= key.length(); i++) {
                int x = key.charAt(i);
                ans += utils.mod(x*(utils.mod((long) Math.pow(c,k-i) , q)), q);
            }
            ans = utils.mod(ans,q);
            return carterWegmanHash.hash(ans);
        }
        public int c() {
            return c;
        }
        public int q() {
            return q;
        }
        public HashFunctor carterWegmanHash() {
            return carterWegmanHash;
        }
    }
}

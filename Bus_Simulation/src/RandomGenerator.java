/**
 * Created by whr on 10/2/15.
 */

import java.util.Random;

// The code generate exponential distribution random number is from
// http://introcs.cs.princeton.edu/java/stdlib/StdRandom.java.html
public class RandomGenerator {
    private static Random random;    // pseudo-random number generator
    private static long seed;        // pseudo-random number generator seed

    // static initializer
    static {
        // this is how the seed was set in Java 1.4
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    // don't instantiate
    private RandomGenerator() { }

    public static double expRandom(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("Rate lambda must be positive");
        return -Math.log(1 - random.nextDouble()) / lambda;
    }
}

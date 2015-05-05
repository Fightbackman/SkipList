/**
 * File: RandomNumber.java
 *
 */
public class RandomNumber {

    final static int a = 16807;       // $7^5$
    final static int m = 2147483647;  // $2^31 - 1$
    final static int q = 127773;      // m / a
    final static int r = 2836;        // m % a

    private static int zn = (int)System.currentTimeMillis();
    private static int zn2 = (int)System.currentTimeMillis();

    public static void setSeed (int seed) {
        zn = seed;
    }

    protected static int randint2 () {
        zn2 = (a * zn2) % m;
        return zn2;
    }

    protected static int randint () {
        if (zn < 0) zn = -zn;
        int gamma = a * (zn % q) - r * (zn / q);

        if (gamma >= 0)
            zn = gamma;
        else zn = gamma + m;

        return zn;
    }
}

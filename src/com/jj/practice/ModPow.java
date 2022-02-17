package com.jj.practice;
// Exponentiation by squaring algorithm
// https://en.wikipedia.org/wiki/Exponentiation_by_squaring
// Time Complexity O(log(power))
public class ModPow {
    private static final long M =   1000000007;
    private ModPow() {}

    // negative power is not implemented
    public static long modPow(long base, long power) {
        if (base < 0 && power < 0) throw new IllegalArgumentException("base >= 0 && power >= 0");
        if (base <= 1) return base;
        long ans = 1;
        while (power > 0) {
            if ((power & 1) == 1) {
                ans *= base;
                ans %= M;
            }
            base *= base;
            base %= M;
            power /= 2;
        }
        return ans % M;
    }

    public static void main(String[] args) {
        System.out.println((long) Math.pow(3, 10) % M);
        System.out.println(modPow(3, 10));

        System.out.println((long) Math.pow(7, 11) % M);
        System.out.println(modPow(7, 11));
    }
}

package com.jj.practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/*************************************
 * Standard Formula:
 * F(n) = F(n-1) + F(n-2) when F(0) = 0, F(1) = 1
 *
 * Fast Doubling Formula:
 * F(2n)   = F(n) * (2F(n+1) - F(n)]
 * F(2n+1) = F(n)^2 + F(n+1)^2
 *************************************/

public class Fibonacci {
    private static BigInteger[] lookup;
    private static ArrayList<BigInteger> memo;

    // slowest implement O(2^n) TC, O(n) SC
    public static BigInteger fiboBasic(int n) {
        if (n <= 1) return BigInteger.valueOf(n);
        return fiboBasic(n-1).add(fiboBasic(n-2));
    }

    // Bottom-up Approach with Tabulation
    public static BigInteger fiboMemoBU(int n) {
        lookup = new BigInteger[n+1];
        lookup[0] = BigInteger.ZERO;
        lookup[1] = BigInteger.ONE;
        for(int i = 2; i <= n; i++)
            lookup[i] = lookup[i-1].add(lookup[i-2]);
        return lookup[n];
    }

    // Top-Down Approach using Memorization O(n) TC, O(n) SC
    public static BigInteger fiboMemoTD(int n) {
        lookup = new BigInteger[n+1];
        lookup[0] = BigInteger.ZERO;
        lookup[1] = BigInteger.ONE;
        return fiboMemoTDCalled(n);
    }
    private static BigInteger fiboMemoTDCalled(int n) {
        if (lookup[n] != null)  return lookup[n];
        lookup[n] = fiboMemoTDCalled(n-1).add(fiboMemoTDCalled(n-2));
        return lookup[n];
    }

    // bottom up iterative approach O(N) TC, O(1) SC
    public static BigInteger fiboStandard(int n) {
        BigInteger prev2 = BigInteger.ZERO;     // f(n-2)
        BigInteger prev1 = BigInteger.ONE;      // f(n-1)
        for (int i = 0; i < n; i++) {
            BigInteger ans = prev2.add(prev1);
            prev2 = prev1;
            prev1 = ans;
        }
        return prev2;
        /*  without BigInteger Class
        long a = 0, b = 1;
        for (int i =0; i < n; i++) {
            long c = a+b;
            a= b;
            b= c;
        }
        return a;   */
    }

    // Fast Doubling Approach O(logn) TC, O(1) SC
    // F(2n)   = F(n) * [2F(n+1) - F(n)]
    // F(2n+1) = F(n)^2 + F(n+1)^2
    public static BigInteger fastFibonacci(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        int m = 0;
        for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
            // Loop invariant: a = F(m), b = F(m+1)

            // Double it
            BigInteger even = a.multiply(b.shiftLeft(1).subtract(a));
            BigInteger odd = a.multiply(a).add(b.multiply(b));
            a = even;
            b = odd;
            m *= 2;

            //System.out.println("m: " +m + " bit: " + bit);
            // Advance by one conditionally
            if ((n & bit) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
                m++;
            }
            //System.out.println("m: " +m + " bit: " + bit);
        }
        return a;
    }

/*    public static BigInteger fastFiboMemo(int n) {
        if (memo == null) memo = new ArrayList(n+1);
        memo.add(0, BigInteger.ZERO);
        memo.add(1, BigInteger.ONE);
        long a = 0, b = 1;
        int m = 0;

        if (memo[n] != null)   return memo[n];
        for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
            m *= 2;
            if (memo[m] == 0) {
                memo[m] = a * ((b << 1) - a);
            }
            if (memo[m+1] == 0) {
                memo[m+1] = a * a + b * b;
            }
            a = memo[m];
            b = memo[m+1];

            if ((n & bit) != 0) {
                if(memo[++m +1] == 0)  memo[m+1] = a + b;
                a = memo[m];
                b = memo[m+1];
            }
        }
        return a;
    }*/

    public static void main(String[] args) {
        System.out.println(fastFibonacci(44));

    }
}

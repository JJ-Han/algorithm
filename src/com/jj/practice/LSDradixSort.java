package com.jj.practice;

import java.util.Arrays;

public class LSDradixSort {
    private static final int BITS_PER_BYTES = 8;

    private LSDradixSort() {}

    // In case of String, LSD works only if the array contains same length of String
    // for various length of Strings, use MSD Radix Sort
    public static void radixSort(String[] a, int w) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];
        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R+1];
            for (String s : a)
                count[s.charAt(d)+1]++;

            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            for (String s : a)
                aux[count[s.charAt(d)]++] = s;

            System.arraycopy(aux, 0, a, 0, n);
        }
    }

    public static void radixSort(int[] a) {
        final int BITS = 32;
        final int R = 1 << BITS_PER_BYTES;
        final int MASK = R-1;                   // 0xFF
        final int w = BITS / BITS_PER_BYTES;

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {
            int[] count = new int[R+1];
            for (int x : a) {
                int c = (x >> BITS_PER_BYTES * d) & MASK;
                count[c + 1]++;
            }

            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            if (d == w-1) {                     // handle negative integers (0x80-0xFF comes before 0x00-0x7F)
                int neg = count[R] - count[R/2];     // # of negatives
                int pos = count[R/2];                // # of positives
                for (int r = 0; r < R/2; r++)
                    count[r] += neg;
                for (int r = R/2; r < R; r++)
                    count[r] -= pos;
            }

            for (int x : a) {
                int c = (x >> BITS_PER_BYTES * d) & MASK;
                aux[count[c]++] = x;
            }

            System.arraycopy(aux, 0, a, 0, n);
        }
    }

    // test
    public static void main(String[] args) {
        String[] test = new String[15];
        int w = 2;
        for (int i = 0; i < test.length; i++)
            test[i] = randomStr(w);

        System.out.println(Arrays.toString(test));
        radixSort(test, w);
        System.out.println(Arrays.toString(test));
    }

    // test utility
    private static String randomStr(int w) {
        StringBuilder sb = new StringBuilder(w);
        while (w-- > 0)
            sb.append(randomChar());
        return sb.toString();
    }

    private static char randomChar() {
        return (char) ((int) (Math.random() * 26) + 'a');
    }
}

package com.jj.practice;
// https://www.geeksforgeeks.org/print-all-permutations-of-a-string-with-duplicates-allowed-in-input-string/
// https://www.geeksforgeeks.org/distinct-permutations-string-set-2/
// https://practice.geeksforgeeks.org/problems/permutations-of-a-given-string/0
// https://leetcode.com/problems/permutations-ii/
import java.util.Arrays;

public class Permutation {
    public static void main(String[] args) {
        String str = "abca";
        permutation(str);
    }

    // sort first and start backtracking
    // nlogn + n^2 n!
    // if sort after backtracking TC becomes n^2 n! + n!log n!
    public static void permutation(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        String strSorted = new String(charArr);
        permutation("", strSorted);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n <= 1) System.out.println(prefix + str);
        else
            for (int i = 0; i < n; i++) {
                if (i > 0 && str.charAt(i) == str.charAt(i - 1)) continue;
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
    }
}

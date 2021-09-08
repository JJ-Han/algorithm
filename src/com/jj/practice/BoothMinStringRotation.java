package com.jj.practice;

// https://en.wikipedia.org/wiki/Lexicographically_minimal_string_rotation#cite_note-2
// Booth's Algorithm for Lexicographically minimal string rotation

import java.util.Arrays;

public class BoothMinStringRotation {
    private BoothMinStringRotation() {}

    public static String BoothMinStringRotation(String s) {
        int n = s.length();

        char[] t = new char[n*2];
        for (int i = 0; i < n; i++)
            t[i] = t[i + n] = s.charAt(i);

        int[] next = new int[n*2];
        Arrays.fill(next, -1);
        int k = 0;
        for (int i = 1; i < t.length; i++) {
            char c = t[i];
            int j = next[i - k - 1];
            while (j != -1 && c != t[k + j + 1]) {
                if (c < t[k + j + 1]) k = i - j - 1;
                j = next[j];
            }
            if (c != t[k + j + 1]) {
                if (c < t[k]) k = i;
                next[i - k] = -1;
            }
            else next[i - k] = j + 1;
        }
        return String.valueOf(t, k, n);
    }

    public static void main(String[] args) {
        String s = "bbaaccaadd";
        System.out.println(BoothMinStringRotation(s));
    }
}

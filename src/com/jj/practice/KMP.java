package com.jj.practice;

// https://algs4.cs.princeton.edu/53substring/KMPplus.java.html
// https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
// https://bowbowbow.tistory.com/6

import java.util.ArrayList;

public class KMP {
    private String pattern;
    private int[] next;
    private int m;

    public KMP(String pattern) {
        this.pattern = pattern;
        m = pattern.length();
        next = new int[m+1];
        int j = -1;
        for(int i = 0; i < m; i++) {
            if(i == 0)  next[i] = -1;
            else if(pattern.charAt(i) != pattern.charAt(j)) next[i] = j;
            else    next[i] = next[j];
            while(j >= 0 && pattern.charAt(i) != pattern.charAt(j))
                j = next[j];
            j++;
        }
        next[m] = j;
    }

    // return offset of first occurrence of text in pattern
    public int search(String text) {
        int n = text.length();
        int i, j;
        for(i = 0, j = 0; i < n && j < m; i++) {
            while(j >= 0 && text.charAt(i) != pattern.charAt(j))
                j = next[j];
            j++;
        }
        if(j == m)  return i - m;
        return -1;
    }

    // return all the offsets
    public ArrayList<Integer> searchAll(String text) {
        int n = text.length();
        int i, j;
        ArrayList<Integer> ret = new ArrayList<>();
        for(i = 0, j = 0; i < n; i++) {
            while(j >= 0 && text.charAt(i) != pattern.charAt(j))
                j = next[j];
            if(++j == m) {
                ret.add(i - m + 1);
                j = next[j];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String pattern = "AABAA";
        String text = "AABAABAAAAABAABAA";
        KMP tc = new KMP(pattern);
        System.out.println(tc.searchAll(text));
    }
}

package com.jj.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

// Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome. If not possible, return -1.
// O(nlogn) implement with Fenwick Tree
// https://www.codechef.com/problems/ENCD12
// https://leetcode.com/discuss/interview-question/351783/

public class MinSwapPalindrome {
    private class FenwickTree {
        private int[] ft;

        public FenwickTree(int size) {
            ft = new int[size+1];
        }

        public void update(int i) {
            i++;
            while (i < ft.length) {
                ft[i]++;
                i += i & -i;
            }
        }

        public int rsq(int i) {
            int sum = 0;
            i++;
            while (i > 0) {
                sum += ft[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        String s = "aabbcccc";
        char[] chars = s.toCharArray();
        MinSwapPalindrome test = new MinSwapPalindrome();
        for (int i = 0; i < 10; i++) {
            test.shuffle(chars);
            String shuffled = String.valueOf(chars);
            System.out.println(shuffled + " " + test.minSwapPalindrome(shuffled));
        }
    }

    public int minSwapPalindrome(String s) {
        Objects.requireNonNull(s);
        if (s.length() <= 1) return 0;
        char[] chars = s.toCharArray();
        Deque<Integer>[] index = new ArrayDeque[128];
        for (char c = 'a'; c <= 'z'; c++)
            index[c] = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++)
            index[chars[i]].add(i);

        int odd = 0, cnt = 0;
        for (char c = 'a'; c <= 'z'; c++)
            if (!index[c].isEmpty()) {
                if ((index[c].size() & 1) == 1 && ++odd > 1) return -1;
                cnt++;
            }
        if ((chars.length & 1) != odd) return -1;

        FenwickTree ft = new FenwickTree(chars.length);
        int lo = 0, hi = chars.length - 1, ans = 0;
        while (cnt > 1) {
            Deque<Integer> queue = index[chars[lo++]];
            if (!queue.isEmpty()) {
                int pos = queue.pollLast(), offset = ft.rsq(pos);
                if (queue.isEmpty()) ans += Math.abs(chars.length/2 - pos + offset);
                else {
                    queue.pollFirst();
                    ft.update(pos);
                    ans += Math.abs(hi-- - pos + offset);
                }
                if (queue.isEmpty()) cnt--;
            }
        }
        return ans;
    }

    public void shuffle(char[] a) {
        int i = a.length;
        while (i > 1) {
            int r = (int) (Math.random() * i--);
            exch(a, i, r);
        }
    }

    private void exch(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

package com.jj.practice;

// count number of sub-array which range(max-min) is less than k
// implement with monotone queue, Time Complexity O(n)

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfSubArrRangeLimit {
    public static void main(String[] args) {
        int n = 1000;
        int cnt = 0;
        for (int i = 0; i < 1000; i++) {
            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = (int) (Math.random() * 100) + 1;
            }
            int k = (int) (Math.random() * 100) + 1;
            int x = countSubArrMaxDiffWithinK(a, k);
            int y = bf(a, k);
            if (x != y) System.out.println("FAIL");
            else  {
                cnt++;
                System.out.println(x + " " + y);
            }
        }
        System.out.println(cnt);
    }
    public static int bf(int[] a, int k) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            int max = a[i], min = a[i];
            for (int j = i; j < a.length; j++) {
                max = Math.max(max, a[j]);
                min = Math.min(min, a[j]);
                if (max - min < k) ans++;
            }
        }
        return ans;
    }

    public static int countSubArrMaxDiffWithinK(int[] a, int k) {
        if (k <= 0) return 0;
        int n = a.length, ans = 0;
        Deque<Integer> maxs = new ArrayDeque<>(), mins = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (!mins.isEmpty() && mins.peekLast() > a[i]) mins.pollLast();
            while (!maxs.isEmpty() && maxs.peekLast() < a[i]) maxs.pollLast();
            maxs.offerLast(a[i]);
            mins.offerLast(a[i]);
            while (maxs.peekFirst() - mins.peekFirst() >= k) {
                if (maxs.peekFirst() == a[j]) maxs.pollFirst();
                if (mins.peekFirst() == a[j]) mins.pollFirst();
                j++;
            }
            ans += i - j + 1;
        }
        return ans;
    }
}

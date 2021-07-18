package com.jj.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class kSum {
    public List<List<Integer>> threeSum(int[] a, int target) {
        return kSum(a, target, 3);
    }

    public List<List<Integer>> fourSum(int[] a, int target) {
        return kSum(a, target, 4);
    }

    private List<List<Integer>> kSum(int[] a, long target, int k) {
        if (k < 2) throw new IllegalArgumentException("k should be greater than 1");

        List<List<Integer>> result = new ArrayList<>();
        if (k > a.length) return result;
        Arrays.sort(a);
        kSum(a, target, 0, 0, new int[k], result);
        return result;
    }

    private void kSum(int[] a, long target, int idx, int pos, int[] curr, List<List<Integer>> result) {
        if (pos == curr.length-2) twoSum(a, target, idx, curr, result);
        else {
            int limit = a.length - curr.length + pos;
            while (idx <= limit) {
                curr[pos] = a[idx];
                kSum(a, target - curr[pos], ++idx, pos+1, curr, result);
                while (idx <= limit && a[idx] == curr[pos]) idx++;
            }
        }
    }

    private void twoSum(int[] nums, long target, int lo, int[] curr, List<List<Integer>> result) {
        int hi = nums.length-1;
        while (lo < hi) {
            long cmp = target - nums[lo] - nums[hi];
            if      (cmp < 0)   hi--;
            else if (cmp > 0)   lo++;
            else {
                int x = nums[lo++], y = nums[hi--];
                curr[curr.length-2] = x;
                curr[curr.length-1] = y;
                result.add(asList(curr));
                while (lo < hi && nums[lo] == x) lo++;
                while (lo < hi && nums[hi] == y) hi--;
            }
        }
    }

    private List<Integer> asList(int[] a) {
        List<Integer> list = new ArrayList<>(a.length);
        for (int x : a)
            list.add(x);
        return list;
    }
}

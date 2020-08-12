package com.jj.practice;

// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class BinarySearchRotatedSortedArr {
    public static void main(String[] args) {
        System.out.println(search(new int[] {3, 5, 1}, 3));
        System.out.println(search(new int[] {3, 1}, 1));
        System.out.println(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search(new int[] {4, 5, 6, 7, 8, 1, 2, 3}, 8));
    }

    // search the target
    // if found, return the index, otherwise return -1
    public static int search(int[] nums, int t) {
        if(nums == null || nums.length == 0) return -1;
        int lo = 0, hi = nums.length-1;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmpMid = t - nums[mid], cmpHi = t - nums[hi];
            boolean isSorted = nums[hi] - nums[mid] > 0;
            if(cmpMid == 0) return mid;
            if((isSorted && cmpMid > 0 && cmpHi <= 0) ||
              (!isSorted && (cmpMid > 0 || cmpHi <= 0)))    lo = mid + 1;
            else                                            hi = mid - 1;
        }
        return -1;
    }
}

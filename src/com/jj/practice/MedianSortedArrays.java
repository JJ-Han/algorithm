package com.jj.practice;

// https://leetcode.com/problems/median-of-two-sorted-arrays/

public class MedianSortedArrays {
    public static void main(String[] args) {

    }

    // Time Complexity log(Min(m, n))
    public static double getMedian(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) return getMedian(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        boolean isOdd = ((n+m) & 1) == 1;
        int mid = (n+m+1)/2;
        // if(nums1[m-1] <= nums2[0]) {
        //     if(isOdd) return nums2[mid-m-1];
        //     int loM = mid < m+1 ? nums1[mid-1] : nums2[mid-m-1];
        //     return (double) (loM + nums2[mid-m]) /2;
        // }
        // if(nums2[n-1] <= nums1[0]) {
        //     if(isOdd) return nums2[mid-1];
        //     int hiM = mid < n ? nums2[mid] : nums1[mid-n];
        //     return (double) (nums2[mid-1] + hiM) /2;
        // }
        int lo = 0, hi = m;
        while(lo <= hi) {
            int i = lo + (hi-lo)/2;
            int j = mid - i;
            if      (i < hi && nums2[j-1] > nums1[i])   lo = i + 1;
            else if (i > lo && nums1[i-1] > nums2[j])   hi = i - 1;
            else {
                int maxL;
                if      (i == 0)    maxL = nums2[j-1];
                else if (j == 0)    maxL = nums1[i-1];
                else                maxL = Math.max(nums1[i-1], nums2[j-1]);
                if(isOdd)   return maxL;

                int minR;
                if      (i == m)    minR = nums2[j];
                else if (j == n)    minR = nums1[i];
                else                minR = Math.min(nums1[i], nums2[j]);
                return (double) (maxL + minR) /2;
            }
        }
        return -1.0;
    }
}

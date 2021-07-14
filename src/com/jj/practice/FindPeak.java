package com.jj.practice;

// constraint: no two adjacent cells are equal
// assume out of boundary as -INF

public class FindPeak {
    // O(logn)
    public static int findPeak(int[] nums) {
        int lo = 0, hi = nums.length-1, maxIdx = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) /2;
            if (mid > 0 && nums[mid-1] > nums[mid]) hi = mid-1;
            else if (mid < maxIdx && nums[mid] < nums[mid+1]) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    // O(m+n)
    public static int[] findPeak(int[][] mat) {
        int lo = 0, hi = mat.length-1, left = 0, right = mat[0].length-1;
        while (lo < hi || left < right) {
            int midR = lo + (hi - lo) /2, midC = left + (right - left) /2;
            int max = mat[midR][midC], maxR = midR, maxC = midC;
            for (int i = lo; i <= hi; i++)
                if (mat[i][midC] > max) {
                    max = mat[i][midC];
                    maxR = i;
                }

            for(int i = left; i <= right; i++) {
                if (mat[midR][i] > max) {
                    max = mat[midR][i];
                    maxR = midR;
                    maxC = i;
                }
            }

            int adjR = maxR, adjC = maxC;
            if (midR != maxR) {
                if (maxC > left && mat[maxR][maxC-1] > max) {
                    adjC = maxC-1;
                    max = mat[maxR][adjC];
                }
                if (maxC < right && mat[maxR][maxC+1] > max) {
                    adjC = maxC+1;
                    max = mat[maxR][adjC];
                }
            }
            else if (midC != maxC) {
                if (maxR > lo && mat[maxR-1][maxC] > max) {
                    adjR = maxR-1;
                    max = mat[adjR][maxC];
                }
                if (maxR < hi && mat[maxR+1][maxC] > max) {
                    adjR = maxR+1;
                    max = mat[adjR][maxC];
                }
            }

            if (adjR == midR || adjC == midC) return new int[] {maxR, maxC};
            if (adjR < midR) hi = midR - 1;
            else lo = midR + 1;

            if (adjC < midC) right = midC -1;
            else left = midC + 1;

        }
        return new int[] {lo, left};
    }
}

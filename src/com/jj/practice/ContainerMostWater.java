package com.jj.practice;

// https://leetcode.com/problems/container-with-most-water/

public class ContainerMostWater {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};     // ans 49
        System.out.println(maxArea(height));
        System.out.println(maxArea2(height));
    }

    // check and compare area at every index
    public static int maxArea(int[] a) {
        int i = 0, j = a.length-1, max = 0;
        while(i < j)
            if(a[i] > a[j])   max = Math.max(max, (j-i) * a[j--]);
            else              max = Math.max(max, (j-i) * a[i++]);
        return max;
    }

    // check area only when h(height) gets increased
    public static int maxArea2(int[] a) {
        int i = 0, j = a.length-1, max = 0;
        while(i < j) {
            int h = Math.min(a[i], a[j]);
            max = Math.max(max, h * (j-i));
            while(i < j && a[i] <= h)    i++;
            while(i < j && a[j] <= h)    j--;
        }
        return max;
    }
}

package com.jj.practice;

// https://www.geeksforgeeks.org/count-pairs-with-given-sum/
// https://practice.geeksforgeeks.org/problems/count-pairs-with-given-sum/0

/***********************************************************************************
 * Given an array of integers, and a number ‘sum’,
 * find the number of pairs of integers in the array whose sum is equal to ‘sum’.
 ***********************************************************************************/

import java.util.Arrays;
import java.util.HashMap;

public class CountPairs {
    static int countPairs(int[] a, int sum)  {
        Arrays.sort(a);
        int i = 0, j = a.length-1, count = 0;
        while (i < j)  {
            if      (a[i] + a[j] < sum) i++;
            else if (a[i] + a[j] > sum) j--;
            else    {
                int i2 = i, numLo = a[i];
                while (a[i] == numLo && i < j)
                    i++;

                int j2 = j, numHi = a[j];
                while (a[j] == numHi && j >= i)
                    j--;

                if (numLo == numHi) {
                    int countDup = j2 - i2;
                    count += (countDup * (countDup+1) / 2);
                }
                else {
                    count += (i - i2) * (j2 - j);
                }
            }
        }
        return count;
    }

    static int countPairsHM (int[] a, int sum) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = a.length;
        for (int key : a) {
            if (!hm.containsKey(key)) hm.put(key, 0);
            hm.put(key, hm.get(key) + 1);
        }

        int result = 0;
        for (int key : a) {
            if (hm.containsKey(sum - key)) {
                result += hm.get(sum - key);
                if (key == sum - key)   result--;
            }
        }
        return result/2;
    }

    public static void main(String[] args)  {
        int[] a = {1, 1, 1, 1};
        int sum = 2;
        //System.out.println(countPairs(a, sum));
        int[] b = {1, 2, 3, 4, 5, 6};
        int i = 4;
        System.out.println(b[b.length/2]);
    }
}
package com.jj.practice;

// https://www.geeksforgeeks.org/sum-of-bit-differences-among-all-pairs/
// https://practice.geeksforgeeks.org/problems/find-sum-of-different-corresponding-bits-for-all-pairs/0

/************************************************************************************************
 * Given an integer array of n integers, find sum of bit differences in all pairs               *
 * that can be formed from array elements. Bit difference of a pair (x, y) is count of          *
 * different bits at same positions in binary representations of x and y.                       *
 * For example, bit difference for 2 and 7 is 2.                                                *
 * Binary representation of 2 is 010 and 7 is 111 ( first and last bits differ in two numbers). *
 ***********************************************************************************************/

public class DiffBitSum {

    public static void main(String[] args)  {
        int[] test = {1, 3, 5};
        System.out.print(checkAllBits(test, test.length));
    }

    static int checkBit (int[] a, int b, int n)    {        // check each bit
        int countOFF = 0;
        for (int i : a)
            if ((i & (1 << b)) == 0)   countOFF++;
        return 2 * countOFF * (n-countOFF);
    }

    static int checkAllBits (int[] a, int n)    {          // sum up all the bit counts
        int sum = 0;
        for (int i = 0; i < 32; i++)  {
            sum += checkBit(a, i, n);
        }
        return sum;
    }
}


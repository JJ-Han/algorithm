package com.jj.practice;

// https://www.geeksforgeeks.org/find-the-missing-number/
// https://practice.geeksforgeeks.org/problems/missing-number-in-array/0

/****************************************************************************************
 * You are given a list of n-1 integers and these integers are in the range of 1 to n.  *
 * There are no duplicates in the list. One of the integers is missing in the list.     *
 * Write an efficient code to find the missing integer.                                 *
 ***************************************************************************************/

public class MissingNum {
    public static void main(String[] args)  {

    }

    static int getMissingNum (int[] a)   {
        int n = a.length, total = 1;
        for (int i = 2; i <= n+1; i++)   {
            total += i;
            total -= a[i-2];
        }
        return total;
    }
}
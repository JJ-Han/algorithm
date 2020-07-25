package com.jj.practice;

// https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
// https://practice.geeksforgeeks.org/problems/spirally-traversing-a-matrix/0
// https://leetcode.com/problems/spiral-matrix/solution/

public class Spiral2D {
    public static void main(String[] args) {
        int[][] nums = {    {1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12},
                            {13, 14, 15, 16}    };

        int[][] nums2 = {   {1, 2},
                {3, 4},
                {5, 6}  };
        spiralArr(nums);
    }
    public static void spiralArr(int[][] a) {
        int iLo = 0, iHi = a.length-1;
        int jLo = 0, jHi = a[0].length-1;
        while (iLo <= iHi && jLo <= jHi) {
            for (int j = jLo; j <= jHi; j++)    System.out.print(a[iLo][j] + " ");
            for (int i = iLo+1; i <= iHi; i++)  System.out.print(a[i][jHi] + " ");
            if (iLo < iHi && jLo < jHi)    {
                for (int j = jHi-1; j > jLo; j--)   System.out.print(a[iHi][j] + " ");
                for (int i = iHi; i > iLo; i--)     System.out.print(a[i][jLo] + " ");
            }
            iLo++;
            iHi--;
            jLo++;
            jHi--;
        }
    }
}

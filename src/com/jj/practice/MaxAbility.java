package com.jj.practice;

public class MaxAbility {
    private static final int MIN_ABILITY = -100001, MAX_ABILITY = 100001;




    public static int maxAbility(Integer[] a) {
        if (a.length < 3)   throw new IllegalArgumentException("array size is too small");
        if (a.length == 3)   return a[0] * a[1] * a[2];
        int max1 = MIN_ABILITY, max2 = MIN_ABILITY, max3 = MIN_ABILITY;
        int min1 = 0, min2 = 0;
        for (int i : a) {
            if (i >= 0) {
                if (i > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = i;
                }
                else if (i > max2) {
                    max3 = max2;
                    max2 = i;
                }
                else if (i > max3) {
                    max3 = i;
                }
            }
            else {
                if (i < min1) {
                    min2 = min1;
                    min1 = i;
                }
                else if (i < min2)  min2 = i;
            }

        }
        return Math.max(max1*max2*max3, min1*min2*max1);
    }
}

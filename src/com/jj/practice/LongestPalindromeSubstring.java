package com.jj.practice;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String s = "abccbaaa";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int lo = 0, hi = 0;
        for(int i = 0; i < chars.length && hi-lo < (chars.length-1-i) << 1 ; i++) {
            int len = Math.max(isPalindrome(chars, i, i), isPalindrome(chars, i, i+1));
            if(len > hi-lo) {
                lo = i - (len-1)/2;
                hi = i + len/2;
            }
        }
        return s.substring(lo, hi+1);
    }

    private static int isPalindrome(char[] s, int lo, int hi) {
        while(lo >= 0 && hi < s.length && s[lo] == s[hi]) {
            lo--;
            hi++;
        }
        return hi-lo-1;
    }

    // TODO: Manacher's Algorithm O(n)
}

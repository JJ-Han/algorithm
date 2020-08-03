package com.jj.practice;

public class Palindrome {
    public static void main(String[] args) {
        String s = "abcba";
        String s2 = "abccba";
        String s3 = "aB cde !@EDcb a";
        System.out.println(isPalin(s3));
    }
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        int n = s.length();
        for (int i = 0; i < (n/2); i++)
            if (s.charAt(i) != s.charAt(n-i-1))
                return false;
        return true;
    }

    public static boolean isPalin(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        int lo = 0, hi = s.length()-1;
        while(lo <= hi)
            if(s.charAt(lo++) != s.charAt(hi--))    return false;
        return true;
    }
}

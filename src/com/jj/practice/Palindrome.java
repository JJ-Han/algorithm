package com.jj.practice;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        int n = s.length();
        for (int i = 0; i < (n/2); i++)
            if (s.charAt(i) != s.charAt(n-i-1))
                return false;
        return true;
    }
}

package com.jj.practice;

// https://ict-nroo.tistory.com/18

public class StringObj {
    public static void main(String[] args) {
        String str1 = "test";                       // str1 and str2 points the same reference (String Constant Pool).
        String str2 = "test";
        String str3 = new String("test");   // 'new String()' creates object on Heap.
        String str4 = "testtest";
        String str5 = str1 + str2;                  // Concatenation also creates object on Heap.
        String str6 = str1.concat(str2);

        System.out.println(str4);
        System.out.println(str1 == str2);       // true
        System.out.println(str1 == str3);       // false

        System.out.println(str4 == str5);       // false
        System.out.println(str4 == str6);       // false
        System.out.println(str4.equals(str5));  // true
    }
}

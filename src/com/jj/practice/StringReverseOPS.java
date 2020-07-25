package com.jj.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReverseOPS {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            reverseStr(br.readLine());
        }
    }

    public static void reverseStr(String s) {       // no extra space
        StringBuilder sb = new StringBuilder(s.length());
        int j = 0;
        for (int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '.') {
                sb.insert(0, "." + s.substring(j, i));
                j = i+1;
            }
        sb.insert(0, s.substring(j, s.length()));
        System.out.println(sb);
    }

    public static void solutionStr(String s) {              // split method
        StringBuilder sb = new StringBuilder(s.length());
        String[] sd = s.split("\\.");

        for(int i=sd.length-1;i>=0;i--)
            if(i==0)    sb.append(sd[i]);
            else        sb.append(sd[i]).append(".");
        System.out.println(sb);
    }
}

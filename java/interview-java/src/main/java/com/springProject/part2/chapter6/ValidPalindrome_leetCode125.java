package com.springProject.part2.chapter6;

import java.io.*;

public class ValidPalindrome_leetCode125 {
    public static boolean isPalindrome(String sentence) throws IOException {
        String target = sentence.trim().toLowerCase();
        int start = 0;
        int end = target.length() - 1;

        while(start < end) {
            if( !Character.isLetterOrDigit(target.charAt(start))) {
                start++;
            } else if ( !Character.isLetterOrDigit(target.charAt(end))) {
                end--;
            } else {
                if(target.charAt(start) != target.charAt(end)) {
                    return false;
                } else {
                    start++;
                    end++;
                }
            }
        }
        return true;
    }
}

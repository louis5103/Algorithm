package com.springProject.part3.chapter7;

import java.util.Arrays;

public class ProductOfArrayExceptLeet238 {
    private final int[] nums;
    ProductOfArrayExceptLeet238(int[] input) {
        this.nums = input;
    }

    public int[] productExceptSelf(){
        int[] p1 = new int[nums.length];
        int[] p2 = new int[nums.length];
        int[] result = new int[nums.length];
        p1[0] = 1;
        p2[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i++) {
            p1[i] = nums[i-1] * p1[i-1];
            p2[nums.length-i-1] = nums[nums.length-i] * p2[nums.length-i];
        }
        for(int i=0; i<result.length; i++){
            result[i] = p1[i] * p2[i];
        }
        System.out.println(Arrays.toString(p1));
        System.out.println(Arrays.toString(p2));
        return result;
    }
}

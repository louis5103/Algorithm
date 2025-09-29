package com.springProject.part3.chapter7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumLeet15 {
    private final int[] nums;
    ThreeSumLeet15(int[] nums) {
        this.nums = nums;
    }

    public List<List<Integer>> twoPointerMethod(){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < nums.length-2; i++){
            if(i>0 && nums[i] == nums[i-1]) continue;

            int left = i+1;
            int right = nums.length - 1;

            int sum = 0;
            while(left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if(sum > 0)
                    right--;
                else if(sum < 0)
                    left++;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]) left++;
                    while(left < right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}

package com.springProject.part3.chapter7;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWaterLeet42 {
    private final int[] height;

    public TrappingRainWaterLeet42(int[] height){
        this.height = height;
    }

    public int twoPointerMethod() {
        if(height == null || height.length < 3) return 0;

        int volume = 0;
        int leftPointerIndex = 0;
        int rightPointerIndex = height.length - 1;

        int leftHeightMax = height[leftPointerIndex];
        int rightHeightMax = height[rightPointerIndex];


        while(leftPointerIndex < rightPointerIndex) {
            leftHeightMax = Math.max(leftHeightMax, height[leftPointerIndex]);
            rightHeightMax = Math.max(rightHeightMax, height[rightPointerIndex]);

            // leftPointer 이동
            if(leftHeightMax <= rightHeightMax){
                volume += leftHeightMax - height[leftPointerIndex];
                leftPointerIndex++;
            } else { // rightPointer 이동
                volume += rightHeightMax - height[rightPointerIndex];
                rightPointerIndex--;
            }
        }
        return volume;
    }

    public int stackMethod() {
        if(height == null || height.length < 3) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        int volume = 0;

        for(int i = 0; i < height.length; i++){
            // 변곡점일때 검사.
            if(!stack.isEmpty() && height[i] > stack.peek()){
                int targetIndex = stack.pop();
                int distance = i - targetIndex - 1;

                for(int innerIndex = i; i >= targetIndex; i--){
                    int minHeight = Math.min(height[i], height[innerIndex]);

                }
            }
            // push.
            stack.push(i);
        }

        return -1;
    }
}

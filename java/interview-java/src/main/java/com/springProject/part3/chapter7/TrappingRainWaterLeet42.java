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

        for(int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int prevBottomIndex = stack.pop();

                if(stack.isEmpty()) break;
                int distance = i - stack.peek() - 1; // left wall 위치 index. left wall들중 가장 최근접

                // left wall과 right wall 중 낮은 높이 - bottom 인덱스
                int waters = Math.min(height[i], height[stack.peek()]) - height[prevBottomIndex];
                volume += waters * distance;
            }
            stack.push(i);
        }

        return volume;
    }



    public int stackMethodSolution(){
        Deque<Integer> stack = new ArrayDeque<>();
        int volume = 0;

        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer top = stack.pop();

                if(stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                int waters = Math.min(height[i], height[stack.peek()]) - height[top];
                volume += distance * waters;

            }
            stack.push(i);
        }
        return volume;
    }
}

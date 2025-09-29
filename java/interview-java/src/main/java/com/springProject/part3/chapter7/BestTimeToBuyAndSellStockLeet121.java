package com.springProject.part3.chapter7;

public class BestTimeToBuyAndSellStockLeet121 {
    public static int solution(int[] prices){
        int maxProfit = 0;
        int lowPoint = prices[0];

        for(int price : prices){
            lowPoint = Math.min(lowPoint, price);
            maxProfit = Math.max(maxProfit, price - lowPoint);
        }
        return maxProfit;
    }
}

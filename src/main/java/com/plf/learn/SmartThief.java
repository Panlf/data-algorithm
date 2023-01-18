package com.plf.learn;

/**
 * 聪明的小偷
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约
 * 因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一个晚上被小偷闯入，系统就会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算不触动警报装置的情况下，一夜之内能够偷窃到的最高金额
 * @author panlf
 * @date 2023/1/18
 */
public class SmartThief {

    public static void main(String[] args) {
        System.out.println(steal(new int[]{1,5,3,9}));
    }

    public static int steal(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        // 只偷最后一个
        dp[n-1] = nums[n-1];
        // 如果有1个以上可以偷，那偷倒数第二个，就是两个比较
        if(n > 1){
            dp[n-2] = Math.max(nums[n-1],nums[n-2]);
        }
        //之后就是跳一格，隔开一格加当前的金额，跟上一个进行比较
        for(int i = n - 3;i>=0;--i){
            dp[i] = Math.max(nums[i]+dp[i+2],dp [i+1]);
        }
        return dp[0];
    }
}

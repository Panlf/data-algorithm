package com.plf.learn;

/**
 * 有n阶楼梯，有两种走法1次走1阶 或者 1次走2阶
 *
 * 核心要理解
 * 它意味着爬到第 xx 级台阶的方案数是爬到第 x - 1 级台阶的方案数和爬到第 x - 2  级台阶的方案数的和。
 * 很好理解，因为每次只能爬 1 级或 2 级，所以 f(x) 只能从 f(x - 1)  和 f(x - 2)  转移过来，
 * 而这里要统计方案总数，我们就需要对这两项的贡献求和。
 *
 * @author panlf
 * @date 2022/11/27
 */
public class CreepFloor {
    public static void main(String[] args) {
        System.out.println(getSum(21));
        //System.out.println(climbStairs(21));
        System.out.println(climbStairs02(21));
    }

    /**
     * 走f(n)阶的可能性 f(n-2) + f(n-1) 两种的总和
     * 走一步 就是 n-1 那就求f(n-1)的走法
     * 走两步 就是 n-2 那就求f(n-2)的走法
     *
     * 递归做法速度太慢了
     */
    public static int getSum(int n){
        if(n==1 || n==2){
            return n;
        }
        return getSum(n-1)+getSum(n-2);
    }

    /**
     * 使用数组，将每一步的结果都存起来
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if(n==1 || n==2){
            return n;
        }
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 2;
        for(int i=3;i<n+1;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr[n];
    }


    /**
     * 使用数组，将每一步的结果都存起来
     * @param n
     * @return
     */
    public static int climbStairs02(int n) {
        if(n==1 || n==2){
            return n;
        }
        int first = 1;
        int second = 2;
        //只存总和值
        int temp = 0;
        for(int i=3;i<n+1;i++){
            // temp 是 第一步 + 第二步
            temp = first + second;
            // 后一步 就是 前一步的second
            first = second;
            // 后两步 就是 前一步的 和
            second = temp;
        }
        return temp;
    }
}

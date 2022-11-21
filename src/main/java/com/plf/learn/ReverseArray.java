package com.plf.learn;

/**
 *
 * 数组里有0和1，一定要翻转一个区间，0变成1，1变成0，请问翻转后可以使得1得个数最多
 *
 * 修改为 区间中 累加和最大的区间
 * @author panlf
 * @date 2022/11/21
 */
public class ReverseArray {
    /**
     *  我们可以这样思考
     *          0 -- 1  这就是多个1
     *          1 -- -1 这就是少个-1
     *          这样总和就是1的个数，我们把问题改成数组区间的最大值
     *
     */
    public static void main(String[] args) {
        // 如果是以下的值 dp的值为
        getMaxSum(new int[]{1,1,-1,-1,1,1,1,-1,1,1});
        // dp 的值
        // 1 2 1 0 1 2 3 2 3 4


        System.out.println(getMaxSum03(new int[]{1,1,-1,-1,1,1,1,-1,1}));
    }

    public static int getMaxSum(int arr[]){
        if(null == arr ||  arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[] dp = new int[n];
        //初始值就是数组的第一个值
        int max = arr[0];
        dp[0] = arr[0];
       // 就是求 第 i 个位置 结束的 最大结果
        for(int i=1;i<n;i++){
            // 第一个值就是 当前 i 位置的值
            int p1 = arr[i];
            // 第二值就是   前一个最大的值 + 当前 i 位置的值
            int p2 = dp[i-1]+arr[i];

            // dp 存储 每个 i 为止 的最大值  如果当前i位置的值 >  前一个最大的值 + 当前 i 位置的值
            // 则dp存 当前位置 i 的值，跟前面就不挂钩了
            dp[i]= Math.max(p1,p2);
            max = Math.max(max,dp[i]);
        }
        /*for(int i=0;i<n;i++){
            System.out.print(dp[i]+" ");
        }*/
        return max;
    }

    public static int getMaxSum02(int arr[]){
        if(null == arr ||  arr.length == 0){
            return 0;
        }
        int n = arr.length;
        //初始值就是数组的第一个值
        int max = arr[0];
        int pre = arr[0];

        // 就是求 第 i 个位置 结束的 最大结果
        for(int i=1;i<n;i++){
            // 第一个值就是 当前 i 位置的值
            int p1 = arr[i];
            // 第二值就是  前一个最大的值+当前值
            int p2 = pre+p1;

            // 当前i位置的值  VS 当前i位置的值+前一个位置的最大的值
            int current = Math.max(p1,p2);
            max = Math.max(max,current);
            pre = current;
        }

        return max;
    }

    public static int getMaxSum03(int arr[]){
        if(null == arr ||  arr.length == 0){
            return 0;
        }
        int n = arr.length;
        //初始值就是数组的第一个值
        int max = arr[0];
        int pre = arr[0];

        // 就是求 第 i 个位置 结束的 最大结果
        for(int i=1;i<n;i++){
            // 要不要加上前面的值  要不要跟前面的值挂钩
            // pre = 当前值 VS 前一个最大的值+当前值
            pre = Math.max(arr[i],pre+arr[i]);
            // 最大值 = 以往 pre 的 最大值
            max = Math.max(max,pre);
        }
        return max;
    }
}

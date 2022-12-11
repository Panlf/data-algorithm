package com.plf.learn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，含有n个数字，可能有正、有负、有0，给定一个正数k，返回所有子序列中，累加和最大的前k个子序列累加和
 * @author panlf
 * @date 2022/12/8
 */
public class CumulativeSum {
    /**
     * 解题思路
     *      第一步：首先将所有正数加起来  ===>  这是数据arr中的最大值
     *      第二步：将负数转成正数、正数保留，然后排序 ===> 求出 计算子数组累加和 最小的情况
     *      第三步：依次将第一步的最大值减去数组中的值，则是前几的累加和
     *
     *      比如[-2,4,5]
     *      最大值是9
     *      那最大和9，然后[2,4,5]  第二大 7 第三大 5 第四大 4
     */

    public static void main(String[] args) {
      int[] arr = new int[]{-1,3,5};
      Arrays.stream(topMaxSum(arr,6)).forEach(System.out::println);
    }
    //前top大
    public static int[] topMaxSum(int[] arr,int k){
        int sum = 0;

        for(int i=0;i<arr.length;i++){
            if(arr[i] >= 0){
                sum += arr[i];
            } else {
                arr[i] = -arr[i];
            }
        }
        int[] ans = topMinSum(arr,k);
        for(int i=0;i<ans.length;i++){
            ans[i] = sum - ans[i];
        }
        return ans;
    }
    //前top小
    private static int[] topMinSum(int[] arr, int k) {
        //对arr进行排序
        Arrays.sort(arr);
        //优先队列 根据数组的第二个数进行排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->a[1]-b[1]);
        heap.add(new int[]{0,arr[0]});
        int[] ans = new int[k];
        // ans 要从 下标 1 开始  然后 0 为空集
        for(int i=1;i<k;i++){
            int[] cur = heap.poll();
            int last = cur[0];
            int sum =  cur[1];
            ans[i] = sum;
            if(last + 1 < arr.length){
                // 获取 下一个坐标的值
                heap.add(new int[]{last+1,sum-arr[last]+arr[last+1]});
                // 获取 比 下一个坐标的值 + 前一个最小和值
                heap.add(new int[]{last+1,sum+arr[last+1]});
            }
        }
        return ans;
    }


}

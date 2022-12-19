package com.plf.learn;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定int[][] meetings,比如
 * {
 *     {66,70} 0号会议截止时间66 获得收益 70
 *     {25,90} 1号会议截止时间25 获得收益 90
 *     {50,30} 2号会议截止时间50 获得收益 30
 * }
 * 一开始的时间是0，任何会议都持续10的时间
 * 但是一个会议一定要在该会议截止时间之前开始
 * 只有一个会议室，任何会议不能共用会议室
 * 一旦一个会议被正确安排，将获得这个会议的收益
 * 请返回最大的收益
 * @author panlf
 * @date 2022/12/14
 */
public class Meeting {

    // 所有会议 先根据截止时间排序
    // [10,40] 、[5,32] -> [5,32]、[10,40]
    public static int maxMeetingScore(int[][] meetings){
        // 根据 二维数组的第一个值 进行排序
        Arrays.sort(meetings,(a,b)->a[0]-b[0]);
        // 小根堆 里面放 收益 ，收益小的在顶，收益大的在底
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int time = 0;
        // 已经把所有会议，按照截止时间，从小到大，排序了
        // 截止时间一样的，谁排前谁排后，无所谓
        for(int i=0;i < meetings.length; i++){
            // 如果 time+10 < 截止时间
            if(time + 10 <= meetings[i][0]){
                heap.add(meetings[i][1]);
                time += 10;
                // 如果 time + 10 > 截止时间
                // 则判断前一个放入的值
                // 不能通过增加会议数量来安排 只能看能不能挤掉之前收益最不行的会议
            }else{
                if(!heap.isEmpty() && heap.peek() < meetings[i][1]){
                    heap.poll();
                    heap.add(meetings[i][1]);
                }
            }
        }
        int ans = 0 ;
        while(!heap.isEmpty()){
            ans += heap.poll();
        }
        return ans;

     }
}

package com.plf.tutorial.sorted;
import java.util.Arrays;

/**
 * @author panlf
 * @date 2024/1/9
 */
public class QuickSort {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void quickSort(int[] arr,int left,int right){
        if(left >= right){
            return;
        }

        //基准值
        int temp = arr[left];

        int i = left;
        int j = right;

        //这个while循环，就会把基准值调到中间，因为基准值调到左边调到右边，循环过来就到中间
        while(i<j){
            // 从右边往左边移动，如果比基准值大的
            // 从右找过来，找到第一个比基准值大的坐标
            while (temp <= arr[j] && i<j){
                j--;
            }
            //如果找到了，那就把基准值换到找到的左边（所以是j--，刚好减一位，就刚好是左边一位）
            swap(arr,i,j);

            // 从左边往右边移动，如果比基准值小的
            // 找到比基准值小的，又把基准值调过来了，然后小的那又刚好在基准值的左边（i++，加一位，然后基准值调到了加一位那边）
            while (temp >= arr[i] && i<j){
                i++;
            }
            swap(arr,i,j);
        }
        //递归调用左半数组
        quickSort(arr, left, i-1);
        //递归调用右半数组
        quickSort(arr, i+1, right);
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4,6,1,8,2,6};
        quickSort(arr,0,arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

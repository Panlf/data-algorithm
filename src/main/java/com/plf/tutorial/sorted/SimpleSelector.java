package com.plf.tutorial.sorted;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Breeze
 * @date 2024/1/8
 */
public class SimpleSelector {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 7};
        selectorSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void selectorSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            //第二个循环是i+1，因为前面的是已经排序过了，不需要再比较，
            // 所以第二次就是i+1后面的进行排序即可
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}

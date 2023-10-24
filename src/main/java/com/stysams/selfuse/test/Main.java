package com.stysams.selfuse.test;

/**
 * @author StysaMS
 */
public class Main {
    public static void main(String[] args) {
        // 严格的冒泡排序
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 8};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            // 优化冒泡排序
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 从小到大排序
                if (arr[j] > arr[j + 1]) {
                    // 交换位置
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 优化冒泡排序
                    flag = false;
                }
            }
            // 优化冒泡排序
            if (flag) {
                break;
            }
        }
    }
}

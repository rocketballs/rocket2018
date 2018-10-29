package com.rocketball.alg.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {7, 9, 1, 4, 6, 22, 55, 3, 7, 9, 8, 14, 19, -4, 12};
        int k = 8;
        quickSor(arr, 0, arr.length - 1, k);
        System.out.println();
    }
    private static void quickSor(int[] arr, int i, int j, int k) {
        if (i < j) {
            int pos = partition(arr, i, j);
//			print(arr,i,j);
//			System.out.println(pos);
            int temp = j - pos;
            if (temp == k) {
                print(arr, pos + 1, j);
                return;
            } else if (temp < k) {
                print(arr, pos + 1, j);
                quickSor(arr, i, pos, k - temp);
            } else if (temp > k) {
                quickSor(arr, pos + 1, j, k);
            }
        }
    }

    private static void print(int[] arr, int i, int j) {
        for (int x = i; x <= j; x++)
            System.out.print(arr[x] + " ");
    }


    private static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }

}

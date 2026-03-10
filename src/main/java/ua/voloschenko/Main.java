package ua.voloschenko;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, -3, 12, 0, -7, 8};

        for (int i = 0; i < arr.length; i++) {
            int thisNum = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>thisNum){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = thisNum;
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
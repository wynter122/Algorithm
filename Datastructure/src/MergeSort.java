import java.io.BufferedReader;
import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] list, int left, int right){
        if (left == right)
            return;

        int mid = left + ((right-left) / 2);
        sort(list, left, mid);
        sort(list, mid+1, right);
        merge(list, left, right, mid+1);
    }

    static void merge(int[] list, int left, int right, int mid){
        int[] tmp = new int[list.length];
        int tmpIdx = left;

        int leftIdx = left;
        int rightIdx = mid;
        while(true){
            if (leftIdx <= mid && rightIdx <= right){
                if (list[leftIdx] < list[rightIdx])
                    tmp[tmpIdx++] = list[leftIdx++];
                else
                    tmp[tmpIdx++] = list[rightIdx++];
            }else if (leftIdx > mid)
                tmp[tmpIdx++] = list[rightIdx++];
            else if (rightIdx > right)
                tmp[tmpIdx++] = list[leftIdx++];

            if (tmpIdx > right)
                break;
        }

        for (int i = left; i <= right; i++)
            list[i] = tmp[i];
    }
}

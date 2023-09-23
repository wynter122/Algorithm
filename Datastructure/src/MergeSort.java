import java.io.BufferedReader;
import java.util.Arrays;

public class MergeSort {
    public static int[] sort(int[] list){
        if (list.length == 1)
            return list;

        int n = list.length / 2;
        int[] left = sort(Arrays.copyOfRange(list, 0, n));
        int[] right = sort(Arrays.copyOfRange(list, n, list.length));

        int[] sortedResult = new int[list.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int resultIdx = 0;
        while(true){
            if (leftIdx < left.length && rightIdx < right.length){
                if (left[leftIdx] < right[rightIdx])
                    sortedResult[resultIdx++] = left[leftIdx++];
                else
                    sortedResult[resultIdx++] = right[rightIdx++];
            }else if (leftIdx >= left.length && rightIdx < right.length)
                sortedResult[resultIdx++] = right[rightIdx++];
            else if (leftIdx < left.length && rightIdx >= right.length)
                sortedResult[resultIdx++] = left[leftIdx++];

            if (resultIdx >= sortedResult.length)
                break;
        }

        return sortedResult;
    }
}

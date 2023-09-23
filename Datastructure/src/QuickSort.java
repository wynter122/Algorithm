public class QuickSort {
    static void sort(int[] list, int left, int right){
        if (left == right)
            return;

        // 1.pivot을 정한다
        int pivot = list[left];

        // 2. 범위 내에서 pivot 보다 작은건 왼쪽, 큰건 오른쪽으로 swap 한다
        int low = left+1;
        int high = right;

        while(low < high){
            if (list[low] > pivot && list[high] < pivot){           // 서로 교환
                int tmp = list[low];
                list[low++] = list[high];
                list[high--] = tmp;
            }else if (list[low] < pivot && list[high] < pivot)      // 두 수 모두 pivot 보다 작다면, pivot 자리 증가
                low++;
            else if (list[low] > pivot && list[high] > pivot)       // 두 수 모두 pivot 보다 크다면, pivot 자리 감소
                high--;
            else if (list[low] < pivot && list[high] > pivot){      // 두 수 모두 재정렬 필요 없다면, 교환하지 않고 low, high 둘 다 이동
                low++;
                high--;
            }
        }

        // 3. pivot 교환
        if (pivot > list[low]){
            list[left] = list[low];
            list[low] = pivot;
        }

        // 4. pivot으로 나눠진 부분배열 정렬
        sort(list, left, low-1);
        sort(list, low, right);
    }
}

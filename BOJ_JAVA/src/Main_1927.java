import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Heap{
    private int[] heap;
    int size;
    public Heap(int N){
        heap = new int[N+1];
        heap[0] = 0;            // 인덱스 1부터 시작하기 위해, 0번 인덱스에 쓰레기값 넣기
        size = 0;
    }

    public void addHeap(int x){
        heap[++size] = x;                        // 제일 마지막에 원소 넣기

        int currIdx = size;
        while(currIdx > 1){
            int parentIdx = currIdx / 2;

            if (heap[currIdx] < heap[parentIdx]){
                swap(currIdx, parentIdx);
                currIdx = parentIdx;
            }else
                break;
         }
    }

    public int removeMin(){
        if (size < 1)
            return 0;

        // 루트가 최소
        int result = heap[1];

        // 가장 마지막 원소를 루트로 옮기기
        heap[1] =  heap[size--];

        // 자식 중 더 작은 값과 swap
        int currIdx = 1;
        while(currIdx * 2 <= size){
            int lefIdx = currIdx * 2;
            int rightIdx = currIdx * 2 + 1;

            if (rightIdx > size){
                if (heap[currIdx] > heap[lefIdx])
                    swap(currIdx, lefIdx);
                break;
            }

            if (heap[currIdx] > heap[lefIdx] && heap[currIdx] > heap[rightIdx]){
                if (heap[lefIdx] < heap[rightIdx]){
                    swap(currIdx, lefIdx);
                    currIdx = lefIdx;
                }else{
                    swap(currIdx, rightIdx);
                    currIdx = rightIdx;
                }
            }else if (heap[currIdx] > heap[lefIdx]){
                swap(currIdx, lefIdx);
                currIdx = lefIdx;
            }else if (heap[currIdx] > heap[rightIdx]){
                swap(currIdx, rightIdx);
                currIdx = rightIdx;
            }else
                break;
        }
        return result;
    }

    private void swap(int originIdx, int newIdx){
        int tmp = heap[originIdx];
        heap[originIdx] =  heap[newIdx];
        heap[newIdx] = tmp;
    }
}

public class Main_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Heap minHeap = new Heap(N);

        for (int n = 0; n < N; n++){
            int x = Integer.parseInt(br.readLine());
            if (x == 0)
                sb.append(minHeap.removeMin()).append("\n");
            else
                minHeap.addHeap(x);
        }

        System.out.print(sb);
    }
}

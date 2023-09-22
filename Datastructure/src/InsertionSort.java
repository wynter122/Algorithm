import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InsertionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            list[n] = Integer.parseInt(st.nextToken());

        sort(list, N);
        System.out.println(Arrays.toString(list));
    }

    static void sort(int[] list, int N){
        for (int i = 1; i < N; i++){    // 아직 정렬되지 않은 원소 시작 인덱스
            System.out.print("Insert : " + list[i] + " -> ");
            int num = list[i];
            for (int j = i-1; j >= 0; j--){            // 정렬된 배열의 마지막 원소부터 순서대로 비교
                if (num < list[j]){
                    list[j+1] = list[j];
                    list[j] = num;
                }else
                    break;
            }
        }
    }
}

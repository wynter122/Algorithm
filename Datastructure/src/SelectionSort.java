import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SelectionSort {
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
        for (int i = 0; i < N; i++){            // 정렬 완료된 인덱스
            int min = list[i];
            int idx = i;
            for (int j = i+1; j < N; j++){
                if (min > list[j]){
                    min = list[j];
                    idx = j;
                }
            }
            int tmp = list[i];
            list[i] = min;
            list[idx] = tmp;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());            // 숫자 개수
        int[] list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            list[n] = Integer.parseInt(st.nextToken());

        sort(list, N);
        System.out.println(Arrays.toString(list));
    }

    static void sort(int[] list, int N){
        for (int i = 0; i < N; i++){                // 끝에서부터 정렬된 원소 개수
            for (int j = 0; j < N-i-1; j++){                // 어디까지 정렬할지
                if (list[j] > list[j+1]){
                    int tmp = list[j+1];
                    list[j+1] = list[j];
                    list[j] = tmp;
                }
            }
        }
    }
}

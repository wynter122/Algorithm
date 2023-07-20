import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20922 {
    static int[] counter = new int[100001]; // 1 ~ 100,000
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int length = 0;
        int start = 0;
        int end = 0;

        counter[sequence[start]]++;    // 시작 포인터에 위치한 숫자 cnt 증가
        while(end < N){        // end 포인터가 끝까지 갈 때까지 반복 O(N)
            if (counter[sequence[end]] > K) {        // end 포인터의 값 K 초과
                counter[sequence[start++]]--;          // start 이동
            }else{                              // end 포인터 값 정상 -> end 이동
                int tmpLength = end - start + 1;
                length = length < tmpLength ? tmpLength : length;
                if (++end < N)
                    counter[sequence[end]]++;
            }
        }

        System.out.println(length);
    }
}

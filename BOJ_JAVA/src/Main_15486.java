import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_15486 {
    static int[] T;
    static int[] P;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        T = new int[N+1];
        P = new int[N+1];
        dp = new int[N+1];

        for (int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            T[n] = Integer.parseInt(st.nextToken());
            P[n] = Integer.parseInt(st.nextToken());
        }

        DP(N);
        System.out.println(dp[1]);
    }

    static void DP(int N){
        int max = 0;                                    // n ~ N 까지의 최대 이윤 저장할 변수
        for (int n = N; n > 0; n--){                // 뒤쪽에서부터 최대 비용 계산
            int end = n + T[n] - 1;                     // 상담이 끝나는 날

            // 상담을 할 수 있으면
            if (end <= N){
                int endValue = end == N ? 0 : dp[end+1];
                dp[n] = Math.max(P[n] + endValue, max);
                max = dp[n];

            }else   // 상담을 할 수 없으면
                dp[n] = max;            // 지금까지 계산된 최대 이윤 저장
        }
    }
}

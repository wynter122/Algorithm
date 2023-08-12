import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11066 {
    static int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());

            int[] files = new int[N+1];             // 파일 크기 저장할 배열
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++)
                files[n] = Integer.parseInt(st.nextToken());

            int[] sum = new int[N+1];               // 파일 합 저장할 배열
            for (int i = 1; i <= N; i++){
                for (int j = 1; j <= i; j++)
                    sum[i] += files[j];
            }

            int[][] dp = new int[N+1][N+1];         // dp 배열
            // Dynamic Programming
            for (int i = 1; i < N; i++)             // 연속하여 2개씩 합친 결과 dp에 저장
                dp[i][i+1] = files[i] + files[i+1];

            for (int j = 2; j < N; j++){            // 합칠 파일의 개수
                for (int i = 1; i + j <= N; i++){            // 파일 합치기 시작 위치
                    int result = INT_MAX;
                    for(int k = i; k < i+j; k++)                // k : 분기점이 될 파일 번호
                        result = result > dp[i][k] + dp[k+1][i+j] ? dp[i][k] + dp[k+1][i+j] : result;

                    dp[i][i+j] = result + (sum[i+j] - sum[i-1]);
                }
            }

            System.out.println(dp[1][N]);
        }
    }
}

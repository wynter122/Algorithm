import java.util.Scanner;

public class Main_2293 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 동전 종류 받기
        int[] coins = new int[N+1];
        for (int n = 1; n <= N; n++)
            coins[n] = sc.nextInt();

        // 배열 생성 및 초기화
        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i <= N; i++)
            dp[i][0] = 1;               // 0을 만들 수 있는 경우의 수는 항상 1개

        for (int i = 1; i <= N; i++){
            int coin = coins[i];
            for (int j = 1; j <= K; j++){
                int account = j;
                if (coin > j)           // 현재 동전으로 금액을 만들 수 없을 때
                    dp[i][j] = dp[i-1][j];
                else {
                    int remain = account - coin;                // 현재 금액 - 현재 동전
                    dp[i][j] = dp[i - 1][j] + dp[i][remain];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}

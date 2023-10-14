import java.util.Scanner;

public class Main_9084 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++){
            int N = sc.nextInt();       // 동전 종류 개수

            // 동전 정보 받기
            int[] coins = new int[N+1];
            for (int n = 1; n <= N; n++)
                coins[n] = sc.nextInt();

            int M = sc.nextInt();       // 만들어야할 금액

            int[][] dp = new int[N+1][M+1];
            for (int i = 0; i <= N; i++)
                dp[i][0] = 1;               // 모든 동전으로 0을 만들 수 있는 경우의 수 == 1개

            for (int i = 1; i <= N; i++){
                for (int account = 1; account <= M; account++){
                    int coin = coins[i];
                    if (account < coin)                     // 현재 금액이 동전 금액보다 작아서 만들 수 없을 때
                        dp[i][account] = dp[i-1][account];      // i-1 동전의 최적해 그대로 가져옴
                    else{                                   // 현재 금액을 동전으로 만들 수 있을 때
                        int remain = account - coin;            // 현재 금액에서 동전만큼 빼기
                        dp[i][account] = dp[i-1][account] + dp[i][remain];
                    }
                }
            }
            System.out.println(dp[N][M]);
        }
    }
}

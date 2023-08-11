import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_2294 {
    static final int INF = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 동전 정보 받기
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        for (int n = 0; n < N; n++)
            set.add(sc.nextInt());

        Object[] coins = set.toArray();
        Arrays.sort(coins);

        N = coins.length-1;

        // dp 초기화
        int[][] dp = new int[N+1][K+1];
        for (int k = 1; k <= K; k++)            // 0원으로 만들 수 있는 금액은 없음
            dp[0][k] = INF;

        // dp 실행
        for (int i = 1; i <= N; i++){
            int coin = (int)coins[i];
            for (int j = 0; j <= K; j++){
                int account = j;

                if (coin > account)
                    dp[i][j] = dp[i-1][j];
                else{
                    int remains = account - coin;
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][remains] + 1);
                }
            }
        }

        int result = dp[N][K] == INF ? -1 : dp[N][K];
        System.out.println(result);
    }
}

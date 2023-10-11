import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070 {
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];

        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < N; m++)
                map[n][m] = Integer.parseInt(st.nextToken());
        }

        DP(N);
        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
    }

    static void DP(int N){
        // 초기값 설정
        dp[0][1][0] = 1;            // 가로로 파이프가 놓여있음
        for (int j = 1; j < N; j++){
            for (int i = 0; i < N; i++){
                // 파이프 상태에 따라 경우의 수 추가
                if (dp[i][j][0] > 0)
                    setHorizon(i, j);
                if (dp[i][j][1] > 0)
                    setDiagonal(i, j);
                if (dp[i][j][2] > 0)
                    setVertical(i, j);
            }
        }
    }

    static void setHorizon(int i, int j){       // 가로 상태일 경우 -> 가로, 대각선
        if (j+1 < map.length && map[i][j+1] == 0){      // 가로
            dp[i][j+1][0] += dp[i][j][0];

            if (i+1 < map.length && map[i+1][j] == 0 && map[i+1][j+1] == 0)
                dp[i+1][j+1][1] += dp[i][j][0];
        }
    }

    static void setDiagonal(int i, int j){              // 대각선 상태일 경우 -> 가로, 대각선, 세로
        if (j+1 < map.length && map[i][j+1] == 0)     // 가로
            dp[i][j+1][0] += dp[i][j][1];

        if (i+1 < map.length && map[i+1][j] == 0)     // 세로
            dp[i+1][j][2] += dp[i][j][1];

        if (i+1 < map.length && j+1 < map.length && map[i][j+1] == 0 && map[i+1][j] == 0 && map[i+1][j+1] == 0)     // 대각선
            dp[i+1][j+1][1] += dp[i][j][1];
    }

    static void setVertical(int i, int j){              // 세로 상태일 경우 -> 대각선, 세로
        if (i+1 < map.length && map[i+1][j] == 0){          // 세로
            dp[i+1][j][2] += dp[i][j][2];

            if (j+1 < map.length && map[i][j+1] == 0 && map[i+1][j+1] == 0)     // 대각선
                dp[i+1][j+1][1] += dp[i][j][2];
        }
    }
}

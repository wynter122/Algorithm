import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_17069 {
    static int[][] map;
    static long[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N][3];

        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < N; m++)
                map[n][m] = Integer.parseInt(st.nextToken());
        }

        DP();
        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
    }

    static void DP(){
        dp[0][1][0] = 1;            // 초기값 세팅

        for (int j = 1; j < map.length; j++){
            for (int i = 0; i < map.length; i++){
                // 가로
                if (dp[i][j][0] > 0){
                    setHorizon(i, j, 0);
                    setDiagonal(i, j, 0);
                }

                // 대각선
                if (dp[i][j][1] > 0){
                    setHorizon(i, j, 1);
                    setDiagonal(i, j, 1);
                    setVertical(i, j, 1);

                }

                // 세로
                if (dp[i][j][2] > 0){
                    setDiagonal(i, j, 2);
                    setVertical(i, j, 2);
                }
            }
        }
    }

    static void setHorizon(int i, int j, int dir){
        if (j+1 >= map.length)
            return;
        if (map[i][j+1] == 0)
            dp[i][j+1][0] += dp[i][j][dir];
    }

    static void setDiagonal(int i, int j, int dir){
        if (i+1 >= map.length || j+1 >= map.length)
            return;
        if (map[i][j+1] == 0 && map[i+1][j] == 0 && map[i+1][j+1] == 0)
            dp[i+1][j+1][1] += dp[i][j][dir];
    }

    static void setVertical(int i, int j, int dir){
        if (i+1 >= map.length)
            return;
        if (map[i+1][j] == 0)
            dp[i+1][j][2] += dp[i][j][dir];
    }
}

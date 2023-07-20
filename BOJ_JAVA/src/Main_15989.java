import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // DP 만들기
        int[][] DP = new int[4][100001];     // 1, 2, 3 행, 1 ~ 10000 열
        for (int n = 1; n < DP[0].length; n++){
            for (int i = 1; i < 4; i++){
                if (i == 1 || n == i)             // 1 혹은 n으로 n을 만들 수 있는 경우의 수는 1
                    DP[i][n] = 1;
                else {
                    int remainder = n - i;
                    if (remainder <= 0)
                        continue;
                    int sum = 0;
                    for (int k = 1; k <= i; k++)
                        sum += DP[k][remainder];
                    DP[i][n] = sum;
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            for (int i = 1; i < 4; i++)
                answer += DP[i][N];
            System.out.println(answer);
        }
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class Main_10942 {
    static int[][] dp;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        dp = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        dp();

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int result = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void dp(){
        for (int i = 1; i < numbers.length; i++)
            dp[i][i] = 1;

        for (int j = 2; j < numbers.length; j++){
            for (int i = 1; i < j; i++) {
                if (Math.abs(i-j) == 1)
                    dp[i][j] = numbers[i] == numbers[j] ? 1 : 0;
                else
                    dp[i][j] = dp[i + 1][j - 1] == 1 && numbers[i] == numbers[j] ? 1 : 0;
            }
        }
    }
}

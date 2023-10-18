import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9252 {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String blank = " ";
        char[] A = (blank + br.readLine()).toCharArray();
        char[] B = (blank + br.readLine()).toCharArray();

        dp = new int[B.length][A.length];
        DP(A, B);

        int result = dp[B.length-1][A.length-1];
        System.out.println(result);
        if (result != 0){
            String str = "";

            int i = B.length-1;
            int j = A.length-1;
            while(dp[i][j] > 0){
                if (B[i] == A[j]){
                    str = B[i] + str;
                    i -= 1;
                    j -= 1;
                }else {
                    if (dp[i-1][j] > dp[i][j-1])
                        i -= 1;
                    else
                        j -= 1;
                }
            }

            System.out.println(str);
        }
    }

    static void DP(char[] A, char[] B){
        for (int i = 1; i < B.length; i++){
            for (int j = 1; j < A.length; j++) {
                if (B[i] == A[j])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

            }
        }
    }
}

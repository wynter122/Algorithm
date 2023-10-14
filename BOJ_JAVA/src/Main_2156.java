import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2156 {
    static int[] numbers;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        dp = new int[N];

        for (int n = 0; n < N; n++)
            numbers[n] = Integer.parseInt(br.readLine());

        if (N == 1)
            System.out.println(numbers[0]);
        else if (N == 2)
            System.out.println(numbers[0] + numbers[1]);
        else {
            DP();

            int maxResult = 0;
            for (int i = 0; i < N; i++)
                maxResult = Math.max(maxResult, dp[i]);

            System.out.println(maxResult);
        }
    }

    static void DP(){
        dp[0] = numbers[0];
        dp[1] = numbers[0] + numbers[1];
        if (numbers[0] + numbers[1] > numbers[1] + numbers[2])
            dp[2] = Math.max(numbers[0] + numbers[1], numbers[0] + numbers[2]);
        else
            dp[2] = Math.max(numbers[1] + numbers[2], numbers[0] + numbers[2]);

        for (int i = 3; i < dp.length; i++)
            dp[i] = Math.max(numbers[i] + numbers[i-1] + dp[i-3], numbers[i] + dp[i-2]);
    }
}

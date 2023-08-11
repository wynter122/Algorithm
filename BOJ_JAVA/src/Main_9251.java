import java.util.Scanner;

public class Main_9251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String X = " "+sc.nextLine();
        String Y = " "+sc.nextLine();

        char[] xArr = X.toCharArray();
        char[] yArr = Y.toCharArray();

        int[][] dp = new int[X.length()][Y.length()];
        for (int i = 1; i < X.length(); i++){
            char x = xArr[i];
            for (int j = 1; j < Y.length(); j++){
                char y = yArr[j];
                if (x == y)
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[X.length()-1][Y.length()-1]);
    }
}

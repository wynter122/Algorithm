import java.util.Scanner;

public class Main_5582 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String X = " " + sc.nextLine();
        String Y = " " + sc.nextLine();

        char[] xArr = X.toCharArray();
        char[] yArr = Y.toCharArray();

        int[][] dp = new int[xArr.length][yArr.length];
        int maxLength = 0;
        for (int i = 1; i < xArr.length; i++){
            char x = xArr[i];
            for (int j = 1; j < yArr.length; j++){
                char y = yArr[j];
                if (x == y) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        System.out.println(maxLength);
    }
}

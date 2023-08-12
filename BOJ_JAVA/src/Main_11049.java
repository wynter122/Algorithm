import java.util.Scanner;

class Matrix {
    long row, col;
    public Matrix(long row, long col){
        this.row = row;
        this.col = col;
    }
}
public class Main_11049 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();           // 행렬 개수
        Matrix[] matrix = new Matrix[N+1];    // 행렬 정보 저장할 배열
        for (int n = 1; n <= N; n++)
            matrix[n] = new Matrix(sc.nextInt(), sc.nextInt());

        // dynamic programming
        long[][] dp = new long[N+1][N+1];
        for (int i = 1; i < N; i++)
            dp[i][i+1] = matrix[i].row * matrix[i].col * matrix[i+1].col;

        for (int j = 2; j < N; j++){
            for (int i = 1; i+j <= N; i++){
                long result = 0;
                for (int k = i; k < i+j; k++) {
                    result = (dp[i][k] + dp[k + 1][i + j]) + (matrix[i].row * matrix[k].col * matrix[i+j].col);
                    if (dp[i][i+j] == 0 || dp[i][i+j] > result)
                        dp[i][i+j] = result;
                }
            }
        }
        System.out.println(dp[1][N]);
    }
}

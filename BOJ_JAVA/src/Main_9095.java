import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9095 {
    static int[][] DP = new int[4][12];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DynamicProgramming();

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(DP[3][num]).append("\n");
        }
        System.out.print(sb);
    }
    static void DynamicProgramming(){
        for (int i = 0; i < DP[0].length; i++)
            DP[1][i] = 1;           // 모든 수를 1로만 만드는 경우의수 = 1
        DP[2][0] = 1;           // 0을 만드는 경우의수 1
        DP[3][0] = 1;

        for (int i = 2; i < DP.length; i++){
            for (int j = 1; j < DP[i].length; j++) {
                if (i > j)
                    DP[i][j] = DP[i-1][j];              // 현재 수보다 나타내야하는 수가 작아서 나타낼 수 없음 -> n-1로 나타낼 수 있는 방법 수 가져옴
                else{
                    // 점화식 : DP[현재까지 가능한 수 n (1->2->3)][나타내야 하는 수] = 나타내야 하는 수에서 1부터 n까지 뺐을 때 경우의 수 합
                    for (int k = 1; k <= i; k++)
                        DP[i][j] += DP[i][j-k];
                }
            }
        }

    }
}

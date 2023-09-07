import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579 {
    static int[] stairs;
    static int[] DP;          //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        stairs = new int[N];
        DP = new int[N];

        for(int n = 0; n < N; n++)
            stairs[n] = Integer.parseInt(br.readLine());

        if (N < 3){         // 계단이 2칸 이하면 모두 더하기
            int result = 0;
            for (int n = 0; n < N; n++)
                result += stairs[n];
            System.out.println(result);
        }else{
            DP[0] = stairs[0];
            DP[1] = stairs[0] + stairs[1];
            DP[2] = Math.max(stairs[0], stairs[1]) + stairs[2];

            for (int i = 3; i < N; i++)
                DP[i] = Math.max(DP[i-3] + stairs[i-1] + stairs[i], DP[i-2] + stairs[i]);
            System.out.println(DP[N-1]);
        }
    }
}

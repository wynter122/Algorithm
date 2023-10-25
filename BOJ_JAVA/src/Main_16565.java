import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main_16565 {
    static int mod = 10007;
    static int[][] comb = new int[53][53];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1; i <= N/4; i++){
            int tmp = (getComb(13, i) * getComb(52 - i * 4, N - i * 4));   // 빼기
            tmp %= mod;

            if (i % 2 == 1){        // 홀수개 포커 쌍
                answer += tmp;
                answer %= mod;
            }else{          // 짝수개 포커 쌍
                answer -= tmp;
                answer += mod;          // 음수가 되는 경우 고려하여 mod 더해줌
                answer %= mod;
            }
        }
        System.out.println(answer);
    }

    static int getComb(int n, int r){       // N_C_K
        if (comb[n][r] != 0)
            return comb[n][r];
        else if (r == 0 || r == n)
            return 1;
        else
            comb[n][r] = (getComb(n-1, r-1) + getComb(n-1, r)) % mod;
        return comb[n][r];
    }
}

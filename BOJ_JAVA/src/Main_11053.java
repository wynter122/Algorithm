import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11053 {
    static int[] numbers;           // 수열 저장
    static int[] DP;                // 각 원소까지의 최장 부분 수열 길이 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        DP = new int[N];
        Arrays.fill(DP, 1);         // 자기자신을 길이로 가지는 최장 부분수열로 초기화

        int result = 0;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            numbers[n] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++){
                if (numbers[j] < numbers[n])                    // 증가하는 부분수열에 낄 수 있음 (현재 수보다 작은 수 발견)
                    DP[n] = Math.max(DP[n], DP[j]+1);               // 해당수의 부분수열길이 + 1, 현재 내가 소속된 부분수열길이 비교 후 최대값으로 업데이트
            }
            result = Math.max(result, DP[n]);
        }
        System.out.println(result);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11722 {
    static int[] numbers;
    static int[] DP;        // 각 인덱스까지 증가하는 부분수열 최대길이 저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        DP = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++)
            numbers[n] = Integer.parseInt(st.nextToken());

        int result = DynamicProgramming();
        System.out.println(result);
    }
    static int DynamicProgramming(){
        int result = 1;
        Arrays.fill(DP, 1);         // 자기자신이 최소 부분수열 개수
        for (int i = 2; i < numbers.length; i++){               // 현재 인덱스
            for (int j = 1; j < i; j++){                          // 1번 수부터 n-1까지의 수를 n과 비교
                if (numbers[i] < numbers[j])                       // 현재 수보다 큰 수 발견 (감소함)
                    DP[i] = Math.max(DP[i], DP[j] + 1);             // 큰수의 최장 수열 길이 + 1 과 내 원래 수열길이 비교해서 큰 값 넣기
                result = Math.max(result, DP[i]);
            }
        }
        return result;
    }
}

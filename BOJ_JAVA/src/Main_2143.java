import java.io.*;
import java.util.*;

public class Main_2143 {
    static HashMap<Integer, Integer> aCounter= new HashMap<>();
    static HashMap<Integer, Integer> bCounter = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int Alen = Integer.parseInt(br.readLine());
        int[] A = new int[Alen];
        st = new StringTokenizer(br.readLine());
        for (int a = 0; a < Alen; a++)
            A[a] = Integer.parseInt(st.nextToken());

        int Blen = Integer.parseInt(br.readLine());
        int[] B = new int[Blen];
        st = new StringTokenizer(br.readLine());
        for (int b = 0; b < Blen; b++)
            B[b] = Integer.parseInt(st.nextToken());

        DP(A, aCounter);
        DP(B, bCounter);

        // 정렬
        Object[] aKeys = aCounter.keySet().toArray();
        Arrays.sort(aKeys);

        long result = 0;
        for (int a = 0; a < aKeys.length; a++){
            int remain = T - (int)aKeys[a];
            if (bCounter.containsKey(remain)){
                result += (long)aCounter.get(aKeys[a]) * (long)bCounter.get(remain);
            }
        }

        System.out.println(result);
    }

    static void DP(int[] numbers, HashMap<Integer, Integer> hashmap){
        int[][] dp = new int[numbers.length][numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            dp[i][i] = numbers[i];
            if (hashmap.containsKey(dp[i][i]))
                hashmap.put(dp[i][i], hashmap.get(dp[i][i]) + 1);
            else
                hashmap.put(dp[i][i], 1);
        }

        for (int j = 1; j < numbers.length; j++){
            for (int i = 0; i+j < numbers.length; i++) {
                dp[i][i + j] = dp[i][i + j - 1] + dp[i + j][i + j];
                if (hashmap.containsKey(dp[i][i+j]))
                    hashmap.put(dp[i][i+j], hashmap.get(dp[i][i+j]) + 1);
                else
                    hashmap.put(dp[i][i+j], 1);
            }
        }
    }
}

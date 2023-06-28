/*
10개의 수를 입력 받아, 그 중에서 가장 큰 수를 출력하는 프로그램을 작성하라.

[제약 사항]
각 수는 0 이상 10000 이하의 정수이다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2068 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            int result = 0;

            String numbers = br.readLine();
            st = new StringTokenizer(numbers, " ");

            while(st.hasMoreTokens()){
                int tmp_num = Integer.parseInt(st.nextToken());
                result = (result < tmp_num) ? tmp_num : result;
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}


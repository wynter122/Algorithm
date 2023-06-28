/*
10개의 수를 입력 받아, 그 중에서 홀수만 더한 값을 출력하는 프로그램을 작성하라.

[제약 사항]
각 수는 0 이상 10000 이하의 정수이다.

[입력]
가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 10개의 수가 주어진다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            int result = 0;
            String numbers = br.readLine();
            st = new StringTokenizer(numbers, " ");

            while (st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                if (num % 2 != 0)
                    result += num;
            }
            System.out.println("#" + tc + " " + result);
        }
    }
}

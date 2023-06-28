/*
어느 고등학교에서 실시한 1000명의 수학 성적을 토대로 통계 자료를 만들려고 한다.
이때, 이 학교에서는 최빈수를 이용하여 학생들의 평균 수준을 짐작하는데, 여기서 최빈수는 특정 자료에서 가장 여러 번 나타나는 값을 의미한다.
다음과 같은 수 분포가 있으면,
10, 8, 7, 2, 2, 4, 8, 8, 8, 9, 5, 5, 3
최빈수는 8이 된다.
최빈수를 출력하는 프로그램을 작성하여라 (단, 최빈수가 여러 개 일 때에는 가장 큰 점수를 출력하라).

[제약 사항]
학생의 수는 1000명이며, 각 학생의 점수는 0점 이상 100점 이하의 값이다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_12712 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T;
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++){
            int counter[] = new int[101];

            int tc = Integer.parseInt(br.readLine());
            String scores = br.readLine();
            st = new StringTokenizer(scores, " ");

            for (int i = 0; i < 1000; i++)
                counter[Integer.parseInt(st.nextToken())]++;

            int result = 0;
            int max_value = 0;
            for (int i = 0; i < counter.length; i++){
                if (max_value <= counter[i]){
                    result = i;
                    max_value = counter[i];
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}

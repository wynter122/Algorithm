/*
A와 B가 가위바위보를 하였다.
가위는 1, 바위는 2, 보는 3으로 표현되며 A와 B가 무엇을 냈는지 입력으로 주어진다.
A와 B중에 누가 이겼는지 판별해보자. 단, 비기는 경우는 없다.

[입력]
입력으로 A와 B가 무엇을 냈는지 빈 칸을 사이로 주어진다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1936 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        st =  new StringTokenizer(input, " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        String result = (A > B) ? "A" : "B";
        System.out.println(result);
    }
}

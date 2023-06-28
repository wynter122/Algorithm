/*
하나의 자연수를 입력 받아 각 자릿수의 합을 계산하는 프로그램을 작성하라.

[제약 사항]
자연수 N은 1부터 9999까지의 자연수이다. (1 ≤ N ≤ 9999)

[입력]
입력으로 자연수 N이 주어진다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int result = 0;
        int div = 1000;

        while(N != 0){
            int quotient = N / div;
            int remainder = N % div;

            result += quotient;
            N = remainder;

            div /= 10;
        }

        System.out.println(result);
    }
}

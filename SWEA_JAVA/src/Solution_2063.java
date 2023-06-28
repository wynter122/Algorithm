/*
중간값은 통계 집단의 수치를 크기 순으로 배열 했을 때 전체의 중앙에 위치하는 수치를 뜻한다.
입력으로 N 개의 점수가 주어졌을 때, 중간값을 출력하라.

[예제]
N이 9 이고, 9개의 점수가 아래와 같이 주어질 경우,
85 72 38 80 69 65 68 96 22
69이 중간값이 된다.

[제약 사항]
1. N은 항상 홀수로 주어진다.
2. N은 9이상 199 이하의 정수이다. (9 ≤ N ≤ 199)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution_2063 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        String numbers = br.readLine();
        st = new StringTokenizer(numbers, " ");

        int i = 0;
        while(st.hasMoreTokens()){
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        Arrays.sort(arr);
        int result = arr[arr.length/2];

        System.out.println(result);
    }
}

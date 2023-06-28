/*
N 개의 숫자로 구성된 숫자열 Ai (i=1~N) 와 M 개의 숫자로 구성된 숫자열 Bj (j=1~M) 가 있다.
Ai 나 Bj 를 자유롭게 움직여서 숫자들이 서로 마주보는 위치를 변경할 수 있다.
단, 더 긴 쪽의 양끝을 벗어나서는 안 된다.
서로 마주보는 숫자들을 곱한 뒤 모두 더할 때 최댓값을 구하라.
N 과 M은 3 이상 20 이하이다.

[입력]
가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스의 첫 번째 줄에 N 과 M 이 주어지고,
두 번째 줄에는 Ai,
세 번째 줄에는 Bj 가 주어진다.
*/

import java.util.Scanner;

class Solution_1959
{
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int arr_A[] = new int[N];
            int arr_B[] = new int[M];

            for (int n = 0; n < N; n++)
                arr_A[n] = sc.nextInt();

            for (int m = 0; m < M; m++)
                arr_B[m] = sc.nextInt();

            int longer[], shorter[];

            if (arr_A.length > arr_B.length) {
                longer = arr_A;
                shorter = arr_B;
            } else {
                longer = arr_B;
                shorter = arr_A;
            }

            int result = 0;

            for (int i = 0; i <= longer.length - shorter.length; i++){
                int temp_sum = 0;

                for (int j = 0; j < shorter.length; j++)
                    temp_sum += longer[i+j] * shorter[j];

                result = (result < temp_sum) ? temp_sum : result;
            }

            System.out.println("#" + test_case + " " + result);

        }
    }
}
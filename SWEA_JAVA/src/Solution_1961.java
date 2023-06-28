import java.util.Scanner;
import java.util.ArrayList;

/*
N x N 행렬이 주어질 때,
시계 방향으로 90도, 180도, 270도 회전한 모양을 출력하라.

[제약 사항]
N은 3 이상 7 이하이다.

[입력]
가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
각 테스트 케이스의 첫 번째 줄에 N이 주어지고,
다음 N 줄에는 N x N 행렬이 주어진다.
*/

public class Solution_1961 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            int arr[][] = new int[N][N];
            ArrayList<Integer>[] result = new ArrayList[N];

            for (int i = 0; i < N; i++)
                result[i] = new ArrayList<Integer>();



            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++)
                    arr[i][j] = sc.nextInt();
            }

            // 90
            int result_idx = 0;
            for (int j = 0; j < N; j++) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = N-1; i >= 0; i--)
                    tmp.add(arr[i][j]);

                result[result_idx] = tmp;
                result_idx++;
            }

            // 180
            result_idx = 0;
            for (int i = N-1; i >= 0; i--){
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int j = N-1; j >= 0; j--)
                    tmp.add(arr[i][j]);

                result[result_idx].addAll(tmp);
                result_idx++;
            }

            // 270
            result_idx = 0;
            for (int j = N-1; j >= 0; j--){
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < N; i++)
                    tmp.add(arr[i][j]);

                result[result_idx].addAll(tmp);
                result_idx++;
            }

            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N*3; j++){
                    if ((j != 0) && (j % N == 0))
                        System.out.print(" ");
                    System.out.print(result[i].get(j));
                }
                System.out.println();
            }
        }
    }
}

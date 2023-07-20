/*
창용 마을에는 N명의 사람이 살고 있다.
사람은 편의상 1번부터 N번 사람까지 번호가 붙어져 있다고 가정한다.
두 사람은 서로를 알고 있는 관계일 수 있고, 아닐 수 있다.
두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면,
이러한 사람들을 모두 다 묶어서 하나의 무리라고 한다.
창용 마을에 몇 개의 무리가 존재하는지 계산하는 프로그램을 작성하라.


[입력]
첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 각각 창용 마을에 사는 사람의 수와 서로를 알고 있는 사람의 관계 수를 나타내는
두 정수 N, M(1 ≤ N ≤ 100, 0 ≤ M ≤ N(N-1)/2) 이 공백 하나로 구분되어 주어진다.
이후 M개의 줄에 걸쳐서 서로를 알고 있는 두 사람의 번호가 주어진다.
같은 관계는 반복해서 주어지지 않는다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            String s = br.readLine();
            st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int group[] = new int[N+1];

            for (int n = 1; n <= N; n++)
                group[n] = n;

            for (int m = 0; m < M; m++) {
                String relationship = br.readLine();
                st = new StringTokenizer(relationship);

                int tmp_1 = Integer.parseInt(st.nextToken());
                int tmp_2 = Integer.parseInt(st.nextToken());

                int first, second;
                if (tmp_1 < tmp_2) {
                    first = tmp_1;
                    second = tmp_2;
                } else {
                    first = tmp_2;
                    second = tmp_1;
                }

                if (group[first] == group[second])
                    continue;
                else {
                    int oldGroup = group[second];
                    for (int i = 1; i <= N; i++){
                        if (group[i] == oldGroup)
                            group[i] = group[first];
                    }
                }
            }

            int result = 0;
            for (int i = 1; i <= N; i++){
                if (group[i] == i)
                    result++;
            }

            System.out.println("#" + t + " " + result);
        }
    }
}

/*

2

6 5
1 2
2 5
5 1
3 4
4 6

6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3

6 5
1 2
2 3
3 4
4 5
5 6

1
5 4
1 2
4 5
5 3
2 4

7 6
7 10
9 8
9 1
10 1
 */
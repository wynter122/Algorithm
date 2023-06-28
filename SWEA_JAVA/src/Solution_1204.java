/*
N x N 배열 안의 숫자는 해당 영역에 존재하는 파리의 개체 수를 의미한다.
아래는 N=5 의 예이다.
파리 킬러 스프레이를 한 번만 뿌려 최대한 많은 파리를 잡으려고 한다. 스프레이의 노즐이 + 형태로 되어있어, 스프레이는 + 혹은 x 형태로 분사된다. 스프레이를 M의 세기로 분사하면 노즐의 중심이 향한 칸부터 각 방향으로 M칸의 파리를 잡을 수 있다.
다음은 M=3 세기로 스프레이르 분사한 경우 파리가 퇴치되는 칸의 예로, +또는 x 중 하나로 분사된다. 뿌려진 일부가 영역을 벗어나도 상관없다.
한 번에 잡을 수 있는 최대 파리수를 출력하라.

[제약 사항]
1. N 은 5 이상 15 이하이다.
2. M은 2 이상 N 이하이다.
3. 각 영역의 파리 갯수는 30 이하 이다.

2
5 2
1 3 3 6 7
8 13 9 12 8
4 16 11 12 6
2 4 1 23 2
9 13 4 7 3
6 3
29 21 26 9 5 8
21 19 8 0 21 19
9 24 2 11 4 24
19 29 1 0 21 19
10 29 6 18 4 3
29 11 15 3 3 29
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1204 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++){
            String s = br.readLine();
            st = new StringTokenizer(s, " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int arr[][] = new int[N][N];
            for (int i = 0; i < N; i++){
                s = br.readLine();
                st = new StringTokenizer(s, " ");
                for (int j = 0; j < N; j++)
                    arr[i][j] = Integer.parseInt(st.nextToken());
            }

            int result = 0;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    int tmp_result = getResult(arr, M, i, j);
                    if (tmp_result > result)
                        result = tmp_result;
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    public static int getResult(int[][] arr, int M, int i, int j){
        // vertical & horizon
        int vh_counter = 0;

        // fix i
        for (int h = j-M+1; h < j+M; h++){
            if ((h < 0) || (h >= arr[i].length))
                continue;
            vh_counter += arr[i][h];
        }
        // fix j
        for (int v = i-M+1; v < i+M; v++){
            if ((v < 0) || (v >= arr[i].length))
                continue;
            vh_counter += arr[v][j];
        }
        vh_counter -= arr[i][j];

        // diagonal
        int dg_counter = 0;

        // direction : \
        int v = i - M+1;
        int h = j - M+1;
        while (true){
            if ((v >= i+M) && (h >= j+M))
                break;
            if (((v < 0) || (v >= arr[i].length)) || ((h < 0) || (h >= arr[i].length))){
                v++;
                h++;
                continue;
            }
            dg_counter += arr[v][h];
            v++;
            h++;
        }

        // direction : /
        v = i - (M-1);
        h = j + (M-1);
        while (true){
            if ((v >= i+M) && (h <= j-M))
                break;

            if (((v < 0) || (v >= arr[i].length)) || ((h < 0) || (h >= arr[i].length))){
                v++;
                h--;
                continue;
            }
            dg_counter += arr[v][h];
            v++;
            h--;
        }

        dg_counter -= arr[i][j];

        int answer = (vh_counter > dg_counter) ? vh_counter : dg_counter;

        return answer;
    }
}

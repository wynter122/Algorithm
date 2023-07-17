/*
로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

로봇 청소기가 있는 방은
N x M 크기의 직사각형으로 나타낼 수 있으며,
1 X 1 크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다.
청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다.
방의 각 칸은 좌표 (r, c)로 나타낼 수 있고,
가장 북쪽 줄의 가장 서쪽 칸의 좌표가 (0, 0), 가장 남쪽 줄의 가장 동쪽 칸의 좌표가 (N-1, M-1)이다.
즉, 좌표 (r, c)는 북쪽에서 (r+1)번째에 있는 줄의 서쪽에서 (c+1)번째 칸을 가리킨다.
처음에 빈 칸은 전부 청소되지 않은 상태이다.

로봇 청소기는 다음과 같이 작동한다.

1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
    - 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    - 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
    - 반시계 방향으로 90도 회전한다.
    - 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
    - 1번으로 돌아간다.

첫째 줄에 방의 크기 N과 M이 입력된다. (3 <= N, M <= 50)
둘째 줄에 처음에 로봇 청소기가 있는 칸의 좌표 r, c 와 처음에 로봇 청소기가 바라보는 방향 d가 입력된다.
d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽을 바라보고 있는 것이다.

셋째 줄부터 N개의 줄에 각 장소의 상태를 나타내는 N x M개의 값이 한 줄에 M개씩 입력된다.
i번째 줄의 j번째 값은 칸 i, j의 상태를 나타내며, 이 값이 0인 경우 (i, j)가 청소되지 않은 빈 칸이고,
1인 경우 (i, j)에 벽이 있는 것이다. 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다.
로봇 청소기가 있는 칸은 항상 빈 칸이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Cleaner{
    int r;
    int c;
    int d;

    public Cleaner(int r, int c, int d){
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public boolean moveBack(int[][] map){
        boolean moveResult = false;

        int tmpR = this.r;
        int tmpC = this.c;

        switch (this.d) {
            case 0 :
                tmpR++;
                break;
            case 1 :
                tmpC--;
                break;
            case 2 :
                tmpR--;
                break;
            case 3 :
                tmpC++;
                break;
        }

        if (map[tmpR][tmpC] == 0){
            this.r = tmpR;
            this.c = tmpC;
            moveResult = true;
        }
        return moveResult;
    }

    public void moveForward(int[][] map, boolean[][] check){
        boolean moveResult = false;

        int tmpR = this.r;
        int tmpC = this.c;

        switch (this.d) {
            case 0 :
                tmpR--;
                break;
            case 1 :
                tmpC++;
                break;
            case 2 :
                tmpR++;
                break;
            case 3 :
                tmpC--;
                break;
        }

        if ((map[tmpR][tmpC] == 0) && (check[tmpR][tmpC] == false)){
            this.r = tmpR;
            this.c = tmpC;
        }
    }

    public void turn(){
        if (this.d == 0)
            this.d = 3;
        else
            this.d--;
    }
}

public class Main_14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Cleaner cleaner = new Cleaner(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int map[][] = new int[N][M];
        boolean check[][] = new boolean[N][M];

        for (int n = 0; n < N; n++){
            String s = br.readLine();
            st = new StringTokenizer(s);
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 1)
                    check[n][m] = true;
            }
        }

        int r_list[] = {-1, 0, 1, 0};
        int c_list[] = {0, 1, 0, -1};

        int cnt = 0;
        while(true){
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (check[cleaner.r][cleaner.c] == false) {
                check[cleaner.r][cleaner.c] = true;
                cnt++;
            }

            boolean isNotCleand = false;
            for (int i = 0; i < 4; i++){
                int tmp_r = cleaner.r + r_list[i];
                int tmp_c = cleaner.c + c_list[i];

                if (check[tmp_r][tmp_c] == false) {
                    isNotCleand = true;
                    break;
                }
            }

            //2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
            if (!isNotCleand){
                //    - 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                boolean mvBackResult = cleaner.moveBack(map);

                //    - 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                if (!mvBackResult)
                    break;
            } else {    //3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
                cleaner.turn(); // 반시계 방향으로 90도 회전한다.
                cleaner.moveForward(map,check); //바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
            }
        }

        System.out.println(cnt);
    }
}
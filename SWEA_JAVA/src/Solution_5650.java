import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node {
    int x, y;
    int n;

    public Node(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }
}

class WormholeContainer {
    Node wormholeA, wormholeB;

    public WormholeContainer(Node wormholeA){
        this.wormholeA = wormholeA;
    }

    public void setWormholeB(Node wormholeB){
        this.wormholeB = wormholeB;
    }

    public Node getPair(int x, int y){
        if (x == wormholeA.x && y == wormholeA.y)
            return wormholeB;
        else
            return wormholeA;
    }
}

class Ball {
    int x, y;
    int dir; // 0 (상), 1 (우), 2 (하), 3 (좌)
    int score;

    public Ball(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
        score = 0;
    }

    public void crushWall(){
        switch (dir){
            case 0: dir = 2; break;
            case 2: dir = 0; break;
            case 1: dir = 3; break;
            case 3: dir = 1; break;
        }
        score++;
    }

    public void crushBlock(int blockType){
        switch (blockType){
            case 1:
                switch (dir){
                    case 0: dir = 2; break;
                    case 1: dir = 3; break;
                    case 2: dir = 1; break;
                    case 3: dir = 0; break;
                }
                break;
            case 2:
                switch (dir){
                    case 0: dir = 1; break;
                    case 1: dir = 3; break;
                    case 2: dir = 0; break;
                    case 3: dir = 2; break;
                }
                break;
            case 3:
                switch (dir){
                    case 0: dir = 3; break;
                    case 1: dir = 2; break;
                    case 2: dir = 0; break;
                    case 3: dir = 1; break;
                }
                break;
            case 4:
                switch (dir){
                    case 0: dir = 2; break;
                    case 1: dir = 0; break;
                    case 2: dir = 3; break;
                    case 3: dir = 1; break;
                }
                break;
        }
        score++;
    }
}

public class Solution_5650 {
    // 상, 우, 하, 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer, WormholeContainer> wormholeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine().trim());
            wormholeList = new HashMap<>();

            // 게임판 생성
            int[][] map = new int[N][N];
            for (int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < N; m++){
                    int val = Integer.parseInt(st.nextToken());
                    map[n][m] = val;

                    switch (val){
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:    // 웜홀
                            if (!wormholeList.containsKey(val)) {         // 짝 없는 웜홀
                                WormholeContainer container = new WormholeContainer(new Node(n, m, val));
                                wormholeList.put(val, container);
                            } else             // 짝 발견
                                wormholeList.get(val).setWormholeB(new Node(n, m, val));
                    }
                }
            }

            // 시뮬레이션
            int result = 0;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (map[i][j] == 0){
                        for (int dir = 0; dir <= 3; dir++) {
                            int tmpResult = Simulation(new Ball(i, j, dir), map);
                            result = Math.max(result, tmpResult);
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + result);
        }
    }

    static int Simulation(Ball ball, int[][] map){
        // 초기값 설정
        int initX = ball.x;
        int initY = ball.y;

        boolean init = false;
        WHILE:
        while(true){
            // 초기 위치로 돌아왔을 때
            if (init && initX == ball.x && initY == ball.y)
                break;

            init = true;
            int nx = ball.x + dx[ball.dir];
            int ny = ball.y + dy[ball.dir];

            // 벽에 부딪혔을 때
            if ((nx < 0 || nx >= map.length) || (ny < 0 || ny >= map.length))
                // 방향 바꾸기
                ball.crushWall();
             else {
                switch (map[nx][ny]) {
                    // 블록에 부딪힘
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        ball.crushBlock(map[nx][ny]);
                        break;

                    case 5:
                        ball.crushWall();
                        break;

                    // 웜홀 만남
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        Node pair = wormholeList.get(map[nx][ny]).getPair(nx, ny);
                        nx = pair.x;
                        ny = pair.y;
                        break;

                    case -1:                    // 블랙홀 만남
                        break WHILE;
                }
            }

            ball.x = nx;
            ball.y = ny;
        }
        return ball.score;
    }
}

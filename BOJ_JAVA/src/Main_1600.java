import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y;
    int step;
    int kCnt;

    public Node(int x, int y, int step, int kCnt){
        this.x = x;
        this.y = y;
        this.step = step;
        this.kCnt = kCnt;
    }
}

public class Main_1600 {
    static int[][] map;
    static int[][][] visited;             // [x좌표][y좌표][k 횟수 기록]
    static final int INT_MAX = 9999999;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());            // 말 처럼 이동가능한 횟수

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());           // 열 개수
        int R = Integer.parseInt(st.nextToken());           // 행 개수
        map = new int[R][C];
        visited = new int[R][C][K+1];

        for (int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                for (int k = 0; k <= K; k++)
                    visited[r][c][k] = INT_MAX;
            }
        }

        // BFS
        BFS(K);

        int result = INT_MAX;
        for (int k = 0; k <= K; k++)
            result = Math.min(result, visited[R-1][C-1][k]);

        if (result == INT_MAX)
            System.out.println(-1);
        else
            System.out.println(result);
    }

    static void BFS(int K){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[] kx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] ky = {1, 2, 2, 1, -1, -2, -2, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 0));           // 시작 노드

        while(!queue.isEmpty()){
            Node curr = queue.poll();               // 현재 방문한 노드
            if (visited[curr.x][curr.y][curr.kCnt] <= curr.step)
                continue;

            visited[curr.x][curr.y][curr.kCnt] = curr.step;

            // 인접 노드 탐색
            for (int k = 0; k < 4; k++){
                int nx = curr.x + dx[k];
                int ny = curr.y + dy[k];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)) {          // 범위 체크
                    if (map[nx][ny] == 0 && visited[nx][ny][curr.kCnt] > curr.step+1)
                        queue.add(new Node(nx, ny, curr.step+1, curr.kCnt));
                }
            }

            if (curr.kCnt < K){                         // K 가 남았을 떄
                for (int k = 0; k < 8; k++){
                    int nx = curr.x + kx[k];
                    int ny = curr.y + ky[k];
                    if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){          // 범위 체크
                        if (map[nx][ny] == 0 && visited[nx][ny][curr.kCnt+1] > curr.step+1)
                            queue.add(new Node(nx, ny, curr.step+1, curr.kCnt+1));
                    }
                }
            }
        }
    }
}

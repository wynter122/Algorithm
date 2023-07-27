import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos{
    int x;
    int y;
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_16234 {
    static int[][] graph;
    static boolean[][] visited;
    static int N, L, R;
    static boolean flag = false;                                // 하루동안 연합이 한번이라도 이뤄졌으면 true
    static Queue<Pos> nextQ = new LinkedList<>();               // 현재 연합이 아니라서 다시 체크할 queue
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        visited = new boolean[N][N];
        for (int n = 0; n < N; n++){                    // 나라별 인구수 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                graph[n][j] = Integer.parseInt(st.nextToken());
        }

        Queue<Pos> currQ = new LinkedList<>();               // bfs 수행할 queue
        currQ.add(new Pos(0, 0));
        int day = 0;
        while(true){
            BFS(currQ);                         // BFS -> 나라별로 연합인 나라 체크해서 인구수 업데이트
            if (nextQ.isEmpty()){                   // 하루동안 모든 나라 국경 탐색이 끝났으면
                if (!flag)                          // 연합 성사 여부 체크
                    break;
                day++;                              // 연합이 한번이라도 이뤄졌으면 하루 추가
                nextQ.add(new Pos(0, 0));     // 0, 0 나라 큐에 추가
                for (boolean[] arr : visited)       // 방문배열 초기화
                    Arrays.fill(arr, false);
                flag = false;                       // 연합 여부 플래그 초기화
            }
            currQ.add(nextQ.poll());            // 연합을 알아보지 않은 다음 나라 탐색
        }

        System.out.println(day);
    }

    static void BFS(Queue<Pos> currQ) {
        int[] xList = {-1, 0, 1, 0};
        int[] yList = {0, 1, 0, -1};

        int unionSum = 0;
        Queue<Pos> union = new LinkedList<>();
        while (!currQ.isEmpty()) {
            Pos now = currQ.poll();
            if (visited[now.x][now.y])                  // 이미 연합이 이뤄진 나라라면 패스
                continue;
            unionSum += graph[now.x][now.y];            // 연합 인구수에 더해줌
            union.add(now);                             // 현재 연합인 나라 큐에 추가
            visited[now.x][now.y] = true;               // 방문체크
            for (int k = 0; k < 4; k++) {
                int nx = now.x + xList[k];
                int ny = now.y + yList[k];
                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N)) {       // 범위 체크
                    if (!visited[nx][ny]) {                          // 방문하지 않았으면
                        int diff = Math.abs(graph[now.x][now.y] - graph[nx][ny]);               // 인구 수 차이 체크
                        if (diff >= L && diff <= R)
                            currQ.add(new Pos(nx, ny));         // 인구차가 범위내에 있으면 queue에 추가
                        else          // 인구차가 범위내에 없으면 nextQ에 삽입
                            nextQ.add(new Pos(nx, ny));
                    }
                }
            }
        }
        // 인구이동
        if (union.size() > 1) {
            flag = true;
            int unionResult = unionSum / union.size();
            while(!union.isEmpty()){
                Pos p = union.poll();
                graph[p.x][p.y] = unionResult;
            }
        }
    }
}

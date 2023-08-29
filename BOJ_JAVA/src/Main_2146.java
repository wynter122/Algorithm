import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Node {
    int x, y;
    int d;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int d){
        this(x, y);
        this.d =d;
    }
}

public class Main_2146 {
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int INT_MAX = 100000;
    static int result = INT_MAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];


        // 지도 정보 받기
        for (int i = 0; i < N; i++){
            Arrays.fill(dist[i], INT_MAX);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // DFS 로 땅 덩어리 체크
        int flag = 1;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] == 1 && !visited[i][j])
                    DFS(new Node(i, j), flag++);
            }
        }

        // 방문배열 초기화
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);

        // BFS : 다른 대륙을 만났을 때 최소 다리 개수 구하기
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] != 0)
                    BFS(new Node(i, j, 0));
            }
        }

        System.out.println(result);
    }

    static void DFS(Node start, int flag){
        Stack<Node> stack = new Stack<>();
        stack.add(start);
        visited[start.x][start.y] = true;

        while(!stack.isEmpty()){
            Node curr = stack.pop();

            map[curr.x][curr.y] = flag;

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (map[nx][ny] == 1 && !visited[nx][ny]){
                        stack.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    static void BFS(Node start){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        int startNum = map[start.x][start.y];         // 시작 그룹넘버
        while(!queue.isEmpty()){
            Node curr = queue.poll();

            if (dist[curr.x][curr.y] <= curr.d)         // 이미 최소 거리로 탐색 마친 노드 -> 주변노드 탐색하지 않음
                continue;

            dist[curr.x][curr.y] = curr.d;          // 최소 거리로 갱신

            // 주변 빈칸 확인
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (map[nx][ny] == 0){  // 주변 노드가 빈 칸
                        if (dist[nx][ny] > curr.d + 1)
                            queue.add(new Node(nx, ny, curr.d + 1));

                    }else if (map[nx][ny] != startNum){         // 다른 대륙 도착
                        result = Math.min(result, curr.d);            // 현재 기록이 이전에 발견된 최소 기록보다 작으면 갱신
                    }
                }
            }
        }

    }
}

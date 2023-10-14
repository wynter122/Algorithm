import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    int x, y;
    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_1520 {
    static int[][] graph;
    static int[][] cost;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        cost = new int[N][M];
        visited = new boolean[N][M];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                graph[n][m] = Integer.parseInt(st.nextToken());
        }

        // Recursive DFS 수행
        Node start = new Node(0, 0);
        DFS(start);
        System.out.println(cost[0][0]);
    }
    static int DFS(Node curr){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 목적지에 도달하면 1 반환
        if (curr.x == graph.length-1 && curr.y == graph[curr.x].length-1)
            return 1;

        // 이미 방문한 노드라면 경우의 수 반환
        if (visited[curr.x][curr.y])
            return cost[curr.x][curr.y];

        visited[curr.x][curr.y] = true;

        // 인접 노드 중 방문 가능 노드 탐색
        for (int d = 0; d < 4; d++){
            int nx = curr.x + dx[d];
            int ny = curr.y + dy[d];

            if ((nx >=0 && nx < graph.length) && (ny >= 0 && ny < graph[nx].length)){
                if (graph[nx][ny] < graph[curr.x][curr.y]) {
                    cost[curr.x][curr.y] += DFS(new Node(nx, ny));
                }
            }
        }

        return cost[curr.x][curr.y];
    }
}

/*
4 4
16 9 8 1
15 10 7 2
14 11 6 3
13 12 5 4
 */
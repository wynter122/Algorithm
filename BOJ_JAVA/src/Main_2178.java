import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y;
    int cnt;

    public Node (int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main_2178 {
    static int[][] map;
    static int[][] visited;
    static final int INT_MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            Arrays.fill(visited[n], INT_MAX);
            for (int m = 0; m < M; m++)
                map[n][m] = line[m] - '0';
        }

        // BFS
        BFS();
        System.out.println(visited[N-1][M-1]);
    }

    static void BFS(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1));               // 0, 0 부터 시작. 시작 노드 카운트 포함

        while (!queue.isEmpty()){
            Node curr = queue.poll();
            if (visited[curr.x][curr.y] <= curr.cnt)
                continue;

            visited[curr.x][curr.y] = curr.cnt;

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (map[nx][ny] == 1 && visited[nx][ny] > curr.cnt) {
                        queue.add(new Node(nx, ny, curr.cnt + 1));

                    }
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_14940 {
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        Node target = null;
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 2)
                    target = new Node(n, m);
            }
        }

        BFS(target);

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++){
                if (map[n][m] != 0 && !visited[n][m])
                    sb.append(-1).append(" ");
                else
                    sb.append(map[n][m]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    static void BFS(Node target){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(target);
        map[target.x][target.y] = 0;
        visited[target.x][target.y] = true;

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (map[nx][ny] == 1 && !visited[nx][ny]){
                        map[nx][ny] = map[curr.x][curr.y] + 1;
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}

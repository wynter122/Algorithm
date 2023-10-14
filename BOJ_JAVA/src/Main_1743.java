import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    int x, y;
    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main_1743 {
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }
        int size = 0;
        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++){
                if (map[n][m] == 1 && !visited[n][m]){
                    int tmpSize = DFS(new Node(n, m));
                    size = Math.max(size, tmpSize);
                }
            }
        }

        System.out.println(size);
    }

    static int DFS(Node start){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Stack<Node> stack = new Stack<>();
        stack.add(start);
        visited[start.x][start.y] = true;

        int size = 0;
        while(!stack.isEmpty()){
            Node curr = stack.pop();
            size++;

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (map[nx][ny] == 1 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        stack.add(new Node(nx, ny));
                    }
                }
            }
        }
        return size;
    }
}

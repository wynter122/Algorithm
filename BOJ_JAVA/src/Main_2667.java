import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Node {
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main_2667 {
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            for (int m = 0; m < N; m++)
                map[n][m] = line[m] - '0';
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] == 1 && !visited[i][j])
                    result.add(DFS(new Node(i, j)));
            }
        }

        result.sort(Comparator.naturalOrder());
        System.out.println(result.size());
        for (int r : result)
            System.out.println(r);
    }

    static int DFS(Node start){
        int[] dx = {-1, 0 , 1, 0};
        int[] dy = {0, 1, 0, -1};

        Stack<Node> stack = new Stack<>();
        stack.add(start);
        visited[start.x][start.y] = true;

        int cnt = 0;
        while(!stack.isEmpty()){
            Node curr = stack.pop();
            cnt++;

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length)) {
                    if (!visited[nx][ny] && map[nx][ny] == 1){
                        stack.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return cnt;
    }
}

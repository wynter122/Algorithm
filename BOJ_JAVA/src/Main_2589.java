import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int i, j;
    int cost;
    public Node(int i, int j, int cost){
        this.i = i;
        this.j = j;
        this.cost = cost;
    }
}

public class Main_2589 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            for (int m = 0; m < M; m++){
                char ch = line[m];
                if (ch == 'W')
                    map[n][m] = 0;
                else if (ch == 'L')
                    map[n][m] = 1;
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == 1){
                    int tmp = BFS(new Node(i, j, 0));
                    result = Math.max(result, tmp);
                }
            }
        }

        System.out.println(result);
    }

    static int BFS(Node start){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.i][start.j] = true;

        int maxResult = 0;
        while(!queue.isEmpty()){
            Node curr = queue.poll();

            maxResult = Math.max(maxResult, curr.cost);
            for (int d = 0; d < 4; d++){
                int ni = curr.i + dx[d];
                int nj = curr.j + dy[d];
                if ((ni >= 0 && ni < map.length) && (nj >= 0 && nj < map[ni].length)){
                    if (map[ni][nj] == 1 && !visited[ni][nj]){
                        visited[ni][nj] = true;
                        queue.add(new Node(ni, nj, curr.cost+1));
                    }
                }
            }
        }
        return maxResult;
    }
}

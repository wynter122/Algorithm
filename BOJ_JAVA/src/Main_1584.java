import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int x, y;
    int lose = 0;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int lose){
        this(x, y);
        this.lose = lose;
    }

    @Override
    public int compareTo(Node node){
        if (this.lose < node.lose)
            return -1;
        else if (this.lose > node.lose)
            return 1;
        else
            return 0;
    }
}

public class Main_1584 {
    static int[][] map = new int[501][501];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 위험, 죽음 구역 체크
        for (int i = 1; i <= 2; i++){
            int N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                markingMap(x1, y1, x2, y2, i);
            }
        }

        // 탐색
        int result = BFS(new Node(0, 0));
        System.out.println(result);
    }

    static int BFS(Node start){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] visited = new int[501][501];
        for (int n = 0; n < visited.length; n++)
            Arrays.fill(visited[n], Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        visited[0][0] = 0;

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length) && map[nx][ny] != 2){
                    int nextLose = curr.lose + map[nx][ny];
                    if (visited[nx][ny] > nextLose){
                        visited[nx][ny] = nextLose;
                        queue.add(new Node(nx, ny, nextLose));
                    }
                }
            }
        }

        if (visited[500][500] == Integer.MAX_VALUE)
            return -1;
        else
            return visited[500][500];
    }

    static void markingMap(int x1, int y1, int x2, int y2, int i){
        int xSmall = Math.min(x1, x2);
        int xLarge = Math.max(x1, x2);
        int ySmall = Math.min(y1, y2);
        int yLarge = Math.max(y1, y2);

        for (int x = xSmall; x <= xLarge; x++){
            for (int y = ySmall; y <= yLarge; y++)
                map[x][y] = i;
        }
    }
}

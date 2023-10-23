import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x, y;
    int cost;

    public Node(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node node){
        if (this.cost < node.cost)
            return -1;
        else if (this.cost > node.cost)
            return 1;
        else
            return 0;
    }
}
public class Main_4485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            int[][] map = new int[N][N];
            for (int n = 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < N; m++)
                    map[n][m] = Integer.parseInt(st.nextToken());
            }

            int result = Dijkstra(map);
            System.out.println("Problem " + t + ": " + result);
            t++;
        }
    }

    static int Dijkstra(int[][] map){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] visited = new int[map.length][map.length];
        for (int n = 0; n < visited.length; n++)
            Arrays.fill(visited[n], Integer.MAX_VALUE);

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.add(new Node(0, 0, map[0][0]));
        visited[0][0] = map[0][0];

        while(!pQ.isEmpty()){
            Node curr = pQ.poll();

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length)){
                    int nextCost = curr.cost + map[nx][ny];
                    if (visited[nx][ny] > nextCost){
                        visited[nx][ny] = nextCost;
                        pQ.add(new Node(nx, ny, visited[nx][ny]));
                    }
                }
            }
        }
        return visited[map.length-1][map.length-1];
    }
}

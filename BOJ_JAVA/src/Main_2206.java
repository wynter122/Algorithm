import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int x, y, broke, cost;
    public Node (int x, int y, int broke, int cost){
        this.x = x;
        this.y = y;
        this.broke = broke;             // 부셔온 벽돌 개수
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
public class Main_2206 {
    static int[][] graph;
    static int[][][] cost;
    static final int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 입력받기
        graph = new int[N][M];
        cost = new int[N][M][2];            // N, M, 1개 부셨을 때 or 안부셨을 때 cost

        for(int n = 0; n < N; n++){
            char[] strArr = br.readLine().toCharArray();
            for (int m = 0; m < M; m++) {
                graph[n][m] = strArr[m] - '0';
                Arrays.fill(cost[n][m], INT_MAX);
            }
        }

        // Dijkstra
        Dijkstra();

        int result = Math.min(cost[N-1][M-1][0], cost[N-1][M-1][1]);
        if (result == INT_MAX)
            System.out.println(-1);
        else
            System.out.println(result);
    }

    static void Dijkstra(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(new Node(0, 0, 0,1));               // 0, 0 부터 시작
        cost[0][0][0] = 1;

        while (!queue.isEmpty()){
            Node curr = queue.poll();
            // 인접 노드 탐색
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < cost.length) && (ny >= 0 && ny < cost[nx].length)){        // 범위 체크
                    Node next = null;
                    if (curr.broke == 0){           // 1도 가능
                        if (graph[nx][ny] == 1 && cost[nx][ny][1] > curr.cost+1){
                            cost[nx][ny][1] = curr.cost+1;
                            next = new Node(nx, ny, curr.broke+1, curr.cost+1);
                        }
                    }

                    if (graph[nx][ny] == 0  && cost[nx][ny][curr.broke] > curr.cost+1){
                        cost[nx][ny][curr.broke] = curr.cost+1;
                        next = new Node(nx, ny, curr.broke, curr.cost+1);
                    }

                    if (next != null)
                        queue.add(next);
                }
            }

        }
    }
}

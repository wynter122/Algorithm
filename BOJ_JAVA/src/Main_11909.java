import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        if (this.cost > node.cost)
            return 1;
        else if (this.cost < node.cost)
            return -1;
        return 0;
    }
}

public class Main_11909 {
    static int[][] graph;
    static int[][] cost;
    static boolean[][] visited;
    static final int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];
        cost = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];
        for (int n = 0; n <= N; n++){
            Arrays.fill(cost[n], INF);
        }
        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                graph[i][j] = Integer.parseInt(st.nextToken());
        }

        // Dijkstra
        dijkstra(N);

        System.out.println(cost[1][1]);

    }

    static void dijkstra(int N){
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(new Node(N, N, 0));

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (visited[curr.x][curr.y])
                continue;

            cost[curr.x][curr.y] = curr.cost > cost[curr.x][curr.y] ? cost[curr.x][curr.y] : curr.cost;     // 최소비용 갱신
            visited[curr.x][curr.y] = true;

            if (curr.x >= 1 && curr.y > 1){                    // i == n -> A[i][j+1]
                int next = graph[curr.x][curr.y-1] - graph[curr.x][curr.y] - 1;
                if (next > 0)
                    next = 0;
                if (!visited[curr.x][curr.y-1])
                    queue.add(new Node(curr.x, curr.y-1, curr.cost + Math.abs(next)));
            }

            if (curr.x > 1 && curr.y >= 1){                    // j == n -> A[i+1][j]
                int next = graph[curr.x-1][curr.y] - graph[curr.x][curr.y] - 1;
                if (next > 0)
                    next = 0;
                if (!visited[curr.x-1][curr.y])
                    queue.add(new Node(curr.x-1, curr.y, curr.cost + Math.abs(next)));
            }

        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int idx;
    int cost;
    public Node (int idx, int cost){
        this.idx = idx;
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

public class Main_1922 {
    static int[][] graph;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());                // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine());                // 간선 수

        graph = new int[N+1][N+1];
        cost = new int[N+1];
        for (int m = 0; m < M; m++){                            // 비용 초기화
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[i][j] = graph[j][i] = c;
        }

        Prim(N);

        int result = 0;
        for (int i = 1; i <= N; i++)
            result += cost[i];
        System.out.println(result);
    }

    static void Prim(int N){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];

        queue.add(new Node(1, 0));              // 1번부터 시작
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (visited[curr.idx])
                continue;

            visited[curr.idx] = true;
            cost[curr.idx] = curr.cost;
            for (int j = 1; j <= N; j++){
                if (!visited[j] && graph[curr.idx][j] != 0)           // 방문하지 않은 노드 중 연결된 노드만
                    queue.add(new Node(j, graph[curr.idx][j]));
            }
        }
    }
}

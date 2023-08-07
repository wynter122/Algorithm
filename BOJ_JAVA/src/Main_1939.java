import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
    int vertex;
    int weight;

    public Edge(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge){
        if (this.weight > edge.weight)
            return -1;
        else if (this.weight < edge.weight)
            return 1;
        return 0;
    }
}

public class Main_1939 {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static int[] cost;
    static int INF = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // init graph
        for (int n = 0; n <= N; n++)
            graph.add(n, new ArrayList<>());
        cost = new int[N+1];

        // edge info
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());           // start
        int T = Integer.parseInt(st.nextToken());           // end

        BFS(S, T, N);

        System.out.println(cost[T]);
    }

    static void BFS(int S, int T, int N){
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(S, INF));
        while(!queue.isEmpty()){
            Edge curr = queue.poll();
            if (visited[curr.vertex])
                continue;

            visited[curr.vertex] = true;
            cost[curr.vertex] = curr.weight;
            for (Edge next : graph.get(curr.vertex)){
                if (!visited[next.vertex] && cost[next.vertex] < next.weight){
                    int w = Math.min(curr.weight, next.weight);
                    queue.add(new Edge(next.vertex, w));
                }
            }
        }
    }
}

/*
5 7
1 2 1
2 5 1
1 3 2
1 5 4
3 5 2
3 4 3
4 5 4
1 5
 */
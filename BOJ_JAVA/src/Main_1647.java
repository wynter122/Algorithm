import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int vertex;
    int cost;

    public Edge(int vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge edge){
        if (this.cost < edge.cost)
            return -1;
        else if (this.cost > edge.cost)
            return 1;
        else
            return 0;
    }
}
public class Main_1647 {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        // Prim 으로 MST 구한 후 최대간선 제거
        System.out.println(Prim(N));
    }

    static int Prim(int N){
        boolean[] visited = new boolean[N+1];
        int sum = 0;
        int max = 0;

        PriorityQueue<Edge> pQueue = new PriorityQueue<>();
        pQueue.add(new Edge(1, 0));             // 1번부터 시작

        while(!pQueue.isEmpty()){
            Edge curr = pQueue.poll();
            if (visited[curr.vertex])
                continue;
            visited[curr.vertex] = true;    // 방문처리
            sum += curr.cost;
            max = Math.max(max, curr.cost);

            // 인접노드 탐색
            for (Edge next : graph.get(curr.vertex)){
                if (!visited[next.vertex])
                    pQueue.add(next);
            }
        }

        return sum-max;
    }
}

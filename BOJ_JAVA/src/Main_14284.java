import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int num;
    int weight;
    public Edge(int num, int weight){
        this.num = num;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge){
        if (this.weight > edge.weight)
            return 1;
        else if (this.weight < edge.weight)
            return -1;
        return 0;
    }
}
public class Main_14284 {
    static List<ArrayList<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] cost;
    static int INF = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        visited = new boolean[N+1];
        cost = new int[N+1];
        for (int n = 0; n <= N; n++){
            graph.add(n, new ArrayList<>());
            cost[n] = INF;
        }


        // 간선 정보 받기
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        // s, t 받기
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // dijkstra 수행
        dijkstra(s, t);

        System.out.println(cost[t]);
    }
    static void dijkstra(int s, int t){
        PriorityQueue<Edge> queue = new PriorityQueue<>();          // 방문할 정점 넣을 큐
        queue.add(new Edge(s, 0));
        cost[s] = 0;        // 출발 정점의 비용 0

        while(!queue.isEmpty()){
            Edge curr = queue.poll();
            if (visited[curr.num])
                continue;
            visited[curr.num] = true;                           // 방문처리
            for (Edge next : graph.get(curr.num)){              // 현재 방문 노드의 인접 노드 탐색
                int newWeight = curr.weight + next.weight;
                if (cost[next.num] > newWeight) {
                    cost[next.num] = newWeight;        // 인접노드의 비용 갱신
                    queue.add(new Edge(next.num, newWeight));
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int n;
    int cost;
    public Node (int n, int cost){
        this.n = n;
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

public class Main_1238 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());               // 노드 개수
        int M = Integer.parseInt(st.nextToken());               // 간선 개수
        int X = Integer.parseInt(st.nextToken());               // 목적지

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, w));
        }

        int[] goCost = new int[N+1];
        int[] backCost = new int[N+1];
        Arrays.fill(backCost, INT_MAX);

        // 가는 비용 dijkstra
        for (int i = 1; i <= N; i++){
            if (i != X)
                goCost[i] = Dijkstra(new Node(i, 0), X);
        }

        // 오는 비용 dijkstra
        Dijkstra(new Node(X, 0), backCost);

        // 비용 합
        int[] totalCost = new int[N+1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            totalCost[i] = goCost[i] + backCost[i];
            result = Math.max(result, totalCost[i]);
        }
        System.out.println(result);
    }

    static int Dijkstra(Node start, int X){
        int[] cost = new int[graph.size()];
        Arrays.fill(cost, INT_MAX);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            if (cost[curr.n] < curr.cost)
                continue;

            cost[curr.n] = curr.cost;           // 최소비용 갱신

            if (curr.n == X)
                break;

            // 인접 노드 탐색 후 방문할 노드 큐에 삽입
            for (Node next : graph.get(curr.n)){
                if (curr.cost + next.cost < cost[next.n]){
                    queue.add(new Node(next.n, curr.cost+next.cost));
                }
            }
        }
        return cost[X];
    }

    static void Dijkstra(Node start, int[] cost){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            if (cost[curr.n] < curr.cost)
                continue;
            cost[curr.n] = curr.cost;
            // 인접 노드 탐색 후 방문할 노드 큐에 삽입
            for (Node next : graph.get(curr.n)){
                if (curr.cost + next.cost < cost[next.n]){
                    queue.add(new Node(next.n, curr.cost+next.cost));
                }
            }
        }
    }
}

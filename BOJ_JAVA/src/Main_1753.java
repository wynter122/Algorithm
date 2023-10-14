import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Node implements Comparable<Node> {
    int n;
    int w;
    public Node (int n, int w){
        this.n = n;
        this.w = w;
    }

    public int compareTo(Node node){
        if (this.w < node.w)
            return -1;
        else if (this.w > node.w)
            return 1;
        else
            return 0;
    }
}

public class Main_1753 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] cost;
    static final int INT_MAX = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());

        // 최소 비용 저장할 배열 초기화
        cost = new int[V+1];
        Arrays.fill(cost, INT_MAX);

        // 간선 정보 저장할 그래프 초기화
        for (int v = 0; v <= V; v++)
            graph.add(new ArrayList<>());

        // 간선정보 받기
        for (int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
        }

        // 다익스트라
        Dijkstra(S);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int v = 1; v <= V; v++){
            if (cost[v] == INT_MAX)
                sb.append("INF").append("\n");
            else
                sb.append(cost[v]).append("\n");
        }

        System.out.println(sb);

    }

    static void Dijkstra(int S){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(S, 0));
        cost[S] = 0;

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            graph.get(curr.n).sort(Comparator.naturalOrder());          // 오름차순으로 정렬
            for (Node next : graph.get(curr.n)){            // 연결 노드 탐색
                if (cost[next.n] > curr.w + next.w){            // 현재까지 계산된 최소 비용이 현재의 최소 비용보다 크면
                    cost[next.n] = curr.w + next.w;                     // 최소비용 갱신
                    queue.add(new Node(next.n, curr.w + next.w));           // 큐에 삽입
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Node implements Comparable<Node>{
    int num;
    int weight;
    public Node(int next, int weight){
        this.num = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        if (this.weight > node.weight)
            return 1;
        else if (this.weight < node.weight)
            return -1;
        return 0;
    }
}

public class Main_5972 {
    static List<ArrayList<Node>> graph = new ArrayList<>();            // 인덱스별로 연결된 노드의 간선 정보 : 오름차순으로 저장
    static boolean[] visited;                               // 방문처리 할 배열
    static int[] cost;                                  // 최소비용 저장할 배열
    static int INF = 1000000000;                                  // 최대 비용 1000 * 50000 이하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 헛간개수, 목적지
        int M = Integer.parseInt(st.nextToken());       // 간선 개수

        cost = new int[N+1];
        Arrays.fill(cost, INF);                     // 비용 배열 무한대로 초기화
        visited = new boolean[N+1];

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        for (int m = 0; m < M; m++){                // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(N);
        System.out.println(cost[N]);
    }

    static void dijkstra(int N){
        PriorityQueue<Node> queue = new PriorityQueue<>();          // 탐색할 노드 삽입할 큐 : 가중치 오름차순으로 저장
        queue.add(new Node(1, 0));          // 1부터 시작
        cost[1] = 0;                                // 1 도달 비용 0으로 세팅

        while(!queue.isEmpty()){
            if (visited[N])     // 목적지에 방문했으면 stop
                break;

            Node curr = queue.poll();           // 방문할 노드 꺼내기
            if (visited[curr.num])              // 이미 방문했으면 pass
                continue;
            visited[curr.num] = true;               // 방문처리
            for (Node next : graph.get(curr.num)){
                if (visited[next.num])
                    continue;
                int nextWeight = cost[curr.num] + next.weight;
                if (cost[next.num] > nextWeight){
                    cost[next.num] = nextWeight;
                    queue.add(new Node(next.num, nextWeight));
                }
            }
        }
    }
}
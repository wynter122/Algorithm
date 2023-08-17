import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Node implements Comparable<Node>{
    int n, delay;
    public Node(int n, int delay){
        this.n = n;
        this.delay = delay;
    }

    @Override
    public int compareTo(Node node){
        if (this.delay < node.delay)
            return -1;
        else if (this.delay > node.delay)
            return 1;
        else
            return 0;
    }
}


public class Main_1005 {
    static int[] delay;
    static int[] inDegree;
    static int[] cost;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 건물 별 소요시간 받기
            delay = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++)
                delay[n] = Integer.parseInt(st.nextToken());

            // 건설 규칙 정보 받기
            for (int n = 0; n <= N; n++)
                graph.add(new ArrayList<>());

            inDegree = new int[N+1];
            cost = new int[N+1];

            for (int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, delay[b]));
                inDegree[b] += 1;               // 진입 차수 추가
            }

            int X = Integer.parseInt(br.readLine());            // 최종 목적 건물 번호

            // Topology
            Topology(X);
            System.out.println(cost[X]);

            delay = null;
            cost = null;
            inDegree = null;
            graph.clear();
        }
    }

    static void Topology (int X){
        // topology sorting
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[delay.length];
        for (int i = 1; i < delay.length; i++){   // 진입 차수가 0인 노드를 큐에 삽입
            if (inDegree[i] == 0)
                queue.add(new Node(i, delay[i]));
        }

        while (!queue.isEmpty()){
            Node curr = queue.poll();       // 방문할 노드 꺼내기

            cost[curr.n] = curr.delay;      // 소요시간 기재

            if (curr.n == X)
                break;

            // 인접 노드 탐색 후 진입차수가 0인 노드 큐에 삽입
            for (Node next : graph.get(curr.n)){
                inDegree[next.n] -= 1;                    // 현재 노드 삭제
                if (inDegree[next.n] == 0)
                    queue.add(new Node(next.n, curr.delay+next.delay));
            }
        }
    }
}

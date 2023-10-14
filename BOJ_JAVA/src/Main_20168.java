import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int n;
    int cost;
    int maximum;

    public Node(int n, int cost){
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

public class Main_20168 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        // 골목 정보 받기
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int result = Dijkstra(N, start, end, money);
        System.out.println(result);
    }

    static int Dijkstra(int N, int start, int end, int money){
        int[] maxCost = new int[N+1];                      // 최대 수금액 저장할 배열
        Arrays.fill(maxCost, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        maxCost[start] = 0;

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            if (curr.n == end)
                continue;

            for (Node next : graph.get(curr.n)){
                if (curr.cost + next.cost <= money){                    // 다음 노드까지 주어진 돈으로 갈 수 있고,
                    int newMax = Math.max(maxCost[curr.n], next.cost);
                    if (maxCost[next.n] > newMax){                      // 다음 노드의 최대 수금액이 현재까지 계산된 최대 수금액보다 크다면
                        maxCost[next.n] = newMax;                           // 다음 노드 최대 수금액 갱신
                        queue.add(new Node(next.n, curr.cost + next.cost));     // 큐에 삽입
                    }
                }
            }
        }

        if (maxCost[end] == Integer.MAX_VALUE)
            return -1;
        else
            return maxCost[end];
    }
}

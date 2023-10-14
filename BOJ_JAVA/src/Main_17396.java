import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int n;
    long cost;

    public Node(int n, long cost){
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

public class Main_17396 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] available;
    static long[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++)
            graph.add(new ArrayList<>());
        available = new int[N];
        distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);


        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            available[n] = Integer.parseInt(st.nextToken());

        available[N-1] = 0;


        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (available[a] == 0 && available[b] == 0)
                graph.get(a).add(new Node(b, t));

            if (available[b] == 0 && available[a] == 0)
                graph.get(b).add(new Node(a, t));
        }

        Dijkstra(new Node(0, 0), N-1);

        if (distance[N-1] == Long.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(distance[N-1]);
    }

    static void Dijkstra(Node start, int target){
        boolean[] visited = new boolean[distance.length];
        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.add(start);
        distance[start.n] = start.cost;

        while(!pQ.isEmpty()){
            Node curr = pQ.poll();

            if (visited[curr.n])
                continue;
            visited[curr.n] = true;

            if (curr.n == target)
                break;

            for (Node next : graph.get(curr.n)){
                if (distance[next.n] > curr.cost + next.cost) {
                    distance[next.n] = curr.cost + next.cost;               // 시간초과 해소 방법 : 큐에 넣을 때 거리 갱신해서 불필요한 탐색 줄이기
                    pQ.add(new Node(next.n, curr.cost + next.cost));
                }
            }
        }
    }
}

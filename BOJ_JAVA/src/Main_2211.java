import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int n;
    int c;
    int l = 0;

    public Node(int n, int c){
        this.n = n;
        this.c = c;
    }

    public Node(int n, int c, int l){
        this(n, c);
        this.l = l;
    }

    public int compareTo(Node node){
        if (this.c < node.c)
            return -1;
        else if (this.c > node.c)
            return 1;
        else
            return 0;
    }
}

class Link {
    int a, b;
    public Link(int a, int b){
        this.a = a;
        this.b = b;
    }
}

public class Main_2211 {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] cost;
    static final int INT_MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        cost = new int[N+1];
        Arrays.fill(cost, INT_MAX);

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        ArrayList<Link> result = Dijkstra(new Node(1, 0));
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (Link link : result)
            sb.append(link.a).append(" ").append(link.b).append("\n");
        System.out.println(sb);
    }

    static ArrayList<Link> Dijkstra(Node start){
        ArrayList<Link> links = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(start);
        cost[start.n] = start.c;            // 최소비용 갱신

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (cost[curr.n] < curr.c)     // 이미 최소 비용 발견
                continue;

            if (curr.l != 0)
                links.add(new Link(curr.l, curr.n));

            for (Node next : graph.get(curr.n)){
                if (cost[next.n] > curr.c + next.c){
                    cost[next.n] = curr.c + next.c;
                    queue.add(new Node(next.n, cost[next.n], curr.n));
                }
            }
        }
        return links;
    }
}

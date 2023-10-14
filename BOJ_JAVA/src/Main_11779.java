import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

class Node implements Comparable<Node>{
    int n;
    int cost;
    ArrayList<Integer> pathNodes;

    public Node(int n, int cost, ArrayList<Integer> pathNodes){
        this.n = n;
        this.cost = cost;
        this.pathNodes = pathNodes;
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
public class Main_11779 {
    static ArrayList<Map<Integer, Integer>> graph = new ArrayList<>();
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for (int n = 0; n <= N; n++)
            graph.add(new HashMap<>());
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (graph.get(a).containsKey(b)){
                if (graph.get(a).get(b) > c)
                    graph.get(a).put(b, c);
            }else
                graph.get(a).put(b, c);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Node result = Dijkstra(start, end);

        StringBuilder sb = new StringBuilder();
        sb.append(result.cost).append("\n");
        sb.append(result.pathNodes.size()).append("\n");
        for (int n : result.pathNodes)
            sb.append(n).append(" ");

        System.out.println(sb);
    }

    static Node Dijkstra(int start, int end){
        boolean[] visited = new boolean[distance.length];

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.add(new Node(start, 0, new ArrayList<>()));
        distance[start] = 0;

        while(!pQ.isEmpty()){
            Node curr = pQ.poll();

            if (visited[curr.n])
                continue;
            visited[curr.n] = true;
            curr.pathNodes.add(curr.n);

            if (curr.n == end)
                return curr;

            for (int next : graph.get(curr.n).keySet()){
                if (!visited[next] && distance[next] > curr.cost + graph.get(curr.n).get(next)){
                    distance[next] = curr.cost + graph.get(curr.n).get(next);
                    ArrayList<Integer> nextArr = new ArrayList<>(curr.pathNodes);
                    pQ.add(new Node(next, distance[next], nextArr));
                }
            }
        }

        return null;
    }
}

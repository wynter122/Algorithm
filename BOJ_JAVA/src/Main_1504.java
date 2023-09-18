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
public class Main_1504 {
    static ArrayList<Map<Integer, Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++)
            graph.add(new HashMap<>());

        long[] allDistance = new long[N+1];
        Arrays.fill(allDistance, Long.MAX_VALUE);

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (graph.get(a).containsKey(b)){
                if (graph.get(a).get(b) > c) {
                    graph.get(a).put(b, c);
                    graph.get(b).put(a, c);
                }
            }else {
                graph.get(a).put(b, c);
                graph.get(b).put(a, c);
            }
        }

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());


        // 1 - first - second - N
        long tmpMid = Dijkstra(new Node(1, 0), N, first);
        checkEnd(tmpMid);
        tmpMid = Dijkstra(new Node(first, tmpMid), N, second);
        checkEnd(tmpMid);
        long fsResult = Dijkstra(new Node(second, tmpMid), N, N);
        checkEnd(fsResult);

        //1 - second - fisrt 구하기
        tmpMid = Dijkstra(new Node(1, 0), N, second);
        tmpMid = Dijkstra(new Node(second, tmpMid), N, first);
        long sfResult = Dijkstra(new Node(first, tmpMid), N, N);

        System.out.println(Math.min(fsResult, sfResult));
    }

    static long Dijkstra(Node start, int N, int end){
        long[] distance = new long[N+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.add(start);
        distance[start.n] = start.cost;

        while(!pQ.isEmpty()){
            Node curr = pQ.poll();

            if (visited[curr.n])
                continue;
            visited[curr.n] = true;

            if (curr.n == end)
                return distance[end];

            for (int next : graph.get(curr.n).keySet()){
                if (!visited[next] && distance[next] > curr.cost + graph.get(curr.n).get(next)){
                    distance[next] = curr.cost + graph.get(curr.n).get(next);
                    pQ.add(new Node(next, distance[next]));
                }
            }
        }
        return distance[end];
    }

    static void checkEnd(long check){
        if (check == Long.MAX_VALUE){
            System.out.println(-1);
            System.exit(0);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;

/* 'a' = 97     -> 1
   ...
   'z' = 122    -> 26
   'A' = 65     -> 27
   ...
   'Z' = 90     -> 52
*/

class Vertex implements Comparable<Vertex> {
    int node;
    int cost;
    public Vertex (int node, int cost){
        this.node = node;
        this.cost = cost;
    }
    @Override
    public int compareTo(Vertex vertex){
        if (this.cost < vertex.cost)
            return -1;
        else if (this.cost > vertex.cost)
            return 1;
        return 0;
    }
}
public class Main_1414 {
    static int[][] graph;
    static int[] cost;
    static boolean[] visited;
    static final int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N];
        cost = new int[N];
        Arrays.fill(cost, INT_MAX);

        // 간선 정보 받기
        int allCost = 0;
        for(int i = 0; i < N; i++){
            char[] info = br.readLine().toCharArray();
            for (int j = 0; j < N; j++){
                String s = String.valueOf(info[j]);
                int value = info[j];
                if (Pattern.matches("[a-z]", s))
                    value -= 96;
                else if(Pattern.matches("[A-Z]", s))
                    value -= 38;
                else if(s.equals("0"))
                    value = 0;

                allCost += value;

                if (value == 0){            // 연결되지 않았을 때 -> [i][j], [j][i] 중 더 큰 값으로 설정
                    value = Math.max(graph[j][i], value);
                } else {                    // 연결되었을 때 -> [i][j], [j][i] 중 0이 아닌 더 작은 값으로 설정
                    if (graph[j][i] != 0 && graph[j][i] < value)
                        value = graph[j][i];
                }
                graph[i][j] = value;
                graph[j][i] = value;
            }
        }

        // Prim
        Prim();

        // 최소비용 계산 후 전체 비용에서 빼기
        boolean flag = true;                // 연결여부체크
        int minimumCost = 0;
        for (int i = 0; i < cost.length; i++){
            if (cost[i] == INT_MAX){
                flag = false;
                break;
            }
            minimumCost += cost[i];
        }

        if (flag)
            System.out.println(allCost - minimumCost);
        else
            System.out.println(-1);

    }

    static void Prim(){
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(0, 0));

        while(!queue.isEmpty()){
            Vertex curr = queue.poll();
            if (visited[curr.node])
                continue;

            visited[curr.node] = true;      // 방문처리
            cost[curr.node] = cost[curr.node] > curr.cost ? curr.cost : cost[curr.node];        // 최소비용 갱신

            // 인접노드 탐색
            for (int next = 0; next < graph.length; next++){
                if (graph[curr.node][next] != 0 && !visited[next])          // 연결되어있고 아직 방문하지 않았으면
                    queue.add(new Vertex(next, graph[curr.node][next]));
            }
        }
    }
}


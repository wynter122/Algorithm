import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge{
    int vertex;
    long cost;

    public Edge(int vertex, long cost){
        this.vertex = vertex;
        this.cost = cost;
    }
}

public class Main_11657 {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static long[] cost;             // 최대 수치 : 정점 (500) * 간선 (6000) * 가중치 (-10000 ~ 10000) -> Underflow 날 수 있으므로 long 으로 선언
    static final int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 정점만큼 인접리스트 행 초기화
        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());
        cost = new long[N+1];
        Arrays.fill(cost, INT_MAX);

        // 간선 정보 받기
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
        }

        // Bellman Ford - 음수 가중치 간선이 있는 경우 사용
        boolean result = BellmanFord(1, N);

        if (result)
            System.out.println(-1);
        else{
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (cost[i] == INT_MAX)
                    sb.append(-1).append("\n");
                else
                    sb.append(cost[i]).append("\n");
            }
            System.out.print(sb);
        }
    }

    static boolean BellmanFord(int start, int N){
        cost[start] = 0;            // 시작 정점의 가중치 = 0으로 초기화

        for (int n = 1; n <= N; n++){                // 정점 개수만큼 반복
            for (int v = 1; v <= N; v++){
                for (Edge next : graph.get(v)){
                    if (cost[v] != INT_MAX && cost[v] + next.cost < cost[next.vertex]){
                        cost[next.vertex] = cost[v] + next.cost;        // 최소 비용으로 갱신
                        if (n == N)         // 정점 개수번째 반복에서 갱신이 이뤄짐 -> 음수 사이클 존재
                            return true;
                    }
                }
            }
        }
        return false;
    }
}

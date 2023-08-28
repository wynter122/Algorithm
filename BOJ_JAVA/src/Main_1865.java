import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int s, e, t;
    public Edge(int s, int e, int t){
        this.s = s;
        this.e = e;
        this.t = t;
    }
}

public class Main_1865 {
    static int[][] graph;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] time;
    static final int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            time = new int[N+1];
            graph = new int[N+1][N+1];
            for (int n = 0; n <= N; n++) {
                time[n] = INT_MAX;
                Arrays.fill(graph[n], INT_MAX);
            }

            // 도로 정보 받기
            for (int m = 0; m < M; m++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if (graph[s][e] > c){
                    graph[s][e] = c;
                    graph[e][s] = c;
                }
            }

            // 웜홀 정보 받기
            for (int w = 0; w < W; w++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = -Integer.parseInt(st.nextToken());

                if(graph[s][e] > c)
                    graph[s][e] = c;
            }

            for (int i = 1; i <= N; i++){
                for (int j = 1; j <= N; j++){
                    if (i != j)
                        edges.add(new Edge(i, j, graph[i][j]));
                }
            }

            // 음수 사이클 체크
            boolean result = BellmanFord(N);

            if (result)
                System.out.println("YES");
            else
                System.out.println("NO");

            // 배열 초기화
            edges = new ArrayList<>();
            time = null;
        }
    }

    static boolean BellmanFord(int N) {
        // 출발 노드 정하기
        time[1] = 0;

        // (정점 개수 -1)+ 1만큼 반복
        for (int v = 1; v <= N; v++){
            // 모든 간선 정보 확인
            for (Edge edge : edges){
                int start = edge.s;
                int end = edge.e;
                int addTime = edge.t;
                if (time[start] != INT_MAX && time[end] > time[start] + addTime){
                    time[end] = time[start] + addTime;
                    if (v == N-1)             // 정점 개수 -1 반복에 새로운 갱신이 되면 -> 음수 사이클 존재
                        return true;
                }
            }
        }
        return false;
    }
}

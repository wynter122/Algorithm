import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Edge {
    int e, t;
    public Edge(int e, int t){
        this.e = e;
        this.t = t;
    }
}

public class Main {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static long[] time;
    static final long INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            // 배열 초기화
            time = new long[N+1];
            Arrays.fill(time, INF);
            for (int n = 0; n <= N; n++)
                graph.add(new ArrayList<>());

            // 도로 정보 받기
            for (int m = 0; m < M; m++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph.get(s).add(new Edge(e, c));
                graph.get(e).add(new Edge(s, c));
            }

            // 웜홀 정보 받기
            for (int w = 0; w < W; w++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = -Integer.parseInt(st.nextToken());

                graph.get(s).add(new Edge(e, c));
            }

            // 음수 사이클 체크
            boolean result = BellmanFord(1, N);

            if (result)
                System.out.println("YES");
            else
                System.out.println("NO");

            // 배열 초기화
            graph = new ArrayList<>();
            time = null;
        }
    }

    static boolean BellmanFord(int start, int N) {
        time[start] = 0;            // 출발 노드 정하기

        for (int i = 1; i <= N; i++){               // 간선 개수만큼 반복
            for (int v = 1; v <= N; v++){               // 매 반복마다 모든 정점으로부터 갱신될 수 있는지 확인
                for (Edge next : graph.get(v)){
                    if (time[v] + next.t < time[next.e]){               // https://www.acmicpc.net/board/view/72995 참고
                        time[next.e] = time[v] + next.t;
                        if (i == N)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}

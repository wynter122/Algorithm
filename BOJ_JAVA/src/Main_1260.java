import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 간선 정보 저장
        boolean[][] graph = new boolean[N+1][N+1];
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            graph[i][j] = true;
            graph[j][i] = true;
        }

        // dfs
        boolean[] visited = new boolean[N+1];
        DFS(graph, V, visited);
        System.out.println();

        // bfs
        ArrayList<Integer> bfs = BFS(graph, N, V);
        for (int n : bfs)
            System.out.print(n + " ");
    }

    static void DFS(boolean[][] graph, int V, boolean[] visited){
        if (visited[V] == true)
            return;
        visited[V] = true;
        System.out.print(V + " ");
        for (int j = 1; j < graph.length; j++){
            if (visited[j] == false && graph[V][j] == true)
                DFS(graph, j, visited);
        }
    }

    static ArrayList<Integer> BFS(boolean[][] graph, int N, int V){
        ArrayList<Integer> bfsResult = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        queue.add(V);   // 첫번째 방문 노드 삽입
        while (!queue.isEmpty()){
            int node = queue.poll();    // 방문할노드 queue에서 꺼냄
            if (visited[node] == true)  // 이미 방문했으면 그냥 삭제
                continue;

            visited[node] = true;       // 방문처리
            bfsResult.add(node);
            for (int n = 1; n < N+1; n++){
                if (graph[node][n] == true && visited[n] == false)
                    queue.add(n);       // 다음에 방문할 노드 queue 삽입
            }
        }
        return bfsResult;
    }
}

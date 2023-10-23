import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1976 {
    static Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 도시 연결 정보
        for (int n = 1; n <= N; n++){
            graph.put(n, new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++){
                int v = Integer.parseInt(st.nextToken());
                if (v == 1)
                    graph.get(n).add(i);
            }
        }
        visited = new boolean[N+1];

        // 여행계획
        ArrayList<Integer> plan = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int m = 0; m < M; m++)
            plan.add(Integer.parseInt(st.nextToken()));

        BFS(plan.get(0));

        String result = "YES";
        for (int node : plan){
            if (!visited[node]){
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }
    
    static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();

            for (int next : graph.get(curr)){
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}

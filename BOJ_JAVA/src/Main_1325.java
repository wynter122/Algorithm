import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1325 {
    static ArrayList<Integer>[] graph;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int n = 1; n <= N; n++)
            graph[n] = new ArrayList<>();
        cost = new int[N+1];
        Arrays.fill(cost, 1);

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            // a 가 b를 신뢰 => b를 해킹하면 a도 해킹
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        for (int n = 1; n <= N; n++)
            BFS(n);

        int maxValue = -1;
        ArrayList<Integer> result = new ArrayList<>();
        for (int n = 1; n <= N; n++){
            int tmp = cost[n];
            if (tmp == maxValue)
                result.add(n);
            else if (tmp > maxValue){
                maxValue = tmp;
                result.clear();
                result.add(n);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result)
            sb.append(r).append(" ");
        System.out.println(sb);
    }

    static void BFS(int start){
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for (int next : graph[curr]){
                if (!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    cost[next]++;
                }
            }
        }
    }

}

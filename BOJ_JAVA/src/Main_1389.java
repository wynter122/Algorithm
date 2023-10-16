import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1389 {
    static Map<Integer, Set<Integer>> graph = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st =  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n <= N; n++)
            graph.put(n, new HashSet<>());

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int resultNum = -1;
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = BFS(N, i);
            if (sum < minSum){
                minSum = sum;
                resultNum = i;
            }
        }

        System.out.println(resultNum);
    }

    static int BFS(int N, int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;

        while(!queue.isEmpty()){
            int curr = queue.poll();

            for (int next : graph.get(curr)){
                if (dist[next] > dist[curr]+1){
                    dist[next] = dist[curr]+1;
                    queue.add(next);
                }
            }
        }

        int sum = 0;
        for (int d = 1; d < dist.length; d++)
            sum += dist[d];

        return sum;
    }
}

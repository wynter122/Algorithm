import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main_1707 {
    static Map<Integer, Set<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new HashMap<>();
            for (int v = 1; v <= V; v++)
                graph.put(v, new HashSet<>());

            for (int e = 0; e < E; e++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            Character[] visited = new Character[V+1];
            boolean result = true;
            for (int i = 1; i < visited.length; i++) {
                if (visited[i] == null) {
                    result = BFS(i, visited);
                    if (!result)
                        break;
                }
            }

            if (result)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    static boolean BFS(int start, Character[] visited) {
        char A = 'A';
        char B = 'B';

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = A;

        while(!queue.isEmpty()){
            int curr = queue.poll();

            for (int next : graph.get(curr)){
                if (visited[next] == null){
                    visited[next] = visited[curr] == A ? B : A;
                    queue.add(next);
                }else {
                    if (visited[next] == visited[curr])
                        return false;
                }
            }
        }
        return true;
    }
}

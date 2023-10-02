import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
    int n;
    int depth;
    public Node(int n, int depth){
        this.n = n;
        this.depth = depth;
    }
}

public class Main_13023 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++)
            graph.add(new ArrayList<>());

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 0번부터 친구 깊이 파악
        int result = 0;

        for (int i = 0; i < N; i++){
            boolean[] visited = new boolean[N];
            result = DFS(new Node(i, 1), visited);
            if (result == 1)
                break;
        }

        System.out.println(result);
    }

    static int DFS(Node curr, boolean[] visited ){
        visited[curr.n] = true;

        if (curr.depth >= 5)
            return 1;

        for (int next : graph.get(curr.n)){
            if (!visited[next]){
                int result = DFS(new Node(next, curr.depth+1), visited);
                if (result == 1)
                    return 1;
            }
        }

        visited[curr.n] = false;
        return 0;
    }
}

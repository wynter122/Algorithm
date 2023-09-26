import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int n, cost;

    public Node(int n, int cost){
        this.n = n;
        this.cost = cost;
    }
}
public class Main_2644 {
    static ArrayList<ArrayList<Integer>> family = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++)
            family.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향으로 관계 저장
            family.get(x).add(y);
            family.get(y).add(x);
        }

        // DFS 시작
        int result = DFS(N, a, b);

        System.out.println(result);
    }

    static int DFS(int N, int x, int y){
        boolean[] visited = new boolean[N+1];
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(x, 0));
        visited[x] = true;

        while(!stack.isEmpty()){
            Node curr = stack.pop();

            if (curr.n == y){
                return curr.cost;
            }

            for (int next : family.get(curr.n)){
                if (!visited[next]){
                    visited[next] = true;
                    stack.push(new Node(next, curr.cost+1));
                }
            }
        }

        return -1;
    }
}

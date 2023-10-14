import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main_2606 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int E = sc.nextInt();

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        for (int e = 0; e < E; e++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int result = DFS(1);
        System.out.println(result);
    }

    static int DFS(int start){
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();
        stack.add(start);

        int cnt = -1;
        while(!stack.isEmpty()){
            int curr = stack.pop();
            if (visited[curr])
                continue;

            visited[curr] = true;
            cnt++;
            for (int next : graph.get(curr)){
                if (!visited[next])
                    stack.add(next);
            }
        }
        return cnt;
    }
}

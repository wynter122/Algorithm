import java.util.*;

public class Main_2533 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();             // 연결된 간선 담을 그래프
    static boolean[] visited;
    static boolean[] isEarly;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        isEarly = new boolean[N+1];
        visited = new boolean[N+1];
        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        // 연결정보
        for (int n = 0; n < N-1; n++){
            int u = sc.nextInt();
            int v = sc.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // DFS
        DFS(1, 1);

        int cnt = 0;
        for (int n = 1; n <= N; n++)
            if (isEarly[n]) cnt++;

        System.out.println(cnt);
    }

    static void DFS(int prev, int root){
        // 방문처리
        visited[root] = true;

        // 연결된 노드 탐색
        for (int child : graph.get(root)) {
           if (!visited[child])             // 방문하지 않은 노드만
               DFS(root, child);
        }

        // 더이상 연결된 노드 없음
        if (isEarly[root])      // 현재 얼리어답터라면 부모 탐색 우선 필요없음
            return;

        // 부모 얼리어답터로 만들기
        if (prev == root){
            for (int c : graph.get(root)){
                if (!isEarly[c]){
                    isEarly[prev] = true;
                    break;
                }
            }
        }
        else
            isEarly[prev] = true;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main_11725 {
    static ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int n = 0; n <= N; n++)
            arrayList.add(new ArrayList<>());
        parent = new int[N+1];
        visited = new boolean[N+1];

        // 간선 정보 받기
        for (int n = 0; n < N-1; n++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            arrayList.get(a).add(b);
            arrayList.get(b).add(a);
        }

        // DFS
        DFS(1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++)
            sb.append(parent[i]).append("\n");

        System.out.print(sb);
    }
    static void DFS(int p, int curr){
        parent[curr] = p;               // 부모 노드 번호 저장
        visited[curr] = true;           // 방문처리

        for (int child : arrayList.get(curr)){
            if (!visited[child])
                DFS(curr, child);
        }
    }
}

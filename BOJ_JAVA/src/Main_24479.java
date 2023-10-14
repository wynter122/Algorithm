import java.io.*;
import java.util.*;

public class Main_24479 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] visited;
    static int cnt = 1;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        // 2차원 배열 초기화
        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());
        // 방문 배열 초기화
        visited = new int[N+1];

        // 간선 정보 받아서 양 노드에 연결정보 저장
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 정렬
        for (int n = 1; n <= N; n++)
            graph.get(n).sort(Comparator.reverseOrder());

        ////// Recursive DFS /////
        // DFS
        // recursiveDFS(S);

        ///// Stack DFS /////
        stackDFS(S);

        for (int v = 1; v < visited.length; v++)
            bw.write(visited[v] + "\n");

        bw.flush();
        bw.close();
    }
    static void recursiveDFS(int curr) {
        // 방문처리
        visited[curr] = cnt++;

        // 인접노드 검색 후 방문
        for (int next : graph.get(curr)){
            if (visited[next] == 0)
                recursiveDFS(next);

        }
    }

    static void stackDFS(int start){
        Stack<Integer> stack = new Stack<>();
        stack.add(start);

        while(!stack.isEmpty()){
            int curr = stack.pop();
            if (visited[curr] != 0)
                continue;

            // 방문처리
            visited[curr] = cnt++;

            // 인접노드 검색
            for (int next : graph.get(curr)){
                if (visited[next] == 0)
                    stack.add(next);
            }
        }
    }
}


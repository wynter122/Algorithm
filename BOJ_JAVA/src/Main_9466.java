import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466 {
    static int[] select;
    static int[] visited;
    static int[] check;
    static final int INT_MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());

            select = new int[N+1];
            check = new int[N+1];
            visited = new int[N+1];             // 방문 순서 저장

            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; n++)
                select[n] = Integer.parseInt(st.nextToken());

            for (int n = 1; n <= N; n++){
                if (visited[n]!=0)               // 이미 싸이클이 이뤄진 번호는 탐색하지 않음
                    continue;
                DFS(n, 1);
            }

            int nonTerm = 0;
            for (int n = 1; n <= N; n++)
                if (check[n] == -1) nonTerm++;

            System.out.println(nonTerm);
        }
    }
    static int DFS(int n, int order) {
        if (visited[n] != 0) {       // 방문한 적 있을 때
            if (check[n] != 0)                   // 이미 싸이클 판별 완료된 지점
                return order;
            else                            // 이번에 처음 싸이클을 판별하는 지점
                return visited[n];              // 싸이클이 시작된 지점 반환
        }

        // 방문한 적 없을 때
        visited[n] = order;        // 방문 순서 저장
        int startCycle = DFS(select[n], order + 1);        // 다음 방문 실행 후 싸이클 시작 지점 반환

        if (order >= startCycle) {               // 탐색 결과 현재 방문 지점이 싸이클에 포함된다면
            check[n] = 1;                           // 팀 설정
            return startCycle;                      // 동일 시작지점 반환
        } else {                                 // 사이클에 포함되지 않는다면
            check[n] = -1;
            return INT_MAX;                               // 무한대 반환
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Node{
        int i; int j;
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class Main_14940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node target = new Node(0, 0);
        int[][] map = new int[N][M];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 2) {
                    target = new Node(n, m);
                }
            }
        }

        // 결과 저장할 배열
        int[][] result = new int[N][M];
        boolean[][] visited = new boolean[N][M];        // 방문처리 할 배열

        Queue<Node> queue = new LinkedList<>();
        queue.add(target);

        int cnt = 0;
        int thisTimeCnt = queue.size();          // 이번 queue 사이즈

        while(!queue.isEmpty()){        // queue 가 빌 때까지
            Node node = queue.poll();
            if (visited[node.i][node.j]){   // 이미 방문했으면 queue 에서 삭제
                thisTimeCnt--;
                if (thisTimeCnt == 0){
                    cnt++;          // 한번 방문 다 했으면 cnt++
                    thisTimeCnt = queue.size();
                }
                continue;
            }

            visited[node.i][node.j] = true; // 방문처리
            result[node.i][node.j] = cnt;   // 거리 기록

            int[] iList = {-1, 0, 1, 0};
            int[] jList = {0, 1, 0, -1};
            for (int k = 0; k < 4; k++){
                int ni = node.i + iList[k];
                int nj = node.j + jList[k];
                if ((ni >= 0 && ni < N) && (nj >= 0 && nj < M)){        // 범위체크
                    if (map[ni][nj] == 1 && !visited[ni][nj]) {     // 1 이고 방문하지 않았으면
                        queue.add(new Node(ni, nj));
                    }
                }
            }
            thisTimeCnt--;      // 이번에 방문해야하는 queue 원소 개수 감소
            if (thisTimeCnt == 0){
                cnt++;          // 한번 방문 다 했으면 cnt++
                thisTimeCnt = queue.size();
            }
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int ans = result[i][j];
                if (ans == 0 && map[i][j] != 2 && map[i][j] != 0)
                    ans = -1;
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int i;
    int j;
    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class Main_7576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] box = new int[N][M];

        int target = 0;
        Queue<Node> queue = new LinkedList<>();
        // 노드 탐색하면서 익은 토마토 Queue에 삽입
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int num = Integer.parseInt(st.nextToken());
                box[n][m] = num;
                if (num == 1)
                    queue.add(new Node(n, m));
                else if (num == 0)
                    target += 1;
            }
        }

        int days = 0;
        boolean[][] visited = new boolean[N][M];    // 방문처리 할 배열
        while (true){
            // 안익은 토마토 == 0개 -> break
            if (target == 0)
                break;

            int todayFind = queue.size(); // 오늘 탐색할 토마토 개수 : queue 에 들어있는 노드 개수
            int todayRipe = 0;            // 오늘 익을 토마토 개수

            while (todayFind > 0){
                Node node = queue.poll();
                todayFind--;

                int[] iList = {-1, 1, 0, 0};
                int[] jList = {0, 0, -1, 1};
                for(int k = 0; k < 4; k++){     // 상하좌우 순회
                    int ni = node.i + iList[k];
                    int nj = node.j + jList[k];

                    if ((ni >= 0 && ni < box.length) && (nj >= 0 && nj < box[ni].length)){  // 범위 체크
                        if (box[ni][nj] == 0 & !visited[ni][nj]){       // 익지 않았고, 아직 방문하지 않음
                            visited[ni][nj] = true;                     // 방문처리
                            box[ni][nj] = 1;
                            queue.add(new Node(ni, nj));                // Queue삽입
                            todayRipe++;                                // 오늘 익을 토마토개수 ++

                        }
                    }
                }
            }

            if (todayRipe == 0){        // 오늘 익을 토마토가 더이상 없으면 -1
                days = -1;
                break;
            }
            target -= todayRipe;    // 앞으로 익어야하는 토마토 개수 갱신
            days++;
        }

        System.out.println(days);
    }
}

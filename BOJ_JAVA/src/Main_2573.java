import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
    int i; int j;
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class Main_2573 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        Queue<Node> queue = new LinkedList<>(); // 빙산 칸 좌표 넣어둘 queue

        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] != 0)
                    queue.add(new Node(n, m));
            }
        }



        int checkCnt = queue.size();        // 빙산 칸 개수
        int years = 0;
        while (true){
            boolean[][] visited = new boolean[N][M];        // 1년 후 녹을 빙산 체크
            // 덩어리 체크 - dfs
            if (checkCnt == 0){     // 더이상 녹을 빙산이 없음
                years = 0;
                break;
            }

            if(DFS(queue.peek(), map, queue.size()))      // dfs 결과 덩어리 갈라짐
                break;

            int meltCnt = 0;        // 1년동안 녹을 빙산 개수 체크
            while (checkCnt > 0) {          // 1년치 빙산만 체크
                Node curr = queue.poll();       // 방문할 빙산 꺼내기
                visited[curr.i][curr.j] = true; // 방문체크
                checkCnt--;

                // 상하좌우 0 개수 체크
                int zeroCnt = 0;

                int[] iList = {-1, 0, 1, 0};
                int[] jList = {0, 1, 0, -1};
                for (int k = 0; k < 4; k++) {
                    int ni = curr.i + iList[k];
                    int nj = curr.j + jList[k];
                    if ((ni >= 0 && ni < N) && (nj >= 0 && nj < M) && !visited[ni][nj]){        // 좌표 범위체크
                        if (map[ni][nj] == 0)
                            zeroCnt++;
                    }
                }

                if (zeroCnt > 0){           // 주변 0 개수만큼 --
                    meltCnt++;              // 1년간 녹은 빙산수 ++
                    map[curr.i][curr.j] -= zeroCnt;
                    if (map[curr.i][curr.j] < 0)
                        map[curr.i][curr.j] = 0;
                }

                if (map[curr.i][curr.j] > 0)
                    queue.add(new Node(curr.i, curr.j));
            }
            checkCnt = queue.size();
            years++;
        }



        System.out.println(years);
    }

    static boolean DFS(Node firstNode, int[][] map, int queueSize) {
        int[] iList = {-1, 0, 1, 0};
        int[] jList = {0, 1, 0, -1};

        boolean[][] visited = new boolean[map.length][map[0].length];       // 방문처리할 2차원 배열
        Stack<Node> stack = new Stack<>();
        stack.push(firstNode);

        int visitCnt = 0;
        while (!stack.isEmpty()) {           // stack 모두 비면 종료
            Node node = stack.pop();   // 방문할 노드 꺼내기
            if (visited[node.i][node.j])    // 이미 방문했으면 stack 에서 삭제만
                continue;

            visited[node.i][node.j] = true;     // 방문처리
            visitCnt++;
            for (int k = 0; k < 4; k++) {
                int ni = node.i + iList[k];
                int nj = node.j + jList[k];
                if ((ni >= 0 && ni < map.length) && (nj >= 0 && nj < map[ni].length)) {        // 좌표 범위체크
                    if (map[ni][nj] != 0 && !visited[ni][nj])   // 주변 노드가 0이 아니고 방문하지 않았다면 stack에 추가
                        stack.push(new Node(ni, nj));
                }
            }
        }

        if (visitCnt == queueSize)
            return false;
        else
            return true;
    }
}

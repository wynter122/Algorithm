import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_2638 {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Node> innerSpace = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        Queue<Node> cheese = new LinkedList<>();        // 치즈 넣을 큐
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 1)
                    cheese.add(new Node(n, m));
            }
        }

        // 치즈 내부 빈공간 -> 2로 변경
        checkSpace(N, M);

        // 치즈 녹는 시뮬레이션
        int hours = 0;
        while(!cheese.isEmpty()) {
            hours++;
            cheese = melt(cheese);

            while(!innerSpace.isEmpty()){
                Node inner = innerSpace.poll();
                if (map[inner.x][inner.y] == 2) {
                    map[inner.x][inner.y] = 0;
                    DFS(inner);
                }
            }
        }

        System.out.println(hours);
    }

    static void checkSpace(int N, int M){
        boolean[][] visited = new boolean[N][M];
        Queue<Node> resultQueue = new LinkedList<>();

        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++){
                if (map[n][m] == 0 && !visited[n][m]) {
                    boolean checkResult = BFS(new Node(n, m), visited, resultQueue);
                    if (checkResult){
                        while(!resultQueue.isEmpty()){
                            Node curr = resultQueue.poll();
                            map[curr.x][curr.y] = 2;
                        }
                    }else
                        resultQueue.clear();
                }
            }
        }
    }

    static Queue<Node> melt(Queue<Node> queue){
        Queue<Node> nextQueue = new LinkedList<>();
        boolean[][] wasCheese = new boolean[map.length][map[0].length];

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            wasCheese[curr.x][curr.y] = true;             // 치즈였던 애 방문표시

            int zeroCnt = 0;
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[0].length)){
                    if (map[nx][ny] == 0 && !wasCheese[nx][ny])
                        zeroCnt++;
                }
            }

            if (zeroCnt >= 2) {
                map[curr.x][curr.y] = 0;

                // inner space 체크
                for (int d = 0; d < 4; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];

                    if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[0].length)) {
                        if (map[nx][ny] == 2)
                            innerSpace.add(new Node(nx, ny));
                    }
                }
            } else
                nextQueue.add(curr);
        }

        return nextQueue;
    }

    static boolean BFS(Node start, boolean[][] visited, Queue<Node> resultQueue){
        boolean result = true;      // 벽을 한번이라도 만나면 false -> 외부공간

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            resultQueue.add(curr);

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[0].length)){
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny));
                    }
                }else
                    result = false;
            }
        }

        return result;
    }

    static void DFS(Node start){
        Stack<Node> stack = new Stack<>();
        stack.add(start);

        while(!stack.isEmpty()){
            Node curr = stack.pop();

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[0].length)) {
                    if (map[nx][ny] == 2) {
                        map[nx][ny] = 0;
                        stack.add(new Node(nx, ny));
                    }
                }
            }
        }

    }
}

/*
8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0
 */
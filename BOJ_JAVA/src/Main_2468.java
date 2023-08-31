import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_2468 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        // 지도 받기
        int rain = 0;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rain = Math.max(rain, map[i][j]);
            }
        }

        // 강수량 N-1 부터 DFS로 영역 확인
        rain--;
        int result = 0;
        while(rain >= 0){
            int tmp = DFS(rain--);
            result = Math.max(result, tmp);
        }

        System.out.println(result);
    }

    static int DFS(int rain){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited = new boolean[map.length][map.length];

        int result = 0;
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map.length; j++){
                if (map[i][j] > rain && !visited[i][j]){
                    result++;

                    Stack<Node> stack = new Stack<>();
                    stack.add(new Node(i, j));
                    visited[i][j] = true;
                    while(!stack.isEmpty()){
                        Node curr = stack.pop();
                        for (int d = 0; d < 4; d++){
                            int nx = curr.x + dx[d];
                            int ny = curr.y + dy[d];

                            if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length)){
                                if (map[nx][ny] > rain && !visited[nx][ny]){
                                    stack.add(new Node(nx, ny));
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }

                }
            }
        }

        return result;
    }
}

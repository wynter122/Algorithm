import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y;
    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_14502 {
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        ArrayList<Node> virus = new ArrayList<>();
        ArrayList<Node> blank = new ArrayList<>();
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] == 2)         // 바이러스 칸 정보 추가
                    virus.add(new Node(n, m));
                if (map[n][m] == 0)         // 빈 칸 정보 추가
                    blank.add(new Node(n, m));
            }
        }

        int result = 0;

        // 벽세우기
        for (int i = 0; i < blank.size()-2; i++){
            map[blank.get(i).x][blank.get(i).y] = 1;
            for (int j = i+1; j < blank.size()-1; j++){
                map[blank.get(j).x][blank.get(j).y] = 1;
                for (int k = j+1; k < blank.size(); k++){
                    map[blank.get(k).x][blank.get(k).y] = 1;

                    int[][] tmpMap = new int[N][M];
                    for (int t = 0; t < map.length; t++)
                        tmpMap[t] = Arrays.copyOf(map[t], M);

                    // BFS : 바이러스 퍼트리기
                    for (Node v : virus)
                        BFS(tmpMap, v);

                    // 빈칸 개수 카운트
                    int tmpResult = count(tmpMap);
                    result = Math.max(result, tmpResult);

                    // 롤백
                    map[blank.get(k).x][blank.get(k).y] = 0;
                }
                map[blank.get(j).x][blank.get(j).y] = 0;
            }
            map[blank.get(i).x][blank.get(i).y] = 0;
        }

        System.out.println(result);
    }

    static void BFS(int[][] tmpMap, Node v){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Node> queue = new LinkedList<>();
        queue.add(v);

        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < tmpMap.length) && (ny >= 0 && ny < tmpMap[nx].length)) {
                    if (tmpMap[nx][ny] == 0) {
                        tmpMap[nx][ny] = 2;
                        queue.add(new Node(nx, ny));
                    }
                }
            }
        }
    }

    static int count(int[][] tmpMap){
        int result = 0;
        for (int i = 0; i < tmpMap.length; i++){
            for (int j = 0; j < tmpMap[i].length; j++){
                if (tmpMap[i][j] == 0)
                    result++;
            }
        }
        return result;
    }
}

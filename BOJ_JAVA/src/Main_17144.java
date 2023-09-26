import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Cleaner{
    Node top;
    Node bottom;

    public void topClean(int[][][] map){
        int prev = 0;

        int r = top.x;
        int c = 0;
        // 하 + 오른쪽
        for (c += 1; c < map[0].length; c++){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        c--;

        // 우 + 위쪽
        for (r-= 1; r >= 0; r--){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        r++;

        // 상 + 왼쪽
        for (c -= 1; c >= 0; c--){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        c++;

        // 좌 + 아래쪽
        for (r += 1; r < top.x; r++){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
    }

    public void bottomClean(int[][][] map){
        int prev = 0;

        int r = bottom.x;
        int c = 0;
        // 상 + 오른쪽
        for (c += 1; c < map[0].length; c++){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        c--;

        // 우 + 아래쪽
        for (r += 1; r < map.length; r++){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        r--;

        // 하 + 왼쪽
        for (c -= 1; c >= 0; c--){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
        c++;

        // 좌 + 위쪽
        for (r -= 1; r > bottom.x; r--){
            int tmp = map[r][c][0];
            map[r][c][0] = prev;
            prev = tmp;
        }
    }
}

public class Main_17144 {
    static Cleaner cleaner;
    static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cleaner = new Cleaner();

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[R][C][2];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c][0] = Integer.parseInt(st.nextToken());
                if (map[r][c][0] == -1) {
                    if (cleaner.top == null)
                        cleaner.top = new Node(r, c);
                    else
                        cleaner.bottom = new Node(r, c);
                }
            }
        }

        Queue<Node> findDusts = new LinkedList<>();
        int t = 0;
        while(true){
            // 미세먼지 확인
            for (int r = 0; r < R; r++){
                for (int c = 0; c < C; c++){
                    if (map[r][c][0] > 0)
                        findDusts.add(new Node(r, c));
                }
            }
            if (t >= T)
                break;

            // 미세먼지 확산
            diffusion(findDusts);

            // 공기청정기 가동
            cleaner.topClean(map);
            cleaner.bottomClean(map);

            t++;
        }

        int result = 0;
        while(!findDusts.isEmpty()){
            Node curr = findDusts.poll();
            result += map[curr.x][curr.y][0];
        }

        System.out.println(result);
    }
    static void diffusion(Queue<Node> queue){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!queue.isEmpty()){
            Node findDust = queue.poll();

            if (map[findDust.x][findDust.y][0] < 5)
                continue;

            int diffusedAmount = map[findDust.x][findDust.y][0] / 5;
            int diffused = 0;
            for (int d = 0; d < 4; d++){
                int nx = findDust.x + dx[d];
                int ny = findDust.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (!((ny == 0) && ((nx == cleaner.top.x || nx == cleaner.bottom.x)))){
                        diffused++;
                        map[nx][ny][1] += diffusedAmount;
                    }
                }
            }

            map[findDust.x][findDust.y][0] -= diffusedAmount * diffused;
        }

        addAmount();
    }

    static void addAmount(){
        for (int r = 0; r < map.length; r++){
            for (int c = 0; c < map[r].length; c++){
                map[r][c][0] += map[r][c][1];
                map[r][c][1] = 0;
            }
        }
    }
}

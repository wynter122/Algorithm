import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x, y;
    int cost;
    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

}

public class Main_1987 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static int max = 0;
    static Map<Integer, Boolean> hashMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int n = 0; n < N; n++)
            map[n] = br.readLine().toCharArray();

        for (int i = 65; i <= 90; i++)
            hashMap.put(i, false);

        DFS(new Node(0, 0, 1));
        System.out.println(max);
    }

    static void DFS(Node curr){
        hashMap.put((int)map[curr.x][curr.y], true);                   // 현재 알파벳 방문처리
        max = Math.max(max, curr.cost);
        
        for (int d = 0; d < 4; d++){
            int nx = curr.x + dx[d];
            int ny = curr.y + dy[d];

            if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[0].length)){
                int nextChar = map[nx][ny];
                if (!hashMap.get(nextChar))
                    DFS(new Node(nx, ny, curr.cost+1));
            }
        }

        hashMap.put((int)map[curr.x][curr.y], false);              // 방문처리 해제
    }
}

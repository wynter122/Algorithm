import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Element {
    int x, y;
    int direction = 0;
    int cnt = 0;

    public Element (int x, int y){
        this.x = x;
        this.y = y;
    }

    public Element (int x, int y, int direction, int cnt){
        this(x, y);
        this.direction = direction;
        this.cnt = cnt;
    }

    public void move(){

    }
}

public class Main_14360 {
    static char[][] map;
    static Element red;
    static Element blue;
    static Element hole;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int N = Integer.parseInt(str.split(" ")[0]);
        int M = Integer.parseInt(str.split(" ")[1]);

        map = new char[N][M];
        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            for (int m = 0; m < M; m++){
                map[n][m] = line[m];
                switch (map[n][m]){
                    case 'R':
                        red = new Element(n, m);
                        break;
                    case 'B':
                        blue = new Element(n, m);
                        break;
                    case 'O':
                        hole = new Element(n, m);
                        break;
                }
            }
        }

        // Red 위치가 0을 찾는 방법 검색 (BFS)
        int cnt = BFS(red);

        // cnt == -1 이라면 끝
//        if (cnt == -1) {
//            System.out.println(cnt);
//            return;
//        }
        System.out.println(cnt);
        // 10번 이하로 가능하다면 Blue 가 같은 방식으로 움직였을 때,
    }

    static int BFS(Element start){
        int[] dx = {0, -1, 0, 1, 0};
        int[] dy = {0, 0, 1, 0, -1};

        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Element> queue = new LinkedList<>();

        start.cnt = 1;
        queue.add(start);

        while(!queue.isEmpty()){
            Element curr = queue.poll();

            if (map[curr.x][curr.y] == 'O') {
//                if (curr.cnt <= 10)
//                    return curr.cnt;
//                else
//                    return -1;
                return curr.cnt;
            }
            // 상하좌우 탐색
            for (int d = 1; d <= 4; d++){           // 1 상, 2 우, 3, 하, 4, 좌
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                    if (!visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'O')){
                        if (curr.direction != 0 && curr.direction != d)                  // 방향이 바뀌면 cnt++
                            queue.add(new Element(nx, ny, d, curr.cnt + 1));
                        else
                            queue.add(new Element(nx, ny, d, curr.cnt));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}

/*
7 7
#######
#R....#
#.....#
#.....#
#.....#
#...O.#
#######
 */
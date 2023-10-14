import java.io.*;
import java.util.*;

class Cell {
    int x, y;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_16946 {
    static int[][] map;
    static int[][][] count;
    static boolean[][] blankVisit;
    static int[][] result;
    static Queue<Cell> bfsQueue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        result = new int[N][M];
        count = new int[N][M][2];
        blankVisit = new boolean[N][M];

        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            for (int m = 0; m < M; m++)
                map[n][m] = (int)line[m] - '0';
        }

        // 0인 칸 BFS 로 묶음 정보 저장
        int num = 1;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == 0 && !blankVisit[i][j]){
                    int cnt = BFS(new Cell(i, j), 0);
                    while(!bfsQueue.isEmpty()){
                        Cell tmp = bfsQueue.poll();
                        count[tmp.x][tmp.y][0] = num;               // 0 뭉치 번호
                        count[tmp.x][tmp.y][1] = cnt;               // 0 개수 저장
                    }
                    num++;
                }
            }
        }

        // 1인 칸 BFS 로 사방탐색
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (map[i][j] == 1)
                    BFS(new Cell(i, j));
            }
        }

        // 출력
        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++)
                sb.append(result[n][m]%10);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int BFS(Cell cell, int cnt){
        Queue<Cell> queue = new LinkedList<>();
        queue.add(cell);

        while(!queue.isEmpty()) {
            Cell curr = queue.poll();
            if (blankVisit[curr.x][curr.y])
                continue;

            blankVisit[curr.x][curr.y] = true;
            bfsQueue.add(curr);
            cnt++;
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)) {
                    if (!blankVisit[nx][ny] && map[nx][ny] == 0)
                        queue.add(new Cell(nx, ny));
                }
            }
        }
        return cnt;
    }

    static void BFS(Cell cell){
        List<Integer> list = new ArrayList<>();                     //*****//

        int ans = 1;
        // 사방 탐색
        for (int d = 0; d < 4; d++){
            int nx = cell.x + dx[d];
            int ny = cell.y + dy[d];

            if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)) {
                int number = count[nx][ny][0];
                int cnt = count[nx][ny][1];

                if (list.contains(number))
                    continue;
                list.add(number);
                ans += cnt;
            }
        }
        result[cell.x][cell.y] = ans;
    }
}

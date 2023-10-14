import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500 {
    static int[][] maze;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                maze[n][m] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++){
                result = Math.max(result, Tetromino1(n, m));
                result = Math.max(result, Tetromino2(n ,m));
                result = Math.max(result, Tetromino3(n ,m));
                result = Math.max(result, Tetromino4(n ,m));
                result = Math.max(result, Tetromino5(n ,m));
            }
        }

        System.out.println(result);
    }

    static int Tetromino1(int x, int y){
        int max = 0;
        int[][] diList = {{0, 0, 0, 0}, {0, 1, 2, 3}};
        int[][] djList = {{0, 1, 2, 3}, {0, 0, 0, 0}};
        for (int d = 0; d < diList.length; d++)
            max = Math.max(max, tetroAssistant(diList[d], djList[d], x, y));

        return max;
    }

    static int Tetromino2(int x, int y){
        int max = 0;
        int[][] diList = {{0, 0, 1, 1}};
        int[][] djList = {{0, 1, 0, 1}};

        for (int d = 0; d < diList.length; d++)
            max = Math.max(max, tetroAssistant(diList[d], djList[d], x, y));

        return max;
    }

    static int Tetromino3(int x, int y){
        int max = 0;
        int[][] diList = {{0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 1, 0, 0}, {0, 1, 2, 0}, {0, 0, 0, 1}, {0, 1, 2, 2}, {0, 1, 1, 1}};
        int[][] djList = {{0, 0, 0, 1}, {0, 0, -1, -2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}, {0, 1, 2, 2}, {0, 0, 0, -1}, {0, 0, 1, 2}};

        for (int d = 0; d < diList.length; d++)
            max = Math.max(max, tetroAssistant(diList[d], djList[d], x, y));

        return max;
    }

    static int Tetromino4(int x, int y){
        int max = 0;
        int[][] diList = {{0, 1, 1, 2}, {0, 0, -1, -1}, {0, 1, 1, 2}, {0, 0, 1, 1}};
        int[][] djList = {{0, 0, 1, 1}, {0, 1, 1, 2}, {0, 0, -1, -1}, {0, 1, 1, 2}};

        for (int d = 0; d < diList.length; d++)
            max = Math.max(max, tetroAssistant(diList[d], djList[d], x, y));

        return max;
    }

    static int Tetromino5(int x, int y){
        int max = 0;
        int[][] diList = {{0, 0, 0, 1}, {0, 1, 1, 2}, {0, -1, 0, 0}, {0, -1, 0, 1}};
        int[][] djList = {{0, 1, 2, 1}, {0, 0, 1, 0}, {0, 1, 1, 2}, {0, 1, 1, 1}};

        for (int d = 0; d < diList.length; d++)
            max = Math.max(max, tetroAssistant(diList[d], djList[d], x, y));

        return max;
    }

    static int tetroAssistant(int[] di, int[] dj, int x, int y){
        int tmp = 0;
        for (int d = 0; d < 4; d++){
            int ni = x + di[d];
            int nj = y + dj[d];
            if (ni < 0 || nj < 0 || ni >= maze.length || nj >= maze[0].length) {
                tmp = 0;
                break;
            }
            tmp += maze[ni][nj];
        }
        return tmp;
    }
}

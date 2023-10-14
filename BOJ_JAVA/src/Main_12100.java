import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12100 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        // 격자 판 받기
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < N; m++)
                map[n][m] = Integer.parseInt(st.nextToken());
        }

        int result = moveManager(map, 0);

        System.out.println(result);
    }

    static int moveManager(int[][] map, int cnt){
        if (cnt >= 5){
            int result = 0;
            for (int i = 0; i < map.length; i++){
                for (int j = 0; j < map.length; j++)
                    result = Math.max(result, map[i][j]);
            }

            return result;
        }

        int result = 0;

        // 상
        int[][] up = moveUp(map);
        result = Math.max(result, moveManager(up, cnt+1));

        // 우
        int[][] right = moveRight(map);
        result = Math.max(result, moveManager(right, cnt+1));

        // 하
        int[][] down = moveDown(map);
        result = Math.max(result, moveManager(down, cnt+1));

        //좌
        int[][] left = moveLeft(map);
        result = Math.max(result, moveManager(left, cnt+1));

        return result;
    }

    static int[][] moveUp(int[][] map){
        int[][] tmp = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++)
            tmp[i] = map[i].clone();

        boolean[][] visited = new boolean[map.length][map.length];
        for (int j = 0; j < map.length; j++){
            int i = 1;
            while(0 < i && i < map.length){
                if (tmp[i][j] != 0){        // 현재 칸이 숫자일 때
                    for (int k = i; k - 1 >= 0; k--){
                        if (tmp[k-1][j] == 0){          // 빈 칸이면 앞으로 이동
                            tmp[k-1][j] = tmp[k][j];
                            tmp[k][j] = 0;
                        }else{
                            if (tmp[k-1][j] == tmp[k][j] && !visited[k-1][j]){
                                tmp[k-1][j] *= 2;
                                tmp[k][j] = 0;
                                visited[k-1][j] = true;
                            }
                            break;
                        }
                    }
                }
                i++;
            }
        }
        return tmp;
    }

    static int[][] moveRight(int[][] map){
        int[][] tmp = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++)
            tmp[i] = map[i].clone();

        boolean[][] visited = new boolean[map.length][map.length];
        for (int i = 0; i < map.length; i++){
            int j = map.length-2;
            while(0 <= j && j < map.length-1){
                if (tmp[i][j] != 0){        // 현재 칸이 숫자일 때
                    for (int k = j; k + 1 < map.length; k++){
                        if (tmp[i][k+1] == 0){          // 빈 칸이면 앞으로 이동
                            tmp[i][k+1] = tmp[i][k];
                            tmp[i][k] = 0;
                        }else{
                            if (tmp[i][k+1] == tmp[i][k] && !visited[i][k+1]){
                                tmp[i][k+1] *= 2;
                                tmp[i][k] = 0;
                                visited[i][k+1] = true;
                            }
                            break;
                        }
                    }
                }
                j--;
            }
        }
        return tmp;
    }

    static int[][] moveDown(int[][] map){
        int[][] tmp = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++)
            tmp[i] = map[i].clone();

        boolean[][] visited = new boolean[map.length][map.length];
        for (int j = 0; j < map.length; j++){
            int i = map.length-2;
            while(0 <= i && i < map.length-1){
                if (tmp[i][j] != 0){        // 현재 칸이 숫자일 때
                    for (int k = i; k + 1 < map.length; k++){
                        if (tmp[k+1][j] == 0){          // 빈 칸이면 앞으로 이동
                            tmp[k+1][j] = tmp[k][j];
                            tmp[k][j] = 0;
                        }else{
                            if (tmp[k+1][j] == tmp[k][j] && !visited[k+1][j]){
                                tmp[k+1][j] *= 2;
                                tmp[k][j] = 0;
                                visited[k+1][j] = true;
                            }
                            break;
                        }
                    }
                }
                i--;
            }
        }
        return tmp;
    }

    static int[][] moveLeft(int[][] map){
        int[][] tmp = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++)
            tmp[i] = map[i].clone();

        boolean[][] visited = new boolean[map.length][map.length];
        for (int i = 0; i < map.length; i++){
            int j = 1;
            while(0 < j && j < map.length){
                if (tmp[i][j] != 0){        // 현재 칸이 숫자일 때
                    for (int k = j; k -1 >= 0; k--){
                        if (tmp[i][k-1] == 0){          // 빈 칸이면 앞으로 이동
                            tmp[i][k-1] = tmp[i][k];
                            tmp[i][k] = 0;
                        }else{
                            if (tmp[i][k-1] == tmp[i][k] && !visited[i][k-1]){
                                tmp[i][k-1] *= 2;
                                tmp[i][k] = 0;
                                visited[i][k-1] = true;
                            }
                            break;
                        }
                    }
                }
                j++;
            }
        }
        return tmp;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main_10026 {
    static boolean[][] visited = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] rgb = new char[N][N];

        for (int n = 0; n < N; n++){
            String s = br.readLine();
            char[] arr = s.toCharArray();
            for (int a = 0; a < arr.length; a++)
                rgb[n][a] = arr[a];
        }

        // 일반사람
        int normalArea = 0;
        for (int i = 0; i < rgb.length; i++){
            for(int j = 0; j < rgb[i].length; j++){
                if (visited[i][j] == false) {
                    normalArea++;
                    DFS(rgb, i, j);
                }
            }
        }
        System.out.println(normalArea);

        // 적록색약
        for (int i = 0; i < rgb.length; i++){
            for (int j = 0; j < rgb[i].length; j++)
                if (rgb[i][j] == 'G') {rgb[i][j] = 'R';}
        }
        for (boolean[] a : visited)
            Arrays.fill(a, false);

        int colorBlindArea = 0;
        for (int i = 0; i < rgb.length; i++){
            for (int j = 0; j < rgb[i].length; j++){
                if (visited[i][j] == false){
                    colorBlindArea++;
                    DFS(rgb, i, j);
                }
            }
        }
        System.out.println(colorBlindArea);


    }

    static void DFS(char[][] rgb, int i, int j){
        if(visited[i][j] == true)
            return ;

        char curr = rgb[i][j];      // 현재 방문한 노드의 원소
        visited[i][j] = true;   // 방문처리

        int[] rList = {-1, 1, 0, 0};
        int[] cList = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++){        // 상화좌우
            int nr = i + rList[k];
            int nc = j + cList[k];
            if ((nr >= 0 && nr < rgb.length) && (nc >= 0 && nc < rgb.length) && visited[nr][nc] == false) {
                if (curr == rgb[nr][nc])
                    DFS(rgb, nr, nc);
            }
        }
    }
}

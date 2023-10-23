import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node{
    int i, j;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }
}
public class Main_17136 {
    static int[][] matrix = new int[10][10];
    static int minResult = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int n = 0; n < 10; n++){
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < 10; m++)
                matrix[n][m] = Integer.parseInt(st.nextToken());
        }

        int[][] visited = new int[10][10];
        int[] paper = {0, 5, 5, 5, 5, 5};
        DFS(0, 0, visited, paper, 0);

        minResult = minResult == Integer.MAX_VALUE ? -1 : minResult;
        System.out.println(minResult);
    }

    static void DFS(int i, int j, int[][] visited, int[] paper, int cnt){
        if (i == 9 && j > 9){
            minResult = Math.min(minResult, cnt);
            return;
        }

        if (j >= matrix.length) {
            i++;
            j = 0;
        }

        if (matrix[i][j] == 1 && visited[i][j] == 0) {
            for (int len = 5; len > 0; len--){
                boolean checkResult = checkLen(i, j, len, visited);
                if (checkResult && paper[len] > 0) {           // len 크기의 색종이 붙일 수 있으면
                    // 색종이 부착
                    if (cnt + 1 < minResult) {
                        cover(i, j, len, visited, 1);
                        paper[len]--;
                        DFS(i, j + 1, visited, paper, cnt + 1);      // 다음 스텝 탐색
                         // 색종이 떼기
                        cover(i, j, len, visited, 0);
                        paper[len]++;
                    }
                }
            }
        }else
            DFS(i, j+1, visited, paper, cnt);
    }

    static void cover(int i, int j, int len, int[][] visited, int mark){
        for (int ni = i; ni < i+len; ni++){
            for (int nj = j; nj < j+len; nj++){
                visited[ni][nj] = mark;
            }
        }
    }

    static boolean checkLen(int i, int j, int k, int[][] visited) {
        for (int ni = i; ni < i+k; ni++){
            for (int nj = j; nj < j+k; nj++){
                if (ni >= matrix.length || nj >= matrix.length || matrix[ni][nj] == 0 || visited[ni][nj]==1)
                    return false;
            }
        }
        return true;
    }
}


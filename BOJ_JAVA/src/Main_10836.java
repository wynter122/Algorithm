import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_10836 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());           // 격자 크기
        int N = Integer.parseInt(st.nextToken());           // 총 진행일

        // 배열 초기화
        int[][] map = new int[M][M];
        for (int i = 0; i < M; i++)
            Arrays.fill(map[i], 1);

        int n = 0;
        while(n++ < N){
            int[] count = new int[3];          // 0, 1, 2 개수 입력받기
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++)
                count[c] = Integer.parseInt(st.nextToken());

            // [M-1][0] -> [0][0] -> [0][M-1] 까지 자라기
            for (int i = M-1; i >= 0; i--){
                if (count[0]-- > 0){
                }else if (count[1]-- > 0){
                    map[i][0] += 1;
                }else if (count[2]-- > 0){
                    map[i][0] += 2;
                }
            }

            for (int j = 1; j < M; j++){
                if (count[0]-- > 0){
                }else if (count[1]-- > 0){
                    map[0][j] += 1;
                }else if (count[2]-- > 0){
                    map[0][j] += 2;
                }
            }
        }

        // 첫 행 문자열로 만들어두기
        StringBuilder str = new StringBuilder();
        for (int j = 1; j < M; j++)
            str.append(map[0][j]).append(" ");
        str.append("\n");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++)
            sb.append(map[i][0]).append(" ").append(str);

        System.out.print(sb.toString());
    }
}

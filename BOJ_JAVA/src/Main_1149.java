import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1149 {
    static int[] Red;
    static int[] Green;
    static int[] Blue;

    static int[][] cumulativeCost;
    static final int INT_MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 집의 개수
        int N = Integer.parseInt(br.readLine());
        Red = new int[N+1];
        Green = new int[N+1];
        Blue = new int[N+1];

        cumulativeCost = new int[N+1][3];               // 행 : 집의 개수, 열 : R, G, B

        // 각 집을 칠하는 비용 받기
        for (int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            Red[n] = Integer.parseInt(st.nextToken());
            Green[n] = Integer.parseInt(st.nextToken());
            Blue[n] = Integer.parseInt(st.nextToken());
        }

        // DP
        BottomUp(N);

        int result = INT_MAX;
        for (int i = 0; i < 3; i++)
            result = Math.min(result, cumulativeCost[N][i]);

        System.out.println(result);
    }

    static void BottomUp(int N){
        char[] colors = {'R', 'G', 'B'};

        // 1번 색 별로 값 써두기
        for (int i = 0; i < 3; i++)
            cumulativeCost[1][i] = getCost(colors[i], 1);

        // 2번부터 가능한 이전 색을 선택 후 비교 -> 최소 값을 누적합으로 선택
        for (int n = 2; n <= N; n++){
            for (int i = 0; i < 3; i++){
                cumulativeCost[n][i] = getCost(colors[i], n);
                switch (colors[i]){
                    case 'R':
                        cumulativeCost[n][i] += Math.min(cumulativeCost[n-1][1], cumulativeCost[n-1][2]);
                        break;
                    case 'G':
                        cumulativeCost[n][i] += Math.min(cumulativeCost[n-1][0], cumulativeCost[n-1][2]);
                        break;
                    case 'B':
                        cumulativeCost[n][i] += Math.min(cumulativeCost[n-1][0], cumulativeCost[n-1][1]);
                        break;
                }
            }

        }
    }

    static int getCost(char color, int n){
        switch (color) {
            case 'R':
                return Red[n];
            case 'G':
                return Green[n];
            case 'B':
                return Blue[n];
        }
        return 0;
    }
}

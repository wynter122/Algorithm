import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404 {
    static int[][] RGB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        RGB = new int[N+1][3];

        for (int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            RGB[n][0] = r;
            RGB[n][1] = g;
            RGB[n][2] = b;
        }

        int result = -1;
        // 1이 R, G, B 일때 각각 최소 cost 구해서 비교
        for (int i = 0; i < 3; i++){
            int[] cost = DP(N, i);

            int tmp = 0;
            for (int c : cost)
                tmp += c;

            if (result == -1)
                result = tmp;
            else
                result = Math.min(result, tmp);
        }
        System.out.println(result);
    }

    static int[] DP(int N, int start){
        int[] cost = new int[N+1];
        int[] color = new int[N+1];     // R==0, G==1, B==2

        cost[1] = RGB[1][start];
        color[1] = start;

        // 1~N-1까지 구하기
        for (int i = 2; i < N; i++){
            int tmpCost = -1;
            int tmpColor = -1;
            switch (color[i-1]){
                case 0:
                    tmpColor = RGB[i][1] < RGB[i][2] ? 1 : 2;
                    break;
                case 1:
                    tmpColor = RGB[i][0] < RGB[i][2] ? 0 : 2;
                    break;
                case 2:
                    tmpColor = RGB[i][0] < RGB[i][1] ? 0 : 1;
                    break;
            }
            cost[i] = RGB[i][tmpColor];
            color[i] = tmpColor;
        }

        // N 구하기 : N'color != 1'color && N'color != N-1'color
        int tmpColor = -1;
        if (color[1] == color[N-1]){
            switch (color[1]){
                case 0:
                    tmpColor = RGB[N][1] < RGB[N][2] ? 1 : 2;
                    break;
                case 1:
                    tmpColor = RGB[N][0] < RGB[N][2] ? 0 : 2;
                    break;
                case 2:
                    tmpColor = RGB[N][0] < RGB[N][1] ? 0 : 1;
                    break;
            }
        }else{
            switch (color[1]){
                case 0:
                    if (color[N-1] == 1)
                        tmpColor = 2;
                    else
                        tmpColor = 1;
                    break;
                case 1:
                    if (color[N-1] == 0)
                        tmpColor = 2;
                    else
                        tmpColor = 0;
                    break;
                case 2:
                    if (color[N-1] == 0)
                        tmpColor = 1;
                    else
                        tmpColor = 0;
                    break;
            }
        }
        color[N] = tmpColor;
        cost[N] = RGB[N][tmpColor];

        return cost;
    }
}

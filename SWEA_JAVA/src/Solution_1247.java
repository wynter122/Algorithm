import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistance(Point other){
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
};
public class Solution_1247 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            st = new StringTokenizer(s, " ");

            Point allPoints[] = new Point[N+2];

            int distInfo[][] = new int[N+2][N+2];
            boolean check[] = new boolean[N+2];
            Arrays.fill(check, false);
            int distance[] = new int[N+2];

            for (int i = 0; i <= N+1; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                Point tmpPoint = new Point(x, y);
                allPoints[i] = tmpPoint;
            }

            for (int i = 0; i <= N+1; i++) {
                for (int j = 0; j <= N+1; j++)
                    distInfo[i][j] = Math.abs(allPoints[i].x - allPoints[j].x) + Math.abs(allPoints[i].y - allPoints[j].y);
            }

            int currIdx = 0;
            int cnt = 1;

            while(true) {
                if (cnt >= N+1) {
                    distance[currIdx] = distInfo[currIdx][N+1];
                    check[currIdx] = true;
                    break;
                }

                check[currIdx] = true;
                distance[currIdx] = 0;

                int minimumDist = 1000;
                int minimumJ = 0;
                for(int j = 0; j <= N; j++) {
                    if (j == currIdx || check[j] == true) continue;
                    if (distInfo[currIdx][j] < minimumDist) {
                        minimumDist = distInfo[currIdx][j];
                        minimumJ = j;
                    }
                }
                distance[currIdx] = minimumDist;
                currIdx = minimumJ;
                cnt++;
            }

            int result = 0;

            System.out.println("#" + t + " " + result);
        }

    }

    public int dfs(){

        return 0;
    }
}

/*
10
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36

 */
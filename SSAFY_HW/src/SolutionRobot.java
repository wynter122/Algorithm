import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionRobot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            char[][] map = new char[N][N];

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[n][j] = st.nextToken().charAt(0);
            }

            int result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 'A') {
                        int tmpJ = j;
                        while(true) {
                            if (tmpJ + 1 >= N || map[i][++tmpJ] != 'S')
                                break;
                            result++;
                        }
					} else if (map[i][j] == 'B') {
                        int tmpJ = j;
                        while (true) {
                            if (tmpJ + 1 >= N || map[i][++tmpJ] != 'S')
                                break;
                            result++;
                        }

                        tmpJ = j;
                        while (true){
                            if (tmpJ - 1 < 0 || map[i][--tmpJ] != 'S')
                                break;
                            result++;
                        }
					} else if (map[i][j] == 'C') {
                        int tmpJ = j;
                        while (true) {
                            if (tmpJ + 1 >= N || map[i][++tmpJ] != 'S')
                                break;
                            result++;
                        }

                        tmpJ = j;
                        while (true){
                            if (tmpJ - 1 < 0 || map[i][--tmpJ] != 'S')
                                break;
                            result++;
                        }

                        int tmpI = i;
                        while (true) {
                            if (tmpI + 1 >= N || map[++tmpI][j] != 'S')
                                break;
                            result++;
                        }

                        tmpI = i;
                        while (true) {
                            if (tmpI - 1 < 0 || map[--tmpI][j] != 'S')
                                break;
                            result++;
                        }
					}
                }
            }

            System.out.println("#" + t + " " + result);
        }

    }

}
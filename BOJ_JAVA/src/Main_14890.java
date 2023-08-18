import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14890 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int result = 0;                 // 지나갈 수 있는 길의 개수 저장

        // 가로 확인
        for (int n = 0; n < N; n++){
            result += check(map[n], L);
        }

        // 세로 확인
        for (int j = 0; j < N; j++){
            int[] road = new int[N];
            for (int i = 0; i < N; i++)
                road[i] = map[i][j];
            result += check(road, L);
        }

        System.out.println(result);
    }

    static int check(int[] road, int L){
        boolean flag = true;
        int prev = road[0];

        int tmpL = 1;
        boolean needSlide = false;
        for (int i = 1; i < road.length; i++){
            int h = road[i];

            if (prev == h){                     // 높이가 같을 때
                tmpL++;
                if (needSlide && tmpL == L){
                    tmpL = 0;
                    needSlide = false;
                }

            }else{
                if (prev > h){                  // 낮아짐
                    if (prev-1 != h){
                        flag = false;
                        break;
                    }
                    if (needSlide){             // 경사로 설치가 필요한 상태인데 한 번 더 낮아졌을떄
                        flag = false;
                        break;
                    }
                    needSlide = true;
                    tmpL = 1;

                    if (needSlide && tmpL == L){
                        needSlide = false;
                        tmpL = 0;
                    }

                }else if (prev < h){            // 높아짐
                    if (prev != h-1){
                        flag = false;
                        break;
                    }

                    if (tmpL < L){              // 같은 높이의 길 && 경사로를 설치하지 않은 길의 길이 >= 경사로의 길이 가 아닐때
                        flag = false;
                        break;
                    }

                    tmpL = 1;
                }
            }

            prev = road[i];
        }

        if (needSlide)
            flag = false;

        if (flag)
            return 1;
        else
            return 0;
    }
}

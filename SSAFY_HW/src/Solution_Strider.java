
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Strider {
    int r;
    int c;
    int dir;    // up : 1, down : 2, left : 3, right : 4

    public Strider (int r, int c, int dir){
        this.r = r;
        this.c = c;
        this.dir = dir;
    }

    public boolean jump(int rotation, int N){
        int jumpCnt = 0;
        switch (rotation){
            case 1:
                jumpCnt = 3;
                break;
            case 2:
                jumpCnt = 2;
                break;
            case 3:
                jumpCnt = 1;
                break;
        }

        int tmpR = r;
        int tmpC = c;

        boolean jumpSuccess = true;

        switch (dir){
            case 1:     // up
                tmpR -= jumpCnt;
                break;
            case 2:     // down
                tmpR += jumpCnt;
                break;
            case 3:     // left
                tmpC -= jumpCnt;
                break;
            case 4:
                tmpC += jumpCnt;  // right
                break;
        }

        if ((tmpR < 0 || tmpR >= N) || (tmpC < 0 || tmpC >= N))
            jumpSuccess = false;
        else{
            this.r = tmpR;
            this.c = tmpC;
        }

        return jumpSuccess;
    }
}

public class Solution_Strider {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            int[][] pond = new int[N][N];
            Queue<Strider> allStrider = new LinkedList<>();

            for (int s = 0; s < S; s++){
                st = new StringTokenizer(br.readLine());
                Strider strider = new Strider(Integer.parseInt(st.nextToken()),
                                                Integer.parseInt(st.nextToken()),
                                                Integer.parseInt(st.nextToken()));
                pond[strider.r][strider.c] = 1;
                allStrider.add(strider);
            }

            int result = 0;
            for (int s = 0; s < S; s++){
                Strider strider = allStrider.poll();
                int prevR = strider.r;
                int prevC = strider.c;

                boolean isAlive = true;

                for (int j = 1; j < 4; j++){
                    boolean jmpResult = strider.jump(j, N);
                    if (!jmpResult || pond[strider.r][strider.c] == 1){
                        isAlive = false;
                        break;
                    }
                }
                if(isAlive){
                    pond[prevR][prevC] = 0;
                    pond[strider.r][strider.c] = 1;
                    result++;
                }
            }

            System.out.println("#" + t + " " + result);
        }
    }
}

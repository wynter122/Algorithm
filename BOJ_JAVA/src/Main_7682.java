import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_7682 {
    static final String valid = "valid";
    static final String invalid = "invalid";
    static char[][] board = new char[3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String str = br.readLine();
            if (str.equals("end"))
                break;

            int xCnt = 0;
            int oCnt = 0;
            // 게임판 받기
            for (int i = 0; i < 3; i++){
                char[] tmp = str.substring(i*3, i*3+3).toCharArray();
                board[i] = tmp;
                for (int j = 0; j < 3; j++){
                    switch (board[i][j]){
                        case 'X':
                            xCnt++;
                            break;
                        case 'O':
                            oCnt++;
                            break;
                    }
                }
            }

            // 말 개수로 1차 확인
            if ((xCnt < oCnt) ||(xCnt-oCnt > 1)){           // x >= o 이고 x-o > 1 이 아니면 실패
                System.out.println(invalid);
                continue;
            }

            // 우승 여부 확인
            boolean xWin = checkWin('X');
            if (xWin && (xCnt == oCnt)){      // x 가 이겼는데 x와 o의 개수가 같으면 실페
                System.out.println(invalid);
                continue;
            }

            boolean oWin = checkWin('O');
            if(xWin && oWin){               // x o 둘 다 우승이면 실패
                System.out.println(invalid);
                continue;
            }

            if (oWin && (xCnt != oCnt)){        // o가 이겼는데 x 의 말이 1개 더 많으면 실패
                System.out.println(invalid);
                continue;
            }

            if(!xWin && !oWin && (xCnt+oCnt != 9)) {        // 우승이 없는데 말이 다 차지 않았음면 실패
                System.out.println(invalid);
                continue;
            }

            System.out.println(valid);
        }
    }

    static boolean checkWin(char piece){
        // win : true

        int tmpCnt;
        // 가로 확인
        for (int i = 0; i < 3; i++){
            tmpCnt = 0;
            for (int j = 0; j < 3; j++){
                if (board[i][j] == piece)
                    tmpCnt++;
            }
            if (tmpCnt == 3)
                return true;
        }

        // 세로 확인
        for (int j = 0; j < 3; j++){
            tmpCnt = 0;
            for (int i = 0; i < 3; i++){
                if (board[i][j] == piece)
                    tmpCnt++;
            }
            if (tmpCnt == 3)
                return true;
        }

        // 대각선 확인
        tmpCnt = 0;
        for (int i = 0; i < 3; i++){
            if (board[i][i] == piece)
                tmpCnt++;
        }
        if (tmpCnt == 3)
            return true;

        tmpCnt = 0;
        int j = 2;
        for (int i = 0; i < 3; i++){
            if (board[i][j--] == piece)
                tmpCnt++;
        }
        if (tmpCnt == 3)
            return true;

        return false;
    }
}

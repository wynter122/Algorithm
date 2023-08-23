import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main_2580 {
    static int[][] sudoku = new int[9][9];
    static Stack<Point> blank = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 스도쿠 입력 받기
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0)      // 빈칸 삽입
                    blank.add(new Point(i, j));
            }
        }

        // 백트래킹 시작
        Backtracking();

    }
    static void Backtracking(){
        if (blank.isEmpty())            // 더이상 빈 칸이 없다면 return이 아닌!!!! 프로그램 종료
        {
            for (int i = 0; i < 9; i++){
                for (int j = 0; j < 9; j++)
                    System.out.print(sudoku[i][j] + " ");
                System.out.println();
            }
            System.exit(0);
        }

        Point curr = blank.pop();                   // 빈 칸 뽑기
        for (int n = 1; n <= 9; n++){
            if (check(curr.x, curr.y, n)){          // 현재 빈 칸에 1부터 9까지 만족하는 수가 있다면
                sudoku[curr.x][curr.y] = n;             // 빈 칸 채우기
                Backtracking();
            }
        }

        sudoku[curr.x][curr.y] = 0;             // 현재 칸 0으로 바꾸고
        blank.add(curr);                        // 스택에 다시 삽입 -> 이전 경우로 돌아가기 위해

    }

    static boolean check(int x, int y, int n){
        // 가로 : 열 옮기기
        for (int j = 0; j < 9; j++){
            if (sudoku[x][j] == n)
                return false;
        }

        // 세로 : 행 옮기기
        for (int i = 0; i < 9; i++){
            if (sudoku[i][y] == n)
                return false;
        }

        // 사각형
        int startI = 3 * (x/3);
        int startJ = 3 * (y/3);
        for (int i = startI; i < startI+3; i++){
            for (int j = startJ; j < startJ+3; j++){
                if (sudoku[i][j] == n)
                    return false;
            }
        }
        return true;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_2239 {
    static int[][] sudoku = new int[9][9];
    static Stack<Point> blank = new Stack<>();         // 빈 칸 넣어둘 스택
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Point> tmpArr = new ArrayList<>();
        // 스도쿠 초기 상태 입력
        for (int i = 0; i < 9; i++){
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++){
                sudoku[i][j] = line[j] - '0';
                if (sudoku[i][j] == 0)
                    tmpArr.add(new Point(i, j));
            }
        }

        // Stack에 제일 마지막 빈칸부터 저장
        for(int i = tmpArr.size()-1; i >= 0; i--)
            blank.add(tmpArr.get(i));

        // Backtracking 시작
        Backtracking();

    }

    static void Backtracking() throws IOException {
        if (blank.isEmpty()){           // 스도쿠를 모두 채움
            for (int i = 0; i < sudoku.length; i++){
                for (int j = 0; j < sudoku.length; j++)
                    System.out.print(sudoku[i][j]);
                System.out.println();
            }
            System.exit(0);
        }

        Point curr = blank.pop();
        // 가능한 수를 차례로 현재 칸에 넣기
        for (int num = 1; num <= 9; num++){
            if (check(curr, num)){              // 가능한 수 발견하면 다음 빈 칸 탐색
                sudoku[curr.x][curr.y] = num;
                Backtracking();
            }
        }

        // 현재 칸을 빈 칸으로 만들고 이전 단계로 돌아가기
        sudoku[curr.x][curr.y] = 0;
        blank.add(curr);
    }

    static boolean check(Point curr, int num){
        // 가로 체크 : 열 변경
        for (int j = 0; j < 9; j++){
            if (sudoku[curr.x][j] == num)
                return false;
        }

        // 세로 체크 : 행 변경
        for (int i = 0; i < 9; i++){
            if (sudoku[i][curr.y] == num)
                return false;
        }

        // 사각형 체크
        int startI = 3 * (curr.x/3);
        int startJ = 3 * (curr.y/3);
        for (int i = startI; i < startI+3; i++){
            for (int j = startJ; j < startJ+3; j++){
                if (sudoku[i][j] == num)
                    return false;
            }
        }
        return true;
    }
}

/*
000000000
000000000
000000000
000000000
000000000
000000000
000000000
000000000
123456789
 */
import java.util.Scanner;

public class Main_9663 {
    static int[] queenLocation;
    static int N;
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        // queenLocation 의 index == 열, 값 == 행
        queenLocation = new int[N];

        // 0열의 0~N-1 백트래킹
        for (int i = 0; i < N; i++)
            Backtracking(0, i, 1);

        System.out.println(count);
    }

    static void Backtracking(int C, int R, int n){
        queenLocation[C] = R;
        if (n == N) {       // N개를 만족하면
            count++;
            return;
        }
        // C+1 열에서 배치 가능한 행 탐색
        for (int i = 0; i < N; i++){
            if (check(i, C+1))
                Backtracking(C+1, i, n+1);
        }
    }

    static boolean check(int checkR, int checkC){
        for (int c = 0; c < checkC; c++){
            // 가로 : 행이 같음
            if (queenLocation[c] == checkR)
                return false;
            // 세로 : 열이 같음
            if (c == checkC)
                return false;
            // 대각선 : 행 차이 절대값 == 열 차이 절대값
            if (Math.abs(checkC-c) == Math.abs(checkR - queenLocation[c]))
                return false;
        }
        return true;
    }
}

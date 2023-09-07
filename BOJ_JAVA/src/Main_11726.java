import java.util.Scanner;

public class Main_11726 {
    static int[] DP;
    static int divisor = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        DP = new int[N+1];
        DynamicProgramming(N);
        System.out.println(DP[N]);
    }

    static void DynamicProgramming(int N){
        DP[1] = 1;
        if (N < 2)
            return;
        DP[2] = 2;
        for (int i = 3; i <= N; i++)
            DP[i] = (DP[i-1] + DP[i-2]) % divisor;
    }
}

import java.util.Scanner;

public class Main_2747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] fibonacci = new int[N+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int n = 2; n <= N; n++)
            fibonacci[n] = fibonacci[n-2] + fibonacci[n-1];

        System.out.println(fibonacci[N]);
    }
}

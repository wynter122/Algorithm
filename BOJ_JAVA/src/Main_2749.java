import java.util.Scanner;

public class Main_2749 {
    static final int divisor = (int) Math.pow(10, 6);
    static final int pisano = (int) (Math.pow(10, 5) * 15);          // 1,000,000 의 피사노주기
    static long[] fibonacci = new long[pisano+1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int n = 2; n < fibonacci.length; n++)
            fibonacci[n] = (fibonacci[n-2] + fibonacci[n-1]) % divisor;

        long N = sc.nextLong();
        System.out.println(fibonacci[(int) (N % pisano)]);
    }
}

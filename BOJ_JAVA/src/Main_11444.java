import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main_11444 {
    static Map<Long, Long> map = new HashMap<>();
    static final int divisor = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();

        long result = fibonacci(N);
        System.out.println(result % divisor);
    }

    static long fibonacci(long n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (map.containsKey(n)) return map.get(n);

        long result = 0;
        if (n%2 == 0)
            result = (fibonacci(n/2) % divisor) * (2 * (fibonacci(n/2-1) % divisor) + (fibonacci(n/2) % divisor));
        else
            result = (fibonacci((n-1)/2) % divisor) * (fibonacci((n-1)/2) % divisor) + (fibonacci((n-1)/2 + 1) % divisor) * (fibonacci((n-1)/2 + 1) % divisor);

        map.put(n, result % divisor);

        return result;
    }
}

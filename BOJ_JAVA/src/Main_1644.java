import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_1644 {
    static boolean[] primeNum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        if (N == 1){
            System.out.println(0);
            return;
        }
        primeNum = new boolean[N+1];
        Arrays.fill(primeNum, true);

        // 에라토스테네스의 체 - 2부터 N의 제곱근까지 소수를 판별 후 리스트 받음
        ArrayList<Integer> primes = Eratosthenes(N);

        // N 이 되는 경우의수 판별
        int result = twoPointer(N, primes);
        System.out.println(result);
    }

    static ArrayList<Integer> Eratosthenes(int N){
        ArrayList<Integer> primes = new ArrayList<>();
        int sqrtN = (int)Math.ceil(Math.sqrt(N));
        for (int i = 2; i <= sqrtN; i++){
            if (primeNum[i]){
                primes.add(i);
                for (int j = 2; i*j <= N; j++)
                    primeNum[i*j] = false;
            }
        }

        for (int i = sqrtN+1; i <= N; i++){
            if (primeNum[i])
                primes.add(i);
        }
        return primes;
    }

    static int twoPointer(int N, ArrayList<Integer> primes){
        int start = 0;
        int end = 0;

        int cnt = 0;

        int sum = primes.get(start);
        while(true){
            if (sum == N){
                cnt++;

                if (start == primes.size()-1)
                    break;
                sum -= primes.get(start++);

                if (end < primes.size()-1)
                    sum += primes.get(++end);
            }else if (sum < N) {
                if (end < primes.size()-1)
                    sum += primes.get(++end);
                else {
                    if (start == primes.size()-1)
                        break;
                    sum -= primes.get(start++);
                }
            }else if (sum > N){
                if (start == primes.size()-1)
                    break;
                sum -= primes.get(start++);
            }
        }

        return cnt;
    }

}

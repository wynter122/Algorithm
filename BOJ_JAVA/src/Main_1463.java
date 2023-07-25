import java.util.Scanner;

public class Main_1463 {
    static int[] DP = new int [1000001];
    public static void main(String[] args) {
        for (int i = 1; i < DP.length; i++) {
            if (i == 1)
                DP[i] = 0;
            else if (i == 2 || i == 3)
                DP[i] = 1;
            else {
                int tmpI = i;
                int result = 1;
                if ((i % 2 != 0) && (i % 3 != 0)) {         // 2, 3 나누기 둘 다 안될 때 -> -1
                    result++;
                    tmpI--;
                }

                if ((tmpI % 2 == 0) && !(tmpI % 3 == 0))                    // 2로만 나눠질 때
                    result += DP[tmpI/2] < DP[tmpI-1] ? DP[tmpI/2] : DP[tmpI-1];
                else if (!(tmpI % 2 == 0) && (tmpI % 3 == 0))               // 3으로만 나눠질 때
                    result += DP[tmpI/3] < DP[tmpI-1] ? DP[tmpI/3] : DP[tmpI-1];
                else {                                                      // 둘 다 나눠질 때
                    int tmp2 = DP[tmpI/2] < DP[tmpI-1] ? DP[tmpI/2] : DP[tmpI-1];
                    int tmp3 = DP[tmpI/3] < DP[tmpI-1] ? DP[tmpI/3] : DP[tmpI-1];
                    result += tmp2 < tmp3 ? tmp2 : tmp3;
                }
                DP[i] = result;
            }
        }

        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        System.out.println(DP[X]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());            // N*N
        int K = Integer.parseInt(br.readLine());            // K번째 수 찾기

        // 이분탐색으로 x값 찾기 -> N*N 행렬에서 x 보다 작거나 같은 수의 개수가 k개 이상여야 함
        int start = 1;          // 1부터 시작
        int end = K;          // 최대 K까지 탐색 필요

        boolean checkOne = false;
        int result = 0;
        while(true){
            // 중간 값 구하기
            int mid = start;
            if ((end-start)/2 == 0)
                if (checkOne)
                    mid += 1;
                else
                    checkOne = true;
            else
                mid += (end-start)/2;

            int tmpCnt = getSmallerNumberCnt(N, mid);
            if (tmpCnt < K)
                start = mid;
            else {
                result = mid;
                if (end == mid)
                    break;

                end = mid;
            }
        }

        System.out.println(result);
    }

    static int getSmallerNumberCnt(int N, int x){
        // i -> 1~N
        int result = 0;
        for (int i = 1; i <= N; i++)
            result += Math.min(N, x/i);

        return result;
    }
}

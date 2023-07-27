import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467 {
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        number = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            number[n] = Integer.parseInt(st.nextToken());

        long result = 2000000000;
        int resultS = 0;
        int resultE = N-1;

        int start = resultS;
        int end = resultE;
        while(start != end){
            int sNum = number[start];
            int eNum = number[end];

            long tmp = sNum + eNum;
            if (Math.abs(tmp) < result){
                result = Math.abs(tmp);
                resultS = start;
                resultE = end;
            }

            if (tmp == 0)
                break;
            else if (tmp > 0)
                end--;
            else if (tmp < 0)
                start++;
        }

        System.out.println(number[resultS] + " " + number[resultE]);
    }
}

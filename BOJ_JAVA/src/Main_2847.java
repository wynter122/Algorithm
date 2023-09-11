import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N];
        for (int n = 0; n < N; n++)
            scores[n] = Integer.parseInt(br.readLine());

        int result = 0;
        int last = scores[N-1];
        for (int n = N-2; n >= 0; n--){
            int curr = scores[n];
            if (curr >= last){
                int tmp = curr - last + 1;
                scores[n] = curr - tmp;
                result += tmp;
            }
            last = scores[n];
        }

        System.out.println(result);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            list[n] = Integer.parseInt(st.nextToken());

        ///// sort /////
    }
}

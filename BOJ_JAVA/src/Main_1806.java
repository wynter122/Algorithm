import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806 {
    static final int INT_MAX = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            arr[n] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;

        int minLength = INT_MAX;

        int num = arr[start];
        while(start < arr.length){
            if (num >= S){
                minLength = Math.min(minLength, end-start+1);
                num -= arr[start++];
                if (start > end)
                    end++;
            }else {
                if (end == arr.length-1)
                    break;
                num += arr[++end];
            }
        }

        if (minLength == INT_MAX)
            minLength = 0;
        System.out.println(minLength);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            arr[n] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        for (int n = 0; n < N; n++)
            stack.push(arr[n]);

        int[] result = new int[N];

        for (int n = 0; n < N; n++){
            int target = arr[n];


            if (stack.empty())
                result[n] = -1;
            else
                result[n] = stack.pop();
        }

        for (int r : result)
            System.out.print(r + " ");
    }
}

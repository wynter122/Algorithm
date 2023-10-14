import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1932 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++)
            graph.add(new ArrayList<>());

        // n == depth
        graph.get(0).add(Integer.parseInt(br.readLine()));
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> parent = graph.get(n-1);
            for (int j = 0; j <= n; j++){
                int curr = Integer.parseInt(st.nextToken());
                if (j == 0)
                    curr += parent.get(0);
                else if (j == n)
                    curr += parent.get(j-1);
                else
                    curr += Math.max(parent.get(j-1), parent.get(j));

                graph.get(n).add(curr);
            }
        }

        int result = 0;
        for (int n : graph.get(N-1))
            result = Math.max(result, n);
        System.out.println(result);
    }
}

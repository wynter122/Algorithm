import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1107 {
    static boolean[] broken = new boolean[10];
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if (M  > 0) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens())
                broken[Integer.parseInt(st.nextToken())] = true;
        }

        // |N-100| : +, 혹은 -로만
        result = Math.abs(N - 100);
        if (result == 0)
            System.out.println(result);
        else {
            // 버튼을 누르고, +. -로 찾아가는 경우의수
            recursion(N, "");
            System.out.println(result);
        }
    }

    static void recursion(int N, String curr){
        if (!curr.equals("")) {
            int tmp = curr.length() + Math.abs(N - Integer.parseInt(curr));
            result = Math.min(tmp, result);

            if (result == 0) {
                System.out.println(result);
                System.exit(0);
            }
        }

        if (String.valueOf(N).length()+1 <= curr.length())
            return;

        for (int i = 0; i < 10; i++){
            if (!broken[i])
                recursion(N, curr + i);
        }
    }
}

/*
101
10
0 1 2 3 4 5 6 7 8 9
 */
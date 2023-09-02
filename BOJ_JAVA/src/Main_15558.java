import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class User {
    String side;
    int pos;
    public User(String side, int pos){
        this.side = side;
        this.pos = pos;
    }

}

public class Main_15558 {
    static final String Left = "LEFT";
    static final String Right = "RIGHT";
    static int[] left;
    static int[] right;
    static boolean[] leftCheck;
    static boolean[] rightCheck;
    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        left = new int[N];
        right = new int[N];

        leftCheck = new boolean[N];
        rightCheck = new boolean[N];

        char[] line = br.readLine().toCharArray();
        for (int i = 0; i < N; i++)
            left[i] = line[i] - '0';

        line = br.readLine().toCharArray();
        for (int i = 0; i < N; i++)
            right[i] = line[i] - '0';

        User user = new User(Left, 0);              // 왼쪽 0칸부터 시작

        DFS(user, 0);
        System.out.println(0);
    }

    static void DFS(User user, int sec){
        if (user.pos >= N){
            System.out.println(1);
            System.exit(0);
        }

        if (user.side == Left)
            leftCheck[user.pos] = true;
        else if (user.side == Right)
            rightCheck[user.pos] = true;

        if ((user.pos + K >= N) || (user.side == Left && right[user.pos+K] == 1 && !rightCheck[user.pos+K]) || (user.side == Right && left[user.pos+K] == 1 && !leftCheck[user.pos+K])){
            String nextSide = user.side == Left ? Right : Left;
            DFS(new User(nextSide, user.pos+K), sec+1);
        }

        if ((user.pos + 1 >= N) || (user.side == Left && left[user.pos+1] == 1 && !leftCheck[user.pos+1]) || (user.side == Right && right[user.pos+1] == 1 && !rightCheck[user.pos+1]))
            DFS(new User(user.side, user.pos+1), sec+1);

        if (user.pos - 1 > sec){
            if ((user.side == Left && left[user.pos-1] == 1 && !leftCheck[user.pos-1]) || (user.side == Right && right[user.pos-1] == 1 && !rightCheck[user.pos-1]))
                DFS(new User(user.side, user.pos-1), sec+1);
        }

        if (user.side == Left)
            leftCheck[user.pos] = false;
        else if (user.side == Right)
            rightCheck[user.pos] = false;
    }
}

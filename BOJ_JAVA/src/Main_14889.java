import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14889 {
    static int[][] arr;
    static int N;
    static int m;
    static Stack<Integer> startStack = new Stack<>();
    static boolean[] visited;
    static int diff = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());            // 팀원 수
        m = N/2;                                        // 한 팀당 뽑혀야 하는 인원수
        arr = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        // 조합에 따라 능력치 차이 구하기
        Combination(0, 1);

        System.out.println(diff);

    }

    static void Combination(int cnt, int idx){
        if (cnt == m){
            // start 팀 능력치 구하기
            int start = getAvailability(startStack);

            // link 팀 능력치 구하기
            Stack<Integer> linkStack = new Stack<>();
            for (int v = 1; v < visited.length; v++){
                if (!visited[v])
                    linkStack.push(v);
            }
            int link = getAvailability(linkStack);
            int tmpDiff = Math.abs(start - link);
            diff = diff > tmpDiff ? tmpDiff : diff;

            Arrays.fill(visited, false);
            return;
        }

        for (int i = idx; i <= N; i++){
            startStack.push(i);
            Combination(cnt + 1, i + 1);
            startStack.pop();
        }
    }

    static int getAvailability(Stack team){
        Iterator<Integer> it = team.iterator();
        Iterator<Integer> it2 = team.iterator();
        int result = 0;
        while(it.hasNext()){
            int i = it.next();
            visited[i] = true;
            while (it2.hasNext()){
                int j = it2.next();
                result += arr[i][j];
            }
            it2 = team.iterator();
        }
        return result;
    }
}

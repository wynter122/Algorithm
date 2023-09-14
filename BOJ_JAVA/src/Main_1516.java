import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1516 {
    static int[] times;
    static int[] preCnt;
    static int[] result;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());
        times = new int[N+1];           // 1번부터 N번까지 소요되는 시간 저장
        preCnt = new int[N+1];
        result = new int[N+1];

        for (int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            times[n] = t;           // 건설 소요 시간 기재

            while (st.hasMoreTokens()){
                int preNum = Integer.parseInt(st.nextToken());
                if (preNum == -1)
                    break;
                graph.get(preNum).add(n);       // 앞서야하는 건물에 현재 건물 번호 추가
                preCnt[n]++;                    // 현재 건물에 앞서서 건설되어야하는 건물 개수 1 증가
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++){   // 큐에 앞서서 지어야하는 건물 개수 0인 애들 먼저 넣기
            if (preCnt[i] == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()){
            int num = queue.poll();         // 건설 가능한 번호
            result[num] += times[num];          // 시간 확정

            // 그래프에서 이후 건설될 수 있는 번호 순회 -> preCnt -1해주기
            for (int next : graph.get(num)){
                preCnt[next]--;
                result[next] = Math.max(result[num], result[next]);

                if (preCnt[next] == 0)
                    queue.add(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            sb.append(result[i]).append("\n");
        System.out.println(sb);
    }
}

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1766 {
    static int[] havePrior;                                                             // 문제 번호 마다 선행해서 풀어야하는 문제의 개수 기록
    static ArrayList<PriorityQueue<Integer>> waiting = new ArrayList<>();              // 문제 번호 마다 이후에 풀 수 있는 문제 번호 나열
    static boolean[] clear;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int M = sc.nextInt();

        havePrior = new int[N+1];
        clear = new boolean[N+1];
        for (int n = 0; n <= N; n++)
            waiting.add(new PriorityQueue<>());

        // 문제 순서 정보 받기
        for (int m = 0; m < M; m++){
            int first = sc.nextInt();
            int second = sc.nextInt();

            waiting.get(first).add(second);             // 선행 문제의 우선순위 큐에 이후 풀 수 있는 문제 추가
            havePrior[second]++;                        // 후행 문제의 선행 필요 문제 개수 증가
        }

        // 우선순위큐에 havePrior가 0인 노드를 num 오름차순으로 넣기
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (havePrior[i] == 0)
                queue.add(i);
        }

        // 우선순위큐를 비우면서, 이후 풀 수 있는 문제의 havePrior 가 이면 우선순위 큐에 삽입
        ArrayList<Integer> done = new ArrayList<>();
        while(!queue.isEmpty()){
            int curr = queue.poll();
            done.add(curr);
            PriorityQueue<Integer> currQ = waiting.get(curr);
            while(!currQ.isEmpty()){
                int next = currQ.poll();
                if (--havePrior[next] == 0)
                    queue.add(next);
            }
        }

        for(int n : done)
            sb.append(n).append(" ");
        System.out.println(sb);
    }

}

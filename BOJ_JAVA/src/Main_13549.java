import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
    int num;
    int sec;
    public Pair(int num, int sec){
        this.num = num;
        this.sec = sec;
    }
}
public class Main_13549 {
    static int[] cost = new int[100001];
    static int INF = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Arrays.fill(cost, INF);

        BFS(N, K);
        System.out.println(cost[K]);
    }

    static void BFS(int N, int K){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(N, 0));

        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            if (cost[curr.num] <= curr.sec)           // 이미 최단 거리 찾은 경우 Pass (같은 경우도 패스해야함. 그렇지 않으면 무한루프)
                continue;

            cost[curr.num] = curr.sec;

            // 인접 노드 큐에 삽입
            if (curr.num-1 >= 0)
                queue.add(new Pair(curr.num-1, curr.sec+1));
            if (curr.num+1 <= 100000)
                queue.add(new Pair(curr.num+1, curr.sec+1));
            if (curr.num*2 <= 100000)
                queue.add(new Pair(curr.num*2, curr.sec));
        }
    }
}

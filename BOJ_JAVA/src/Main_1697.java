import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Pair{
    int pose;
    int sec;
    int[] child = new int[3];           // -1, +1, *2
    public Pair(int pose, int sec){
        this.pose = pose;
        this.sec = sec;
        this.child[0] = pose-1;
        this.child[1] = pose+1;
        this.child[2] = pose*2;
    }
}
public class Main_1697 {
    static boolean[] visited = new boolean[100001];     // 방문처리 할 배열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Pair result = BFS(N, K);
        System.out.println(result.sec);
    }

    static Pair BFS(int N, int K){
        // N을 루트로 하는 트리 생성 -> 자식 3개
        Queue<Pair> queue = new LinkedList<>();

        int sec = 0;
        Pair pair = new Pair(N, sec++);
        queue.add(pair);
        int queueSize = queue.size();

        Pair resultPair;

        while (true){
            Pair curr = queue.poll();       // 방문할 노드 꺼내기
            if (!visited[curr.pose]) {
                visited[curr.pose] = true;  // 방문처리
                if (curr.pose == K){        // 방문한 노드의 위치 == K
                    resultPair = curr;
                    break;
                }

                for (int child : curr.child) {
                    if (child >= 0 && child <= 100000)
                        queue.add(new Pair(child, sec));
                }
            }

            queueSize--;
            if (queueSize == 0){
                sec++;
                queueSize = queue.size();
            }
        }

        return resultPair;
    }
}

/*
1
2
3
6
12
24
48
49
98
196
195
390
780
781
1562 (14)
3124
3125
6250
12500
25000
50000
100000
 */
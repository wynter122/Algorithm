import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Pair implements Comparable<Pair>{
    int node;
    int cost;
    public Pair(int node, int cost){
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pair pair){
        if (this.cost > pair.cost)
            return 1;
        else if (this.cost < pair.cost)
            return -1;
        else
            return 0;
    }
}
class Node {
    int num;
    int weight;
    public Node(int next, int weight){
        this.num = next;
        this.weight = weight;
    }
}

public class Main_5972 {
    static List<ArrayList<Node>> graph = new ArrayList<>();            // 인덱스별로 연결된 노드의 간선 정보
    static int[] cost;                                  // 최소비용 저장할 배열
    static int INF = 1000000000;                                  // 최대 비용 1000 * 50000 이하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 헛간개수, 목적지
        int M = Integer.parseInt(st.nextToken());       // 간선 개수

        cost = new int[N+1];
        Arrays.fill(cost, INF);                     // 비용 배열 무한대로 초기화

        for (int n = 0; n <= N; n++)
            graph.add(new ArrayList<>());

        for (int m = 0; m < M; m++){                // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(N);
        System.out.println(cost[N]);
    }

    static void dijkstra(int N){
        PriorityQueue<Pair> queue = new PriorityQueue<>();          // 탐색할 노드 삽입할 큐 : node 번호와 node 까지 구해진 cost 쌍 오름차순
        queue.add(new Pair(1, 0));          // 1부터 시작
        cost[1] = 0;

        while(!queue.isEmpty()){
            Pair curr = queue.poll();           // 방문할 노드 꺼내기
            if (cost[curr.node] < curr.cost)        // 이미 cost 배열에 최소비용이 구해진 경우 skip
                continue;

            for (Node next : graph.get(curr.node)){          // 인접한 노드 꺼내기
                int nextWeight = curr.cost + next.weight;       // 인접 노드의 새로운 최소 비용이 기존 최신 비용보다 작으면
                if (cost[next.num] > nextWeight){
                    cost[next.num] = nextWeight;                // 갱신
                    queue.add(new Pair(next.num, cost[next.num]));  // 갱신된 인접노드 및 새로운 최소비용 쌍을 큐에 추가
                }
            }
        }
    }
}

/*
6 8
1 2 1
1 4 10
2 4 100
2 3 100
4 5 2
3 5 1
6 3 1
5 6 1

6 8
1 2 1
1 4 10
2 4 100
2 3 0
4 5 2
3 5 1
6 3 1
5 6 1

4 5
1 2 0
1 3 0
1 4 1
4 3 2
2 4 2
-> 1

4 5
1 2 1
1 3 1
1 4 2
4 3 2
2 4 2
-> 2

5 6
1 2 10
1 3 10
2 3 10
2 5 10
3 4 10
4 5 10
-> 20

5 6
1 2 10
1 3 10
2 3 1
2 5 10
3 4 10
4 5 10
-> 20

5 6
1 2 10
1 3 1
2 3 1
2 5 10
3 4 10
4 5 10
-> 12

5 6
1 2 1
1 3 2
2 3 3
2 5 6
3 4 4
4 5 5
-> 7

5 7
1 2 1
1 3 0
2 3 1
2 5 1
3 4 1
4 5 0
3 5 0
-> 0

5 4
1 2 1
2 4 0
2 3 10
3 5 1
-> 12

5 7
1 2 2
1 3 1
2 3 1
2 5 1
3 4 1
4 5 1
1 2 1
-> 2
 */
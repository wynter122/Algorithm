import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

class Node{
    int num;
    int sec;
    int parent;

    public Node(int num, int sec, int parent){
        this.num = num;
        this.sec = sec;
        this.parent = parent;
    }
}

public class Main_13913 {
    static int[] parent = new int[100001];
    static int[] cost = new int[100001];
    static int INF = 1000000;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = sc.nextInt();
        int K = sc.nextInt();

        Arrays.fill(cost, INF);
        Arrays.fill(parent, -1);

        // dijkstra 수행
        dijkstra(N, K);

        // 결과 출력
        bw.write(cost[K] + "\n");

        int p = K;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(K);
        while(true){
            int tmpP = parent[p];
            if (tmpP == p)
                break;
            arr.add(tmpP);
            p = tmpP;
        }

        for (int i = arr.size()-1; i >= 0; i--)
            bw.write(arr.get(i) + " ");
        bw.flush();
        bw.close();
    }

    static void dijkstra(int N, int K){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0, N));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            if (cost[node.num] <= node.sec)          // 이미 최소 시간 찾았으면 패스
                continue;

            cost[node.num] = node.sec;              // 최소 시간 갱신
            parent[node.num] = node.parent;         // 부모 노드 갱신
            // 자식 노드 큐에 삽입
            if (node.num-1 >= 0)
                queue.add(new Node(node.num-1, node.sec+1, node.num));
            if (node.num+1 <= 100000)
                queue.add(new Node(node.num+1, node.sec+1, node.num));
            if(node.num*2 <= 100000)
                queue.add(new Node(node.num*2, node.sec+1, node.num));

            if (node.num == K)
                break;
        }
    }
}

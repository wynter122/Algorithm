import java.lang.reflect.Array;
import java.util.*;

class Star{
    double x;
    double y;
    public Star(double x, double y){
        this.x = x;
        this.y = y;
    }
}

class Node implements Comparable<Node> {
    int idx;
    double weight;
    public Node(int idx, double weight){
        this.idx = idx;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node){
        if (this.weight > node.weight)
            return 1;
        else if (this.weight < node.weight)
            return -1;
        return 0;
    }
}

public class Main_4386 {
    static double[][] graph;
    static Star[] stars;
    static double[] cost;
    static double INF = 100000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        graph = new double[N][N];           // 좌표간 거리 저장할 2차원 배열 초기화

        stars = new Star[N];
        cost = new double[N];
        Arrays.fill(cost, INF);
        for (int n = 0; n < N; n++){
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stars[n] = new Star(x, y);
        }

        // 좌표간 거리 저장
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (i != j)
                    graph[i][j] = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
            }
        }

        // Prim 수행
        Prim();

        double result = 0;
        for (double d : cost)
            result += d;
        System.out.println(Math.round(result * 100) / 100.0);
    }
    static void Prim(){
        PriorityQueue<Node> queue = new PriorityQueue<>();          // 다음에 탐색할 별 삽입
        boolean[] visited = new boolean[stars.length];

        queue.add(new Node(0, 0));             // 0번부터 시작
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (visited[curr.idx])           // 이미 방문했으면 패스
                continue;

            visited[curr.idx] = true;
            cost[curr.idx] = curr.weight;               // 최소거리 갱신
            for (int j = 0; j < stars.length; j++){
                if (!visited[j] )
                    queue.add(new Node(j, graph[curr.idx][j]));
            }
        }
    }
}

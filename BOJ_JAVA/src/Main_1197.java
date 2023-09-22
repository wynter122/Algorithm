import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex>{
    int v;
    int w;
    public Vertex(int v, int w){
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Vertex vertex){
        if (this.w > vertex.w)
            return 1;
        else if (this.w < vertex.w)
            return -1;
        return 0;
    }
}

public class Main_1197 {
    static ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
    static int cost[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());               // 정점 개수
        int E = Integer.parseInt(st.nextToken());               // 간선 개수

        // 배열 초기화
        for (int v = 0; v <= V; v++)
            graph.add(v, new ArrayList<>());
        cost = new int[V+1];

        // 간선 정보 받기
        for (int e = 0; e < E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Vertex(b, w));
            graph.get(b).add(new Vertex(a, w));
        }

        // Kruskal 수행
        Prim(V);

        int result = 0;
        for (int c : cost)
            result += c;

        System.out.println(result);

    }
    static void Prim(int V){
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[V+1];               // 방문 처리 배열
        queue.add(new Vertex(1, 0));        // 1번부터 시작

        while(!queue.isEmpty()){
            Vertex curr = queue.poll();
            if (visited[curr.v])
                continue;

            visited[curr.v] = true;
            cost[curr.v] = curr.w;
            for (Vertex next : graph.get(curr.v)){
                if (!visited[next.v])
                    queue.add(next);
            }
        }
    }
}

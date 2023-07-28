import java.io.*;
import java.util.*;

public class Main_18352 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] distance;
    static boolean[] visited;
    static int INF = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 노드 개수
        int M = Integer.parseInt(st.nextToken());       // 간선 개수
        int K = Integer.parseInt(st.nextToken());       // 요구 최단 거리
        int X = Integer.parseInt(st.nextToken());       // 출발 도시 번호

        distance = new int[N+1];            // 노드에 도달하는데 걸리는 최단 거리 저장할 배열
        visited = new boolean[N+1];         // 방문 체크 배열
        Arrays.fill(distance, INF);         // 무한대로 설정
        distance[X] = 0;                    // 출발노드 0으로 설정

        for (int n = 0; n <= N; n++)
            graph.add(n, new ArrayList<>());

        // 간선 정보 받음 (a, b : a->b)
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        // dijkstra 수행
        dijkstra(X);

        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 1; i < distance.length; i++){
            if (distance[i] == K)
                pQ.add(i);
        }


        if (pQ.isEmpty())
            System.out.println(-1);
        else{
            while(!pQ.isEmpty())
                System.out.println(pQ.poll());
        }
    }


    static void dijkstra(int X){            // 출발 노드
        Queue<Integer> queue = new LinkedList<>();          // 탐색할 노드 삽입할 큐
        queue.add(X);
        visited[X] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();
            for (int next : graph.get(curr)){
                if(!visited[next]){
                    distance[next] = Math.min(distance[next], distance[curr] + 1);
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int num;
    int weight;
    public Node(int next, int weight){
        this.num = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        if (this.weight > node.weight)
            return 1;
        else if (this.weight < node.weight)
            return -1;
        return 0;
    }
}

public class Main_5972 {
    static List<PriorityQueue<Node>> graph = new ArrayList<>();            // 인덱스별로 연결된 노드의 간선 저장
    static boolean[] visited;                               // 방문처리 할 배열
    static int[] distance;                                  // 최단거리 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 헛간개수, 목적지
        int M = Integer.parseInt(st.nextToken());       // 간선 개수

        visited = new boolean[N+1];
        for (int n = 0; n <= N; n++)
            graph.add(new PriorityQueue<>());

        for (int m = 0; m < M; m++){                // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int result = 0;

        int curr = 1;
        visited[curr] = true;
        while(!visited[N]){
            int tmpWeight = 0;
            int tmpCurr = curr;

            PriorityQueue<Node> queue = graph.get(curr);
            while(!queue.isEmpty()){
                Node node = queue.poll();
                if (visited[node.num])
                    continue;
                tmpWeight = node.weight;
                tmpCurr = node.num;
                break;
            }

            result += tmpWeight;
            curr = tmpCurr;
            visited[curr] = true;
        }
        System.out.println(result);
    }
}

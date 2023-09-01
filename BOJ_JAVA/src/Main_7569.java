import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int n, m, h;
    public Node (int n, int m, int h){
        this.n = n;
        this.m = m;
        this.h = h;
    }
}

public class Main_7569 {
    static int[][][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        graph = new int[N][M][H];
        Queue<Node> tomatoes = new LinkedList<>();
        for (int h = 0; h < H; h++){
            for (int n= 0; n < N; n++){
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    graph[n][m][h] = Integer.parseInt(st.nextToken());
                    if (graph[n][m][h] == 1)
                        tomatoes.add(new Node(n, m, h));
                }
            }
        }

        int day = 0;
        while (true){
            tomatoes = BFS(tomatoes);
            if (tomatoes.isEmpty())
                break;
            day++;
        }

        for (int h = 0; h < H; h++){
            for (int n= 0; n < N; n++){
                for (int m = 0; m < M; m++) {
                    if (graph[n][m][h] == 0){
                        day = -1;
                        break;
                    }
                }
            }
        }

        System.out.println(day);
    }

    static Queue<Node> BFS(Queue<Node> queue){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[] dh = {-1, 1};

        Queue<Node> nextQueue = new LinkedList<>();

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            // 같은 층 상하좌우 탐색
            for (int d = 0; d < 4; d++){
                int nn = curr.n + dx[d];
                int nm = curr.m + dy[d];
                if ((nn >= 0 && nn < graph.length) && (nm >= 0 && nm < graph[nn].length)){
                    if (graph[nn][nm][curr.h] == 0){
                        graph[nn][nm][curr.h] = 1;
                        nextQueue.add(new Node(nn, nm, curr.h));
                    }
                }
            }

            // 위, 아래 층 동일좌표 탐색
            for (int d = 0; d < 2; d++){
                int nh = curr.h + dh[d];
                if (nh >= 0 && nh < graph[0][0].length){
                    if (graph[curr.n][curr.m][nh] == 0){
                        graph[curr.n][curr.m][nh] = 1;
                        nextQueue.add(new Node(curr.n, curr.m, nh));
                    }
                }
            }
        }
        return nextQueue;
    }
}

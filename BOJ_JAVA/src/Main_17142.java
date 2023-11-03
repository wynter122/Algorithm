import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int x, y;
    int cost;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int cost){
        this(x, y);
        this.cost = cost;
    }

    @Override
    public int compareTo(Node node){
        if (this.cost < node.cost)
            return -1;
        else if (this.cost > node.cost)
            return 1;
        else
            return 0;
    }
}

public class Main_17142 {
    static final int WALL = -1;
    static final int NOT_ACTIVATED = -2;
    static final int ACTIVATED = -3;
    static int[][] lab;
    static ArrayList<Node> viruses = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        lab = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                int n = Integer.parseInt(st.nextToken());

                switch (n){
                    case 1:
                        lab[i][j] = WALL; break;
                    case 2:
                        lab[i][j] = NOT_ACTIVATED;
                        viruses.add(new Node(i, j));
                        break;
                }
            }
        }

        // virus 활성화 : recursion
        activate(0, 0, M);

        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);
    }

    static void activate(int idx, int cnt, int M){
        if (cnt == M){
            int[][] visited = new int[lab.length][lab.length];
            for (int i = 0; i < visited.length; i++)
                Arrays.fill(visited[i], Integer.MAX_VALUE);

            PriorityQueue<Node> pQ = new PriorityQueue<>();
            for (Node virus : viruses){
                if (lab[virus.x][virus.y] == ACTIVATED)
                    pQ.add(new Node(virus.x, virus.y, 0));
            }

            BFS(pQ, visited);

            // 현재 BFS 결과의 최대값 구하기
            int tmpResult = 0;
            for (int i = 0; i < visited.length; i++){
                for (int j = 0; j < visited.length; j++) {
                    if (visited[i][j] == Integer.MAX_VALUE && lab[i][j] != WALL && lab[i][j] != NOT_ACTIVATED)
                        return;
                    if (lab[i][j] != WALL && lab[i][j] != NOT_ACTIVATED)
                        tmpResult = Math.max(tmpResult, visited[i][j]);
                }
            }

            // 최소 시간으로 갱신
            result = Math.min(result, tmpResult);
            return;
        }

        cnt++;
        for (int v = idx; v < viruses.size() - (M-cnt); v++){
            Node virus = viruses.get(v);
            lab[virus.x][virus.y] = ACTIVATED;
            activate(v+1, cnt, M);
            lab[virus.x][virus.y] = NOT_ACTIVATED;
        }
        cnt--;
    }

    static void BFS(PriorityQueue<Node> pQ, int[][] visited){

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!pQ.isEmpty()){
            Node curr = pQ.poll();
            if (visited[curr.x][curr.y] <= curr.cost)
                continue;

            visited[curr.x][curr.y] = curr.cost;

            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < lab.length) && (ny >= 0 && ny < lab.length)){
                    if (lab[nx][ny] != WALL && visited[nx][ny] > curr.cost+1)
                        pQ.add(new Node(nx, ny, curr.cost+1));
                }
            }
        }
    }
}

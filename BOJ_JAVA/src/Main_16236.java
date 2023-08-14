import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
    int x, y;
    int size = 2;       // 초기 크기 2
    int eat = 0;
    int sec = 0;

    public Shark(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moving(int x, int y, int sec){
        this.x = x;
        this.y = y;
        this.sec += sec;
    }

    public void eating(int fishSize){
        eat++;              // 먹은 개수 증가
        if (size == eat){       // 사이즈만큼의 수를 먹었다면 사이즈 증가
            size++;
            eat = 0;
        }
    }
}

class Node implements Comparable<Node>{
    int x, y, sec;
    public Node (int x, int y, int sec){
        this.x = x;
        this.y = y;
        this.sec = sec;
    }

    @Override
    public int compareTo(Node node){
        if (this.sec < node.sec)
            return -1;
        else if (this.sec > node.sec)
            return 1;
        else{
            if (this.x < node.x)
                return -1;
            else if (this.x > node.x)
                return 1;
            else {
                if (this.y < node.y)
                    return -1;
                else if (this.y > node.y)
                    return 1;
            }
        }
        return 0;
    }
}

public class Main_16236 {
    static int[][] map;
    static Shark shark;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j);
                    map[i][j] = 0;
                }
            }
        }
        while(true){            // 위치를 옮길 때 마다 BFS 수행하여 먹잇감 찾기
            Node target = BFS();
            if (target == null)     // 더이상 먹을 물고기 없을 때
                break;

            // 이동
            shark.moving(target.x, target.y, target.sec);

            // 먹기
            shark.eating(map[target.x][target.y]);

            // 지도 갱신
            map[target.x][target.y] = 0;
        }

        System.out.println(shark.sec);
    }

    static Node BFS(){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        boolean[][] visited = new boolean[map.length][map.length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(shark.x, shark.y, 0));

        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (visited[curr.x][curr.y])
                continue;
            visited[curr.x][curr.y] = true; // 방문처리

            // 먹을 수 있는 물고기인지 확인
            if (1 <= map[curr.x][curr.y] && map[curr.x][curr.y] <= 6){
                if (map[curr.x][curr.y] < shark.size)
                    pQueue.add(curr);
            }

            // 인접 노드 탐색
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length)){      // 범위 체크
                    if (!visited[nx][ny] && map[nx][ny] <= shark.size)          // 방문하지 않았고, 상어의 크기보다 작거나 같은 물고기 칸
                        queue.add(new Node(nx, ny, curr.sec+1));
                }
            }
        }

        return pQueue.poll();
    }
}

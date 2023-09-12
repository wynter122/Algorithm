import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int x, y;
    int value;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int value){
        this(x, y);
        this.value = value;
    }

    @Override
    public int compareTo(Node node){
        if (this.value < node.value)
            return -1;
        else if (this.value > node.value)
            return 1;
        else
            return 0;
    }
}

public class Main_1937 {
    static int[][] map;
    static int[][] check;           // DP, 해당 칸까지 걸린 칸수 기재
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        check = new int[N][N];
        for (int[] arr : check)
            Arrays.fill(arr, 1);          // 자기 자신부터 시작

        // 작은 값부터 DFS 시작
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            for (int nn = 0; nn < N; nn++) {
                map[n][nn] = Integer.parseInt(st.nextToken());
                queue.add(new Node(n, nn, map[n][nn]));
            }
        }

        while(!queue.isEmpty()){
            DFS(queue.poll());
        }

        System.out.println(result);
    }

    static void DFS(Node start){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Stack<Node> stack = new Stack<>();
        stack.add(start);

        while(!stack.isEmpty()){
            Node curr = stack.pop();

            result = Math.max(result, check[curr.x][curr.y]);           // 최대값 갱신

            ArrayList<Node> tmp = new ArrayList<>();            // 스택에 넣을 인접노드 담아둘 리스트
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map.length)){
                    if (map[nx][ny] > map[curr.x][curr.y]){         // 현재 칸보다 큰 인접노드이고
                        if (check[nx][ny] < check[curr.x][curr.y] + 1) {        // 다음 칸까지 구해진 비용 < 현재 칸부터 구한 비용
                            check[nx][ny] = check[curr.x][curr.y] + 1;              // 최대 비용으로 갱신
                            tmp.add(new Node(nx, ny, map[nx][ny]));                 // 리스트에 담기
                        }
                    }
                }
            }

            tmp.sort(Comparator.reverseOrder());                // 값 내림차순으로 정렬 -> 스택은 오름차순으로 방문
            for (Node t : tmp)
                stack.add(t);
        }
    }
}

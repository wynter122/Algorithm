import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
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
}

public class Main_7562 {
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());

            matrix = new int[N][N];

            Node night = makeNode(new StringTokenizer(br.readLine()));
            Node target = makeNode(new StringTokenizer(br.readLine()));

            int result = BFS(night, target);
            System.out.println(result);

            // 초기화
            matrix = null;
        }
    }

    static int BFS(Node start, Node target){
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        Queue<Node> queue = new LinkedList<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if (curr.x == target.x && curr.y == target.y)
                return curr.cost;

            for (int d = 0; d < dx.length; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if ((nx >= 0 && nx < matrix.length) && (ny >= 0 && ny < matrix[nx].length)){
                    if (!visited[nx][ny]){
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny, curr.cost+1));
                    }
                }
            }
        }
        return -1;
    }

    static Node makeNode(StringTokenizer st){
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        return new Node(x, y);
    }
}

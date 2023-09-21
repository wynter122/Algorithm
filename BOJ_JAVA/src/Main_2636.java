import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_2636 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        Queue<Node> blank = new LinkedList<>();
        Queue<Node> cheese = new LinkedList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                graph[n][m] = Integer.parseInt(st.nextToken());
                if (graph[n][m] == 1)
                    cheese.add(new Node(n, m));
                else if (graph[n][m] == 0)
                    blank.add(new Node(n, m));
            }
        }

        // 빈 공간 판별
        checkBlank(blank);

        // 치즈 시뮬레이션
        int hours = 0;
        int prevCnt = cheese.size();
        while(true){
            cheese = melt(cheese);
            hours++;
            if (!cheese.isEmpty())
                prevCnt = cheese.size();
            else
                break;
        }

        System.out.println(hours);
        System.out.println(prevCnt);

    }

    static Queue<Node> melt(Queue<Node> cheese){
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        Queue<Node> meltCheese = new LinkedList<>();
        Queue<Node> nextCheese = new LinkedList<>();

        while(!cheese.isEmpty()){
            Node curr = cheese.poll();
            visited[curr.x][curr.y] = true;
            boolean flag = false;
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (graph[nx][ny] == 2 && !visited[nx][ny]) {
                    flag = true;
                    graph[curr.x][curr.y] = 0;
                    break;
                }
            }

            if (flag)
                meltCheese.add(curr);
            else
                nextCheese.add(curr);
        }

        checkBlank(meltCheese);

        return nextCheese;
    }
    static void checkBlank(Queue<Node> blank){
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        Queue<Node> result;
        while(!blank.isEmpty()){
            Node curr = blank.poll();
            if (!visited[curr.x][curr.y]) {
                result = DFS(curr, visited);
                while(!result.isEmpty())
                    graph[result.peek().x][result.poll().y] = 2;
            }
        }
    }

    static Queue<Node> DFS(Node start, boolean[][] visited){
        Queue<Node> result = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(start);

        boolean flag = false;
        while(!stack.isEmpty()){
            Node curr = stack.pop();
            if (visited[curr.x][curr.y])
                continue;

            visited[curr.x][curr.y] = true;
            result.add(curr);
            for (int d = 0; d < 4; d++){
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                if ((nx >= 0 && nx < graph.length) && (ny >= 0 && ny < graph[nx].length)){
                    if ((graph[nx][ny] == 0 || graph[nx][ny] == 2) && !visited[nx][ny])
                        stack.add(new Node(nx, ny));
                }else
                    flag = true;
            }
        }

        if (!flag)
            result.clear();
        return result;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_16724 {
    static char[][] matrix;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new int[N][M];
        for (int n = 0; n < N; n++){
            char[] line = br.readLine().toCharArray();
            matrix[n] = line;
            Arrays.fill(visited[n], -1);
        }

        // 분리 집합 개수 파악
        int cnt = 0;
        for (int n = 0; n < N; n++){
            for (int m = 0; m < M; m++){
                if (visited[n][m] == -1) {
                    boolean result = DFS(new Node(n, m), cnt);
                    if (result)
                        cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    static boolean DFS(Node start, int cnt){
        boolean result = true;
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();

        stack.add(start);
        visited[start.x][start.y] = cnt;
        while(!stack.isEmpty()){
            Node curr = stack.pop();

            queue.add(curr);

            Node next = null;
            char letter = matrix[curr.x][curr.y];
            switch (letter){
                case 'U':
                    next = new Node(curr.x-1, curr.y);
                    break;
                case 'D':
                    next = new Node(curr.x+1, curr.y);
                    break;
                case 'L':
                    next = new Node(curr.x, curr.y-1);
                    break;
                case 'R':
                    next = new Node(curr.x, curr.y+1);
                    break;
            }

            if ((next.x >= 0 && next.x < matrix.length) && (next.y >= 0 || next.y < matrix[0].length)) {
                if (visited[next.x][next.y] == -1) {
                    stack.add(next);
                    visited[next.x][next.y] = cnt;
                }else if (visited[next.x][next.y] != cnt){
                    cnt = visited[next.x][next.y];
                    result = false;
                }
            }
        }
        if (!result){
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                visited[node.x][node.y] = cnt;
            }
        }

        return result;
    }
}

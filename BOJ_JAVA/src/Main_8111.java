import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node{
    String number;
    int mod;

    public Node(String number, int mod){
        this.number = number;
        this.mod = mod;
    }
}

public class Main_8111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            String result = BFS(N);
            System.out.println(result);
        }
    }

    static String BFS(int N){
        boolean[] visited = new boolean[20001];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(String.valueOf(1), 1));

        while(!queue.isEmpty()){
            Node curr = queue.poll();

            // mod 에 나머지 연산
            int tmpResult = curr.mod % N;
            if (tmpResult == 0)
                return curr.number;

            if (curr.number.length() < 100) {
                // 끝에 0, 1 더한 모듈러 연산
                int newMod = (curr.mod * 10) % N;
                if (!visited[newMod]) {
                    queue.add(new Node(curr.number + "0", newMod));
                    visited[newMod] = true;
                }

                newMod = (curr.mod * 10 + 1) % N;
                if (!visited[newMod]) {
                    queue.add(new Node(curr.number + "1", newMod));
                    visited[newMod] = true;
                }
            }
        }
        return "BRAK";
    }

}

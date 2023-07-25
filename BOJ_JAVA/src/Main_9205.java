import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());

            Node[] allNodes = new Node[N+2];        // 모든 요소 저장할 배열
            int[][] needBeers = new int[N+2][N+2];        // 노드 별 필요한 맥주 개수 저장할 배열
            boolean[] visited = new boolean[N+2];   // 방문처리할 배열

            st = new StringTokenizer(br.readLine());
            allNodes[0] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));       // 집 위치 저장

            for (int n = 1; n <= N+1; n++){
                st = new StringTokenizer(br.readLine());
                allNodes[n] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));     // 노드 위치 저장

                for (int i = 0; i <= n; i++){               // 지금까지 저장된 노드들과 현재 추가된 노드 사이의 맥주 개수 구해서 2차원 배열에 저장
                    int distance = Math.abs(allNodes[i].x - allNodes[n].x) + Math.abs(allNodes[i].y - allNodes[n].y);
                    int bear = (int)Math.ceil((distance / 50.0));
                    bear = bear > 20 ? -1 : bear;           // 만약 맥주가 20개가 넘으면 -1 저장
                    needBeers[i][n] = bear;
                    needBeers[n][i] = bear;
                }
            }

            // BFS
            Queue<Integer> queue = new LinkedList<>();      // 방문할 노드 저장할 queue
            queue.add(0);   // 집 방문
            visited[0] = true;

            while(!queue.isEmpty()){
                int nodeIndex = queue.poll();
                for (int i = 1; i < allNodes.length; i++){
                    if (!visited[i] && needBeers[nodeIndex][i] >= 0){           // 아직 방문하지 않았고, 맥주의 개수가 0 ~ 20 사이면 queue 에 저장
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }

            String result = "";
            result = visited[N+1] == true ? "happy" : "sad";
            System.out.println(result);
        }
    }
}
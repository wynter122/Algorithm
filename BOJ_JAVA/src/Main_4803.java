import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main_4803 {
    static boolean[] visited = new boolean[501];
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("src/input.txt");
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n == 0 && m == 0)
                break;

            List<ArrayList<Integer>> tree = new ArrayList<>();            // 트리초기화
            for (int i = 0; i < n+1; i++)
                tree.add(new ArrayList<>());

            // 간선 정보 받기
            for (int i = 0; i < m; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();

                if (a > b){
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                tree.get(a).add(b);
                if (a!=b)
                    tree.get(b).add(a);
            }

            // 트리 방문
            int treeCnt = 0;
            for (int root = 1; root <= n; root++) {
                if(!visited[root]){             // 탐색되지 않은 노드 방문함
                    boolean result = DFS(tree, root);
                    if(result)
                        treeCnt++;
                }
            }
            if (treeCnt <= 0)
                System.out.println("Case " + caseNum + ": No trees.");
            else if (treeCnt == 1)
                System.out.println("Case " + caseNum + ": There is one tree.");
            else
                System.out.println("Case " + caseNum + ": A forest of " + treeCnt +" trees.");

            caseNum++;
            Arrays.fill(visited, false);
        }
    }
    static boolean DFS(List<ArrayList<Integer>> tree, int root){
        int node = 0;
        int edge = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int curr = queue.poll();
            if (!visited[curr])
                node++;
            visited[curr] = true;
            for (int currChild : tree.get(curr)){
                edge++;
                if (!visited[currChild])
                    queue.add(currChild);
            }
        }

        if (edge == 2 * (node-1))
            return true;
        else
            return false;
    }
}


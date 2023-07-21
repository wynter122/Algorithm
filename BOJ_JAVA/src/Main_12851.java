import java.util.*;

class Node{
    int num;
    int sec;
    int[] child = new int[3];

    public Node(int num, int sec ){
        this.num = num;
        this.sec = sec;

        child[0] = num-1;
        child[1] = num+1;
        child[2] = num*2;
    }
}

public class Main_12851 {
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Queue<Node> queue = new LinkedList<>();     // 방문할 노드 저장할 큐

        int sec = 0;
        queue.add(new Node(N, sec++));       // 루트노드 삽입

        Node resultNode = null;
        int queueSize = queue.size();
        int cnt = 0;

        while(!queue.isEmpty()){
            Node node = queue.poll();           // 방문할 노드 꺼내기

            if (node.num == K){
                resultNode = node;
                cnt++;
            }else {
                for (int n : node.child) {
                    if (n >= 0 && n < 100001) {
                        if (!visited[n])         // queue에 넣을 때 방문체크
                            queue.add(new Node(n, sec));

                    }
                }
            }
            queueSize--;
            if (queueSize <= 0){
                sec++;
                queueSize = queue.size();
            }
        }

        System.out.println(resultNode.sec);
        System.out.println(cnt);
    }
}

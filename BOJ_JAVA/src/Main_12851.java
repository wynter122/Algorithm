import java.util.*;

class Node{
    int num;
    int sec;
    int[] child = new int[3];

    public Node(int num, int sec){
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
        HashSet<Node> hashSet = new HashSet<>();      // 한 텀에 방문처리 할 노드 모아두는 hashset

        int sec = 0;
        queue.add(new Node(N, sec++));       // 루트노드 삽입

        Node resultNode = null;
        int queueSize = queue.size();
        int cnt = 0;

        while(!queue.isEmpty()){
            Node node = queue.poll();           // 방문할 노드 꺼내기

            if (!visited[node.num]){            // 아직 방문하지 않았으면
                hashSet.add(node);                  // 방문처리 할 노드 담아두기
                if (node.num == K){
                    resultNode = node;
                    cnt++;      // K 를 찾으면 cnt 증가
                }else {
                    for (int n : node.child) {      // K 를 못찾으면 자식노드 queue 에 삽입
                        if (n >= 0 && n <= 100000) {
                            queue.add(new Node(n, sec));
                        }
                    }
                }
            }

            queueSize--;
            if (queueSize <= 0){
                if (cnt > 0)        // K를 하나라도 찾았으면 stop
                    break;

                Iterator<Node> it = hashSet.iterator();     // 방문처리 보류한 노드들 처리
                while(it.hasNext())
                    visited[it.next().num] = true;
                hashSet.clear();
                sec++;
                queueSize = queue.size();
            }
        }

        System.out.println(resultNode.sec);
        System.out.println(cnt);
    }
}
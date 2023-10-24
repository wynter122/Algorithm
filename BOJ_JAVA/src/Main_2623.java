import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int n;
    Set<Node> nextNodes;
    int prevCnt;

    public Node(int n){
        this.n = n;
        nextNodes = new HashSet<>();
        prevCnt = 0;
    }

    public void addNext(Node node){
        if (nextNodes.contains(node))
            return;
        nextNodes.add(node);
        node.prevCnt++;
    }
}

public class Main_2623 {
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new Node[N + 1];
        for (int n = 1; n <= N; n++)
            nodes[n] = new Node(n);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());

            Node prev = null;
            for (int c = 0; c < C; c++) {
                Node curr = nodes[Integer.parseInt(st.nextToken())];
                if (prev != null)
                    prev.addNext(curr);
                prev = curr;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int n = 1; n <= N; n++) {
            if (nodes[n].prevCnt == 0)
                queue.add(nodes[n]);
        }

        Topology(queue, result);
        if (result.size() < N)
            System.out.println(0);
        else {
            StringBuilder sb = new StringBuilder();
            for (int n : result)
                sb.append(n).append("\n");
            System.out.println(sb);
        }
    }

    static void Topology(Queue<Node> queue, ArrayList<Integer> result) {
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            result.add(curr.n);
            for (Node next : curr.nextNodes) {
                next.prevCnt--;
                if (next.prevCnt == 0)
                    queue.add(next);
            }
        }
    }
}
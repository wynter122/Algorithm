import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    int start, end;
    int min;
    int max;

    public Node(int start, int end){
        this.start = start;
        this.end = end;
        min = Integer.MAX_VALUE;
        max = -1;
    }
}

class Value {
    int min;
    int max;

    public Value(int min, int max){
        this.min = min;
        this.max = max;
    }
}

public class Main_2357 {
    static Node[] Tree;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N : 리프노드 개수, M : 구간 개수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 숫자 받기
        numbers = new int[N+1];
        for (int n = 1; n <= N; n++)
            numbers[n] = Integer.parseInt(br.readLine());

        // 트리 노드 개수 정하기
        int minNodes = 2 * N;
        int nodes = 0;
        double i = 1;
        while(true){
            nodes = (int) Math.pow(2, i++);
            if (nodes >= minNodes)
                break;
        }

        // nodes 크기만큼 트리 만들기
        Tree = new Node[nodes];

        // 트리에 원소 넣기
        makeTree(1, 1, N);

        // 구간 최대 최소 구하기
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Value result = find(1, start, end);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }

        System.out.print(sb);
    }

    static Value find(int idx, int start, int end){
        Node curr = Tree[idx];
        if (curr.start == start && curr.end == end)
            return new Value(curr.min, curr.max);

        int mid = curr.start + (curr.end - curr.start)/2;

        if (end <= mid){            // left 트리로 이동
            return find(2*idx, start, end);
        }else if (start > mid){     // right 트리로 이동
            return find(2*idx+1, start, end);
        }else{  // 나눠서 이동
            Value left = find(2*idx, start, mid);
            Value right = find(2*idx+1, mid+1, end);
            Value ret = new Value(Math.min(left.min, right.min), Math.max(left.max, right.max));
            return ret;
        }
    }

    static void makeTree(int idx, int start, int end){
        if (idx >= Tree.length)
            return;

        Tree[idx] = new Node(start, end);

        if (start == end) {
            Tree[idx].min = Tree[idx].max = numbers[start];
            return;
        }

        int left = 2*idx;
        int right = 2*idx+1;
        int nextEnd = start + (end-start)/2;
        makeTree(left, start, nextEnd);
        makeTree(right, nextEnd+1, end);

        // 자식 비교해서 최대 최소 저장하기
        Tree[idx].min = Math.min(Tree[left].min, Tree[right].min);
        Tree[idx].max = Math.max(Tree[left].max, Tree[right].max);
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    int n;
    Node left = null;
    Node right = null;

    public Node(int n){
        this.n = n;
    }
}

public class Main_4256 {
    static int[] pre;
    static int[] in;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++){
            sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());            // 원소 개수
            pre = new int[N];
            in = new int[N];

            // 순회 결과 받기
            getArr(pre, new StringTokenizer(br.readLine()));
            getArr(in, new StringTokenizer(br.readLine()));

            // 트리 만들기
            Node tree = makeTree(0, N-1, 0, N-1);

            // 출력
            postOrder(tree);
            System.out.println(sb);

            // 초기화
            pre = null;
            in = null;
        }
    }

    static Node makeTree(int preStart, int preEnd, int inStart, int inEnd){
        if (preStart > preEnd)
            return null;

        // pre 부분배열의 첫번째 원소가 해당 서브트리의 루트
        int root = pre[preStart];

        Node curr = new Node(root);
        // 원소가 1개면 해당 서브트리 return
        if (preStart == preEnd)
            return curr;

        // in 부분배열의 중간부터 루트 찾기
        int low = inStart + (inEnd-inStart) / 2;
        int high = (inEnd-inStart) % 2 == 0 ? low : low + 1;

        int rootIdx = -1;
        while(true){
            // 루트 찾으면 멈추기
            if (in[low] == root){
                rootIdx = low;
                break;
            }else if (in[high] == root){
                rootIdx = high;
                break;
            }

            low--;
            high++;
        }

        // 왼쪽 서브트리 원소 개수
        int leftCnt = rootIdx - inStart - 1;

        // root 인덱스를 기준으로 왼쪽, 오른쪽 서브트리 구하기
        curr.left = makeTree(preStart+1, preStart + 1 + leftCnt, inStart, rootIdx-1);
        curr.right = makeTree(preStart + 2 + leftCnt, preEnd, rootIdx+1, inEnd);

        return curr;
    }

    static void postOrder(Node root){
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        sb.append(root.n).append(" ");
    }

    static void getArr(int[] arr, StringTokenizer st){
        for (int n = 0; n < arr.length; n++)
            arr[n] = Integer.parseInt(st.nextToken());
    }
}

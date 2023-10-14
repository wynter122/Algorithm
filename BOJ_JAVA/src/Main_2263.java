import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int root;
    Node leftChild;
    Node rightChild;

    public Node (int root){
        this.root = root;
    }
}

class Range {
    int start;
    int end;
    public Range (int start, int end){
        this.start = start;
        this.end = end;
    }
}

public class Main_2263 {
    static int[] inorder;
    static int[] postorder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        inorder = new int[N];
        postorder = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n++){
            inorder[n] = Integer.parseInt(st1.nextToken());
            postorder[n] = Integer.parseInt(st2.nextToken());
        }

        // root 노드 == postorder 의 맨 끝
        Range inRange = new Range(0, N-1);
        Range postRange = new Range(0, N-1);
        Node root = getSubtree(inRange, postRange);
        preOrder(root);

    }

    static Node getSubtree(Range inRange, Range postRange){
        if (inRange.start > inRange.end || postRange.start > inRange.end)
            return null;

        if (inRange.start == inRange.end)
            return new Node(inorder[inRange.start]);

        // root 노드 == postorder 의 맨 끝
        Node root = new Node(postorder[postRange.end]);

        // root 를 기준으로 inorder 왼쪽, 오른쪽 서브트리 나뉨
        Range newLeftIn = new Range(inRange.start, inRange.start);
        Range newLeftPost = new Range(postRange.start, postRange.start);
        Range newRightIn = new Range(inRange.start, inRange.end);
        Range newRightPost = new Range(postRange.start, postRange.end-1);

        int i = 0;
        int central = inRange.start + (inRange.end - inRange.start)/2;

        while(true){
            int idx = -1;
            // 중간의 왼쪽
            if (central-i >= 0 && inorder[central-i] == root.root)
                idx = central-i;

            // 중간의 오른쪽
            if (central+i < inorder.length && inorder[central+i] == root.root)
                idx = central+i;

            if (idx != -1) {
                newLeftIn.end = idx - 1;
                newRightIn.start = idx + 1;
                newLeftPost.end += (idx-inRange.start)-1;
                newRightPost.start += (idx-inRange.start);
                break;
            }
            i++;
        }


        // 왼쪽 서브트리 형성
        root.leftChild = getSubtree(newLeftIn, newLeftPost);

        // 오른쪽 서브트리 형성
        root.rightChild = getSubtree(newRightIn, newRightPost);

        return root;
    }

    static void preOrder(Node root){
        if (root == null)
            return;
        System.out.print(root.root + " ");
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }
}

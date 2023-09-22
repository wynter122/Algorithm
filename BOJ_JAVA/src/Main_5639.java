import java.util.ArrayList;
import java.util.Scanner;

class Node{
    int num;
    Node leftChild;
    Node rightChild;
    public Node(int num){
        this.num = num;
    }
}

public class Main_5639 {
    static ArrayList<Node> binaryTree = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        binaryTree.add(0, new Node(sc.nextInt()));
        while(sc.hasNextInt()){
            int curr = sc.nextInt();
            insertTree(curr);
        }
        // preOrder(binaryTree.get(0));
        postOrder(binaryTree.get(0));
    }

    static void insertTree(int n){
        Node root = binaryTree.get(0);
        Node newNode = new Node(n);
        while (true){
            if (root.num > n){
                if (root.leftChild == null){
                    root.leftChild = newNode;
                    break;
                }else
                    root = root.leftChild;
            }else if (root.num < n){
                if (root.rightChild == null){
                    root.rightChild = newNode;
                    break;
                }else
                    root = root.rightChild;
            }

        }
    }

    static void preOrder(Node root){
        if (root == null)
            return;
        System.out.println(root.num);
        preOrder(root.leftChild);
        preOrder(root.rightChild);
    }

    static void postOrder(Node root){
        if (root == null)
            return;

        postOrder(root.leftChild);
        postOrder(root.rightChild);
        System.out.println(root.num);

    }
}

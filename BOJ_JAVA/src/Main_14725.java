import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Node{
    HashMap<String, Node> childs = new HashMap<>();
}

public class Main_14725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node root = new Node();                 // <String, Node> 형태의 자식을 가지는 Node
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            Node curr = root;
            for (int m = 0; m < M; m++){
                String str = st.nextToken();
                if (curr.childs.containsKey(str))
                    curr = curr.childs.get(str);
                else{
                    curr.childs.put(str, new Node());
                    curr = curr.childs.get(str);
                }
            }
        }
        printNode(root, "");

    }

    static void printNode (Node node, String bar){
        Object[] keys = node.childs.keySet().toArray();
        Arrays.sort(keys);

        for (Object str : keys){
            System.out.println(bar + str);
            printNode(node.childs.get(str), bar+"--");
        }
    }
}

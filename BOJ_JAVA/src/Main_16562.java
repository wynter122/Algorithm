import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16562 {
    static int[] friendCost;
    static int[] tree;
    static boolean[] isRoot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 친구비용 정보 받기
        friendCost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; n++)
            friendCost[n] = Integer.parseInt(st.nextToken());

        // 친구 관계정보 받기
        tree = new int[N+1];
        isRoot = new boolean[N+1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = i;
            isRoot[i] = true;
        }

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            // 부모 찾기
            int tmpA = find(Integer.parseInt(st.nextToken()));
            int tmpB = find(Integer.parseInt(st.nextToken()));

            if (tmpA != tmpB){          // 부모가 다르다면
                int[] swapResult = swap(tmpA, tmpB);
                int parent = swapResult[0];
                int child = swapResult[1];

                tree[child] = parent;
                isRoot[child] = false;
            }
        }

        // 친구 비용 계산
        int totalCost = 0;
        for (int friend = 1; friend <= N; friend++){
            if (!isRoot[friend])            // 루트가 아니면 패스
                continue;
            totalCost += friendCost[friend];
        }

        if (K >= totalCost)
            System.out.println(totalCost);
        else
            System.out.println("Oh no");
    }
    static int find(int a){
        int child = a;
        int parent;
        while(true){
            parent = tree[child];               // 현재 친구의 부모 찾기
            if (child == parent)                // 부모가 자기 자신 -> 루트
                break;
            // 루트가 아니라면
            tree[child] = tree[parent];         // 압축
            child = parent;
        }
        return parent;
    }
    static int[] swap(int a, int b){
        if(friendCost[a] > friendCost[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }else if(friendCost[a] == friendCost[b]){
            if (a > b){
                int tmp = a;
                a = b;
                b = tmp;
            }
        }
        int[] result = {a, b};
        return result;
    }
}

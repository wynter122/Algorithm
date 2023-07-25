import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];                   // 각 집합의 루트 저장할 배열
        for (int n = 0; n <= N; n++)                // 각 집합의 루트를 자기 자신으로
            arr[n] = n;

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (operator){
                case 0:         // 합집합 만들기
                    union(arr, a, b);
                    break;

                case 1:         // 같은 집합인지 확인하기
                    int aParent = find(arr, a);
                    int bParent = find(arr, b);
                    if (aParent == bParent)
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                    break;
            }
        }
    }

    static void union(int[] arr, int a, int b){
        // 각 집합의 루트 찾기
        int aParent = find(arr, a);
        int bParent = find(arr, b);
        if (aParent != bParent) {
            if (aParent < bParent) {
                arr[bParent] = aParent;
                arr[b] = aParent;
            }else {
                arr[aParent] = bParent;
                arr[a] = bParent;
            }
        }
    }
    static int find(int[] arr, int num){
        int prev = num;
        int parent = arr[num];
        while(true){
            if (arr[parent] == parent)
                break;
            parent = arr[parent];
            arr[prev] = parent;
            prev = parent;

        }
        return parent;
    }
}

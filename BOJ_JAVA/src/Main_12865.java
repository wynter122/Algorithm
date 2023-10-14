import java.util.Arrays;
import java.util.Scanner;

class Item implements Comparable<Item> {
    int w, v;

    public Item (int w, int v){
        this.w = w;
        this.v = v;
    }

    @Override
    public int compareTo(Item item){
        if (this.w < item.w)
            return -1;
        else if (this.w > item.w)
            return 1;
        return 0;
    }
}
public class Main_12865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        Item[] items = new Item[N+1];
        items[0] = new Item(0, 0);
        for (int n = 1; n <= N; n++){
            int W = sc.nextInt();
            int V = sc.nextInt();
            items[n] = new Item(W, V);
        }
        Arrays.sort(items);             // 무게순으로 아이템 정렬

        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++){
            Item item = items[i];                   // 행 번호 == 오름차순으로 정렬된 아이템 번호
            for (int w = 1; w <= K; w++){           // 담을 수 있는 최대 무게
                if (w < item.w)                       // 현재 아이템을 담을 수 없는 경우
                    dp[i][w] = dp[i-1][w];                  // 해당 무게에서 i-1 번까지 담았을 때의 최적해 그대로 가져옴
                else                                  // 현재 아이템을 담을 수 있는 경우
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-item.w] + item.v);        // (해당 무게에서 i-1 번까지 담았을 때의 최적해) VS (현재 아이템 가치 + 현재 무게에서 현재 아이템 무게를 뺐을 때 최적해) 비교해서 큰 값 가져옴
            }
        }

        System.out.println(dp[N][K]);
    }
}

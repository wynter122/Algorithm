import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();          // 낮은 숫자가 우선순위인 priority queue 생성

        for (int n = 0; n < N; n++)
            pQueue.add(sc.nextInt());

        int result = 0;
        while(true){
            int x = pQueue.poll();
            if (pQueue.isEmpty())       // queue 가 비면 멈추기
                break;
            int y = pQueue.poll();

            int tmpResult = x + y;
            result += tmpResult;
            pQueue.add(tmpResult);
        }

        System.out.println(result);
    }
}

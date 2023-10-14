import javax.lang.model.element.Element;
import java.util.*;

class Assignment implements Comparable<Assignment>{
    int num;
    long cost;
    int deadline;
    public Assignment(int num, long cost, int deadline){
        this.num = num;
        this.cost = cost;
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Assignment ele){
        if (this.deadline < ele.deadline)
            return -1;
        else if (this.deadline > ele.deadline)
            return 1;
        else{
            if (this.cost > ele.cost)
                return -1;
            else
                return 1;
        }
    }
}
public class Main_1781 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        PriorityQueue<Assignment> assignments = new PriorityQueue<>();

        for (int n = 1; n <= N; n++){
            int d = sc.nextInt();
            long c = sc.nextLong();
            assignments.add(new Assignment(n, c, d));
        }

        long totalCost = 0;
        PriorityQueue<Long> costQueue = new PriorityQueue<>();                // 현재까지 푼 숙제 담아둘 큐
        while(!assignments.isEmpty()) {
            Assignment assign = assignments.poll();
            if (assign.deadline > costQueue.size()){            // 아직 데드라인이 남았을 때
                totalCost += assign.cost;           // 비용 더하기
                costQueue.add(assign.cost);              // 비용 큐에 푼 문제 삽입
            } else{
                if(costQueue.peek() < assign.cost){                  // 데드라인이 더 길고 마감이 가능한 숙제 비용 > 데드라인이 짧은 숙제의 비용일 경우
                    totalCost -= costQueue.poll();
                    totalCost += assign.cost;
                    costQueue.add(assign.cost);
                }
            }
        }

        System.out.println(totalCost);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2696 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            System.out.println((N+1)/2);

            PriorityQueue<Integer> descQueue = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> ascQueue = new PriorityQueue<>();
            for (int n = 1; n <= N; n++){
                int num = Integer.parseInt(st.nextToken());

                if (descQueue.size() == ascQueue.size()){               // desc에 삽입
                    if (descQueue.isEmpty())
                        descQueue.add(num);
                    else {
                        if (ascQueue.peek() < num) {
                            descQueue.add(ascQueue.poll());
                            ascQueue.add(num);
                        } else
                            descQueue.add(num);
                    }
                }else if (descQueue.size() == ascQueue.size()+1){       // asc에 삽입
                    if (ascQueue.isEmpty()){
                        if (descQueue.peek() > num){
                            ascQueue.add(descQueue.poll());
                            descQueue.add(num);
                        }else
                            ascQueue.add(num);
                    }
                    else {
                        if (descQueue.peek() > num) {
                            ascQueue.add(descQueue.poll());
                            descQueue.add(num);
                        } else
                            ascQueue.add(num);
                    }
                }
                if (n%2 == 1)
                    System.out.print(descQueue.peek() + " ");

                if (n%10 == 0)
                    st = new StringTokenizer(br.readLine());
            }
            System.out.println();
        }

    }
}

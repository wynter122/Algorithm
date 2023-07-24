import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Pair{
    int num;
    int index;

    public Pair(int num, int index){
        this.num = num;
        this.index = index;
    }
}
public class Main_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        Stack<Pair> stack = new Stack<>();       // 탑의 높이와 위치 저장할 stack
        for (int n = 1; n <= N; n++){
            int num = Integer.parseInt(st.nextToken());
            Pair pair = new Pair(num, n);
            if (n==1){
                arr[n] = 0;     // 가장 왼쪽에 위치한 탑의 도달위치는 0
                stack.push(pair);  // stack 에 삽입
                continue;
            }

            int target = 0;
            while(!stack.empty()){
                if (stack.peek().num < num)       // stack 의 상단에 있는 숫자가 num보다 작으면 꺼내기
                    stack.pop();
                else {                        // n 보다 큰 숫자를 stack 에 남겨두기
                    target = stack.peek().index;
                    break;
                }
            }
            stack.push(pair);      // num 삽입
            arr[n] = target;    // 배열에 num 의 도달점 메모
        }

        for (int i = 1; i <= N; i++)
            System.out.print(arr[i] + " ");
    }
}

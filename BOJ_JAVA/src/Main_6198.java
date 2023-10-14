import java.util.Scanner;
import java.util.Stack;

class Building {
    int num;
    int height;
    public Building(int num, int height){
        this.num = num;
        this.height = height;
    }
}

public class Main_6198 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long[] answer = new long[N+1];

        Stack<Building> stack = new Stack<>();
        for (int n = 1; n <= N; n++){
            Building curr = new Building(n, sc.nextInt());              // 빌딩 번호, 빌딩 높이 객체

            long cnt = 0;
            while(!stack.empty()){
                if (stack.peek().height > curr.height) {      // stack 의 head 에 있는 높이가 현재 받은 높이보다 높다면 break
                    answer[stack.peek().num] += cnt;
                    cnt = 0;
                    break;
                }
                Building out = stack.pop();                 // 현재 추가된 빌딩보다 낮거나 같은 빌딩 pop
                answer[out.num] += cnt;                    // out 된 building 에 cnt 더하기
                cnt = answer[out.num] + 1;
            }
            stack.push(curr);
        }

        // stack 에 남은 빌딩 처리
        long cnt = 0;
        while(!stack.empty()){
            Building out = stack.pop();
            answer[out.num] += cnt;
            cnt = answer[out.num] + 1;
        }

        long result = 0;
        for (long c : answer)
            result += c;

        System.out.println(result);
    }
}

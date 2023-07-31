import java.util.Scanner;
import java.util.Stack;

public class Main_6198 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        long result = 0;
        for (int n = 1; n <= N; n++){
            int curr = sc.nextInt();              // 새로 들어온 빌딩 높이
            while(!stack.empty()){
                if (stack.peek() > curr) {      // stack 의 top > 현재 높이
                    result += stack.size();                     // 새로 들어올 건물을 볼 수 있는 기존 건물 개수만큼 ++
                    break;
                }
                stack.pop();                 // 현재 추가된 빌딩보다 낮거나 같은 빌딩 pop -> stack 이 내림차순이 되도록 만들어줌
            }
            stack.push(curr);
        }

        System.out.println(result);
    }
}

/*
6
10
3
7
4
12
2
 */
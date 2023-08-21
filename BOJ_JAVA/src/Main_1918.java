import java.util.Scanner;
import java.util.Stack;
import java.util.regex.*;

public class Main_1918 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] str = sc.nextLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        String PATTERN_OPERATOR = "[*/+-]";
        String result = "";
        for (char ele : str){                   // 문자열을 순회하며 조건에 따라 스택 삽입, 제거
            if (Pattern.matches(PATTERN_OPERATOR, String.valueOf(ele))){            // 연산자일 경우
                if (stack.isEmpty()){
                    stack.add(ele);
                    continue;
                }

                char tmp = stack.peek();
                // 우선순위 비교
                if (tmp == '(' || ((tmp == '+' || tmp == '-') && (ele == '*' || ele =='/')))               // 새로 들어온 연산자의 우선순위가 더 높을 때 -> 스택에 삽입
                    stack.add(ele);
                else {                                                                     // 새로 들어온 연산자의 우선순위가 낮거나 같을 때 -> stack에서 pop
                    while(!stack.isEmpty()) {
                        if (stack.peek() == '(' || ((stack.peek() == '+' || stack.peek() == '-') && (ele == '*' || ele == '/')))        // ( 혹은 더 낮은순위의 연산자를 만날 때 까지 pop
                            break;
                        result += stack.pop();
                    }
                    stack.add(ele);
                }
            }else if (ele == '('){                      // 괄호 시작 -> Stack에 삽입
                stack.add(ele);
            }else if (ele == ')'){                      // 괄호 닫힘 -> 가장 처음 나오는 ( 를 만날 때 까지 Stack에서 pop
                while(!stack.isEmpty()){
                    char tmp = stack.pop();
                    if (tmp == '(')
                        break;
                    result += tmp;
                }
            }else
                result += ele;
        }

        while(!stack.isEmpty())
            result += stack.pop();

        System.out.println(result);
    }
}

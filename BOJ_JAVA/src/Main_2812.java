import java.io.*;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] num = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();                  // 수 삽입할 스택
        int remove = 0;
        for (int n = 0; n < num.length; n++) {
            int curr = Integer.valueOf(num[n] - '0');
            if (stack.empty()) {
                stack.add(curr);
                continue;
            }
            while(!stack.empty() && remove < K) {         // 아직 지울 숫자가 남아있고
                if (stack.peek() < curr) {   // stack의 top 이 현재 수보다 작다면
                    stack.pop();
                    remove++;
                }else
                    break;
            }
            stack.push(curr);
        }

        while(remove < K){
            stack.pop();
            remove++;
        }

        Iterator<Integer> it = stack.iterator();
        while(it.hasNext())
            bw.write(String.valueOf(it.next()));

        bw.flush();
        bw.close();
    }
}

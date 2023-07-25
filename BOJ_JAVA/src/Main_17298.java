import java.io.*;
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

public class Main_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] result = new int[N];
        Stack<Pair> stack = new Stack<>();               // 입력된 수 중 오큰수를 발견하지 못한 수 저장할 stack

        st = new StringTokenizer(br.readLine());

        int idx = 0;
        stack.push(new Pair(Integer.parseInt(st.nextToken()), idx++));      // 처음 수 stack 에 삽입
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            while(!stack.empty()){
                if(stack.peek().num < n)        // stack 의 top 이 n 보다 작으면
                    result[stack.pop().index] = n;    // pop 한 후 해당 수의 오큰수를 n 으로 저장
                else
                    break;
            }
            stack.push(new Pair(n, idx++));
        }

        for (int r : result) {
            r = r == 0 ? -1 : r;
            bw.write(r + " ");
        }
        bw.flush();
        bw.close();
    }
}

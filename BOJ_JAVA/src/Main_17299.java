import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Element{
    int idx;
    int num;
    int cnt;
    public Element(int idx, int num, int cnt){
        this.idx = idx;
        this.num = num;
        this.cnt = cnt;
    }
}

public class Main_17299 {
    static int[] counter = new int[1000001];                // 원소의 등장 횟수 담을 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        ArrayList<Element> arrayList = new ArrayList<>();           // 수열 입력 받으면서 원소 등장횟수 파악
        int i = 0;
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            counter[n]++;
            arrayList.add(new Element(i++, n, 0));
        }

        int[] result = new int[N];
        Stack<Element> stack = new Stack<>();                       // 원소 담을 stack
        for (Element ele : arrayList) {
            ele.cnt = counter[ele.num];                     // 카운트 갱신
            if (!stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    Element top = stack.peek();                         // stack 의 탑에 있는 원소
                    if (top.cnt >= ele.cnt)                             // 현재 원소의 cnt 보다 크거나 같으면 break
                        break;

                    result[top.idx] = ele.num;                          // 현재 원소의 cnt 보다 작으면 : 현재 원소 값을 결과 배열의 top 의 인덱스에 삽입
                    stack.pop();
                }
            }
            stack.add(ele);
        }

        while(!stack.isEmpty())
            result[stack.pop().idx] = -1;

        for (int r : result)
            bw.write(r + " ");
        bw.flush();
        bw.close();
    }
}

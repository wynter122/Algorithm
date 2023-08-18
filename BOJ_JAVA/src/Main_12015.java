import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_12015 {
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++){
            int curr = Integer.parseInt(st.nextToken());
            if (n == 0){
                arr.add(curr);
                continue;
            }

            if (arr.get(arr.size()-1) < curr){                  // 현재까지 모은 수들보다 더 큰 수가 들어왔을 때
                arr.add(curr);
            }else{
                replace(curr);
            }
        }

        System.out.println(arr.size());
    }

    static void replace(int n){
        int start = 0;
        int end = arr.size()-1;

        while(start <= end){
            int curr = start + (int)Math.ceil((end-start) / 2.0);

            if (arr.get(curr) >= n){
                if (curr == 0 || arr.get(curr-1) < n){                       // n보다 큰 첫번째 수를 찾으면
                    arr.set(curr, n);
                    break;
                }else
                    end = curr-1;
            }else if (arr.get(curr) < n){
                start = curr;
            }
        }
    }
}

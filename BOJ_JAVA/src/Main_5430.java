import java.io.*;
import java.util.StringTokenizer;

public class Main_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            char[] expression = br.readLine().toCharArray();          // R, D 로 이루어진 수식 입력
            int N = Integer.parseInt(br.readLine());    // 입력받을 배열 크기
            String[] array = new String[N];

            String strArray = br.readLine();            // [n1, n2, ...] 로 입력되는 배열
            if (N > 0) {
                strArray = strArray.split("\\[")[1];    // [ 삭제
                strArray = strArray.split("]")[0];      // ] 삭제
                array = strArray.split(",");
            }

            int frontPointer = 0;     // D 에 따라 움직일 pointer (if reverseFlag == false)
            int backPointer = array.length-1;       // D에 따라 움직일 pointer (if reverseFlag == true)
            boolean reverseFlag = false;        // R 에 따라 설정될 flag
            boolean resultFlag = true;
            for (int i = 0; i < expression.length; i++) {
                char curr = expression[i];
                if (curr == 'R')
                    reverseFlag = reverseFlag == false ? true : false;
                else if (curr == 'D'){
                    if (frontPointer > backPointer){
                        resultFlag = false;
                        break;
                    }
                    if (!reverseFlag)           // 정방향
                        frontPointer++;
                    else
                        backPointer--;          // 역방향
                }
            }

            // 결과 출력
            if (resultFlag){
                bw.write("[");
                if (!reverseFlag) {
                    for (int i = frontPointer; i <= backPointer; i++) {
                        bw.write(array[i]);
                        if (i != backPointer)
                            bw.write(",");
                    }
                }else{
                    for (int i = backPointer; i >= frontPointer; i--) {
                        bw.write(array[i]);
                        if (i != frontPointer)
                            bw.write(",");
                    }
                }
                bw.write("]\n");
            }else{
                bw.write("error\n");
            }
        }
        bw.flush();
        bw.close();
    }
}

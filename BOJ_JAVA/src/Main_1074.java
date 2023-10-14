import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074 {
    static int cntStart = 0;
    static int cntEnd;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cntEnd = (int) (Math.pow(2, N) * Math.pow(2, N));

        int len = (int) Math.pow(2, N);             // 0 ~ len-1

        recursion(0, len-1, 0, len-1);
    }

    static void recursion(int rowStart, int rowEnd, int colStart, int colEnd){
        if (rowStart == rowEnd && colStart == colEnd){
            if (rowStart == r && colStart == c) {
                System.out.println(cntStart);
                System.exit(0);
            }
            return;
        }

        int rowMid = rowStart + (rowEnd-rowStart)/2;
        int colMid = colStart + (colEnd-colStart)/2;

        int cntQuarter = (cntEnd - cntStart) / 4;
        if (r >= rowStart && r <= rowMid){
            if (c >= colStart && c <= colMid) {
                makeCnt(cntQuarter, 0);
                recursion(rowStart, rowMid, colStart, colMid);
            }
            else if (c > colMid && c <= colEnd) {
                makeCnt(cntQuarter, 1);
                recursion(rowStart, rowMid, colMid + 1, colEnd);
            }
        } else if (r > rowMid && r <= rowEnd){
            if (c >= colStart && c <= colMid) {
                makeCnt(cntQuarter, 2);
                recursion(rowMid + 1, rowEnd, colStart, colMid);
            }
            else if (c > colMid && c <= colEnd) {
                makeCnt(cntQuarter, 3);
                recursion(rowMid + 1, rowEnd, colMid + 1, colEnd);
            }
        }
    }

    static void makeCnt(int cntQuarter, int times){
        cntStart += cntQuarter * times;
        cntEnd = cntStart + cntQuarter;
    }
}

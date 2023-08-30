import java.util.Scanner;

public class Main_6137 {
    static char[] S;
    static String T = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        S = new char[N];
        for (int n = 0; n < N; n++)
            S[n] = sc.next().charAt(0);

        int start = 0;
        int end = N-1;
        recursiveFind(start, end);

        char[] charT = T.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < charT.length; n++){
            if (n != 0 && n%80 == 0)
                sb.append("\n");
            sb.append(charT[n]);
        }

        System.out.print(sb);
    }

    static void recursiveFind(int start, int end){
        if (start > end)
            return;
        char startChar = S[start];
        char endChar = S[end];

        if (start == end){
            T += startChar;
            return;
        }

        if (startChar < endChar){
            T += startChar;
            recursiveFind(++start, end);
        } else if (startChar > endChar){
            T += endChar;
            recursiveFind(start, --end);
        } else{
            T += startChar;

            int tmpStart = start+1;
            int tmpEnd = end-1;
            while(true){
                if (tmpStart >= tmpEnd){
                    start++;
                    break;
                }
                if (S[tmpStart] < S[tmpEnd]){
                    start++;
                    break;
                }else if (S[tmpStart] > S[tmpEnd]){
                    end--;
                    break;
                }else {
                    tmpStart++;
                    tmpEnd--;
                }
            }

            recursiveFind(start, end);
        }
    }
}

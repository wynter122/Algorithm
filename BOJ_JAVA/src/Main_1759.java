import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759 {
    static char[] letters;
    static StringBuilder sb = new StringBuilder();
    static String vowels = "aeiou";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        letters = new char[C];
        for (int i = 0; i < letters.length; i++)
            letters[i] = st.nextToken().charAt(0);

        Arrays.sort(letters);           // 문자 오름차순으로 정렬

        Recursion("", 0, L);
        System.out.println(sb);
    }

    static void Recursion(String text, int idx, int L){
        if (text.length() == L){
            int vowelCnt = 0;
            int consonantCnt = 0;

            char[] txtChar = text.toCharArray();
            for (char l : txtChar){
                if (vowels.contains(String.valueOf(l)))
                    vowelCnt++;
                else
                    consonantCnt++;
            }

            if (vowelCnt >= 1 && consonantCnt >= 2)
                sb.append(text).append("\n");
            return;
        }

        for (int i = idx; i < letters.length; i++) {
            char add = letters[i];
            Recursion(text + add, i + 1, L);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_2671 {
    static final String YES = "SUBMARINE";
    static final String NO = "NOISE";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String REGEXP_PATTERN_NUM = "^(10[0]+[1]+|01)+$";            // (100~1~|01)~


        String s = br.readLine();
        Boolean result = Pattern.matches(REGEXP_PATTERN_NUM, s);
        if (result)
            System.out.println(YES);
        else
            System.out.println(NO);

    }
}

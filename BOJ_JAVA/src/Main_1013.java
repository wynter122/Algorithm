import java.util.*;
import java.util.regex.Pattern;

public class Main_1013 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String PATTERN = "(100+1+|01)+";

        int T = sc.nextInt();
        for(int t = 0; t < T; t++){
            String str = sc.next();
            boolean result = Pattern.matches(PATTERN, str);

            if (result)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
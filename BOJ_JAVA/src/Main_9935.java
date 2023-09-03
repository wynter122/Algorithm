import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935 {
    static final String NO_STRING = "FRULA";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] target = br.readLine().toCharArray();
        String bomb = br.readLine();

        StringBuilder result = new StringBuilder();

        for (Character ch : target){
            result.append(ch);

            if (result.length() >= bomb.length()){
                boolean flag = true;
                for (int i = 0; i < bomb.length(); i++){
                    char a = result.charAt(result.length() - bomb.length() + i) ;
                    char b = bomb.charAt(i);

                    if (a != b){
                        flag = false;
                        break;
                    }
                }

                if (flag)
                    result.delete(result.length()-bomb.length(), result.length());
            }
        }


        if (result.toString().equals(""))
            System.out.println(NO_STRING);
        else
            System.out.println(result);
    }
}

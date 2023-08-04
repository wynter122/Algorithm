import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12919 {
    static int Flag = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine();
        String target = br.readLine();

        Recursion(original, target);
        System.out.println(Flag);

    }

    public static void Recursion(String original, String target){
        if (original.equals(target)){
            Flag = 1;
            return;
        }

        String tmpTarget = deleteA(target);
        if (tmpTarget != null)
            Recursion(original, tmpTarget);

        tmpTarget = reverseDeleteB(target);
        if (tmpTarget != null)
            Recursion(original, tmpTarget);
    }

    public static String deleteA (String str){
        if (str.toCharArray()[str.length()-1] != 'A')
            return null;

        String result = str.substring(0, str.length()-1);
        if (result.equals(""))
            return null;
        return result;
    }

    public static String reverseDeleteB (String str){
        if (str.toCharArray()[0] != 'B')
            return null;

        String result = "";
        for (int s = str.length()-1; s >= 0; s--)
            result += str.toCharArray()[s];
        result = result.substring(0, result.length()-1);
        if (result.equals(""))
            return null;
        return result;
    }
}

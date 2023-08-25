import java.util.Scanner;

public class Main_16953 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(DFS(A, B, 1));
    }

    static int DFS(int target, int curr, int cnt){          // curr 이 target 이 되면 멈추기
        if (curr == target){
            System.out.println(cnt);
            System.exit(0);
        }

        int result = -1;
        if (curr % 2 == 0)
            result = DFS(target, curr/2, cnt+1);

        char[] charArrCurr = String.valueOf(curr).toCharArray();
        if (charArrCurr[charArrCurr.length-1] == '1'){
            String tmp = String.valueOf(curr).substring(0, charArrCurr.length-1);
            if (!tmp.equals(""))
                result = DFS(target, Integer.parseInt(tmp), cnt+1);
            else
                return -1;
        }

        return result;
    }
}

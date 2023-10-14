import java.util.*;
import java.io.FileInputStream;

class Solution {
    static Map<Character, Integer> hexMap = new HashMap<>();
    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);

        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F'};
        int num = 10;
        for (int i = 0; i < alphabet.length; i++)
            hexMap.put(alphabet[i], num++);

        int T;
        T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int pwLen = N / 4;          // 한 변의 길이
            LinkedList<Character> characters = new LinkedList<>();
            char[] arr = sc.next().toCharArray();
            for (int n = 0; n < arr.length; n++)
                characters.add(arr[n]);

            // 비밀번호 담을 Set
            Set<Long> passwords = new HashSet<>();

            // 총 길이 -1만큼 회전
            for (int i = 0; i < N; i++){
                // pwlen 만큼 잘라서 set에 넣기
                for (int j = 0; j < N; j += pwLen){
                    String pw = "";
                    for (int k = 0; k < pwLen; k++)
                        pw += characters.get(k+j);
                    passwords.add(makeDecimal(pw));
                }

                char tmp = characters.remove(N-1);
                characters.add(0, tmp);
            }

            ArrayList<Long> pwList = new ArrayList<>(passwords);
            Collections.sort(pwList, Collections.reverseOrder());
            Long result = pwList.get(K-1);
            System.out.println("#" + test_case + " " + result);
        }
    }

    static Long makeDecimal(String origin){
        char[] arr = origin.toCharArray();

        Long result = (long) 0;

        int indices = 0;
        for (int i = origin.length()-1; i >= 0; i--){
            char curr = arr[i];
            int currInt = hexMap.containsKey(curr) ? hexMap.get(curr) : curr - '0';
            result += currInt * (int) Math.pow(16, indices++);
        }

        return result;
    }
}
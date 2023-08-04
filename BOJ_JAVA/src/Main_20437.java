import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // a~z 까지 hashmap 만들기
            HashMap<Character, Integer> counter = new HashMap<>();
            for (int i = 97; i < 123; i++)
                counter.put((char)i, 0);

            // str 순회하며 각 알파벳 개수 구하기
            char[] charStr = str.toCharArray();
            for (int i = 0; i < str.length(); i++)
                counter.put(charStr[i], counter.get(charStr[i]) + 1);

            // 정확히 K개의 문자를 포함하는 가장 짧은 연속 문자열 == 어떤 문자열로 시작하고 끝나면서 K개 포함하는 가장 짧은 문자열
            int shortestLength = -1;
            int longestLength = -1;

            for (int start = 0; start < charStr.length; start++){            // 각 문자를 K개 이상인 키를 대상으로 찾기
                char sLetter = charStr[start];
                if (counter.get(sLetter) < K)
                    continue;
                int tmpCnt = 1;
                int end = start;
                while(true){
                    if(tmpCnt == K) {
                        int tmpLen = end - start + 1;
                        if (shortestLength == -1 || shortestLength > tmpLen)
                            shortestLength = tmpLen;
                        if (longestLength == -1 || longestLength < tmpLen)
                            longestLength = tmpLen;
                        break;
                    }
                    end = str.indexOf(sLetter, end+1);
                    tmpCnt++;
                }
                counter.put(sLetter, counter.get(sLetter)-1);
            }

            if (shortestLength == -1 || longestLength == -1)
                System.out.println(-1);
            else
                System.out.println(shortestLength + " " + longestLength);

        }
    }
}

/*
1
therearebananas
2
 */
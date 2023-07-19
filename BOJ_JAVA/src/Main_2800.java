import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair{
    char element;
    int idx;
    public Pair(char element, int idx){
        this.element = element;
        this.idx = idx;
    }
}
public class Main_2800 {
    static Stack<Integer> comb = new Stack<>();
    static ArrayList<String> resultIdx = new ArrayList<>();
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] expression = br.readLine().toCharArray();

        Stack<Pair> bracket = new Stack<>();
        for (int i = 0; i < expression.length; i++){
            char ele = expression[i];
            if (ele == '(' || ele == ')')
                bracket.push(new Pair(ele, i));
        }

        Stack<Pair> tmp = new Stack<>();
        ArrayList<int[]> brktCombination = new ArrayList<>();

        while (!bracket.empty()){
            Pair curr = bracket.pop();
            if (tmp.empty()){
                tmp.push(curr);
                continue;
            }

            if (curr.element == '('){
                Pair currPair = tmp.pop();
                int[] pairIdx = {curr.idx, currPair.idx};
                brktCombination.add(pairIdx);
            }else
                tmp.push(curr);
        }

        int[] brktIdxs = new int[brktCombination.size()];
        for (int i = 0; i < brktCombination.size(); i++)
            brktIdxs[i] = i;

        for (int r = brktIdxs.length; r > 0; r--){
            Combination(brktIdxs, 0, 0, r);
        }

        HashSet<String> rmDuplication = new HashSet<>();
        for (String s : resultIdx){
            String[] numbers = s.split("\\[");
            numbers = numbers[1].split("]");
            numbers = numbers[0].split(", ");

            boolean[] visited = new boolean[expression.length];
            for (int n = 0; n < numbers.length; n++){
                int brktI = Integer.parseInt(numbers[n]);
                visited[brktCombination.get(brktI)[0]] = true;
                visited[brktCombination.get(brktI)[1]] = true;
            }

            String result = "";
            for (int i = 0; i < expression.length; i++){
                if (visited[i] == false)
                    result += expression[i];
            }
            rmDuplication.add(result);
        }

        String[] answer = new String[rmDuplication.size()];
        int c = 0;
        Iterator<String> iter = rmDuplication.iterator();
        while(iter.hasNext())
            answer[c++] = iter.next();

        Arrays.sort(answer);

        for (String s : answer) {
            if (s != null)
                System.out.println(s);
        }
    }

    static void Combination(int[] arr, int start, int level, int r){
        if (level == r){
            resultIdx.add(comb.toString());
            cnt++;
            return;
        }

        for (int i = start; i < arr.length; i++){
            comb.push(arr[i]);
            Combination(arr, i+1, level+1, r);
            comb.pop();
        }
    }
}

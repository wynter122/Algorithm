import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class Pair{
    char element;
    int idx;
    public Pair(char element, int idx){
        this.element = element;
        this.idx = idx;
    }
}
public class Main_2800 {
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

        for (int i = 0; i < brktCombination.size(); i++){

        }

        System.out.println();
    }
}

import java.util.ArrayList;
import java.util.Stack;

public class Combination {
    static Stack<Integer> answer = new Stack<>();
    public static void main(String[] args) {
        int[] arr = {0, 1, 2};

        for (int r = arr.length-1; r > 0; r--)
            Combination(arr, 0, 0, r);

    }

    public static void Combination(int[] arr, int index, int level, int r){
        if (level == r){
            System.out.println(answer.toString());
            return;
        }

        for (int i = index; i < arr.length; i++){
            answer.push(arr[i]);
            Combination(arr, i+1, level+1, r);
            answer.pop();
        }

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14888 {
    static int[] numbers;
    static int[] operators = new int[4];
    static final int PLUS = 0;
    static final int MINUS = 1;
    static final int PRODUCT = 2;
    static final int DIVIDE = 3;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        // 수열 받기
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            numbers[n] = Integer.parseInt(st.nextToken());

        // 연산자 개수 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());

        int[] operatorResult = new int[N-1];
        Arrays.fill(operatorResult, -1);

        setOperators(PLUS, 0, operatorResult, 0);

        System.out.println(max);
        System.out.println(min);
    }

    static void setOperators(int type, int typeCnt, int[] operatorResult, int idx){
        if (type > DIVIDE || typeCnt == operators[type]){
            if (type >= DIVIDE) {
                // 게산
                int i = 0;
                int ans = numbers[i];   // 초기값
                while(i < numbers.length-1){
                    int op = operatorResult[i++];
                    switch (op){
                        case PLUS : ans += numbers[i]; break;
                        case MINUS: ans -= numbers[i]; break;
                        case PRODUCT: ans *= numbers[i]; break;
                        case DIVIDE: ans /= numbers[i]; break;
                    }
                }
                max = Math.max(max, ans);
                min = Math.min(min, ans);
                return;
            } else
                setOperators(type+1, 0, operatorResult, 0);
        }

        for (int o = idx; o < operatorResult.length; o++){
            if (operatorResult[o] == -1){       // 빈자리 발견
                operatorResult[o] = type;       // 현재 연산자 삽입
                // 재귀
                if (typeCnt+1 >= operators[type])
                    setOperators(type+1, 0, operatorResult, 0);
                else
                    setOperators(type, typeCnt+1, operatorResult, o+1);
                operatorResult[o] = -1;         // 원복
            }
        }
    }

}

import java.util.Scanner;

public class Main_10775 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int G = sc.nextInt();
        int P = sc.nextInt();

        int[] gates = new int[G+1];                     // 인덱스 == 게이트 번호. 게이트번호 이전 몇번째부터 도킹 가능한지 기록
        int success = 0;
        for (int p = 1; p <= P; p++){
            int avGate = sc.nextInt();

            boolean flag = false;
            int g = avGate;
            while(true){
                if (gates[g] == 0)              // 도킹 가능하면 == 해당 인덱스부터 1까지 도킹된게 없다면
                    break;
                g = g - gates[g];
            }

            if (g > 0){
                flag = true;
                gates[g]++;                     // 도킹 기록 (+1) -> 해당 인덱스 번호 게이트 아래로 기록된 값만큼 빼야 도킹 가능한 게이트 나옴
                if (g != avGate)
                    gates[avGate]++;                    // 경로압축
            }

            if (!flag)
                break;
            success++;
        }
        System.out.println(success);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Gear{
    LinkedList<Byte> list = new LinkedList<>();

    public Gear(char[] arr){
        for (int i = 0; i < arr.length; i++)
            list.add((byte) (arr[i] - '0'));
    }

    public boolean checkLeft(Gear gear){
        if (this.list.get(6) != gear.list.get(2))
            return true;
        else
            return false;
    }

    public boolean checkRight(Gear gear){
        if (this.list.get(2) != gear.list.get(6))
            return true;
        else
            return false;
    }

    public void clockWise(){
        byte tmp = list.remove(7);
        list.add(0, tmp);
    }

    public void counterClockWise(){
        byte tmp = list.remove(0);
        list.add(tmp);
    }
}

class RotateInfo{
    int gearNum;
    int direction;

    public RotateInfo(int gearNum, int direction){
        this.gearNum = gearNum;
        this.direction = direction;
    }
}

public class Main_14891 {
    static Gear[] gears = new Gear[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 톱니바퀴 정보 받기
        for (int i = 0; i < 4; i++){
            char[] arr = br.readLine().toCharArray();
            gears[i] = new Gear(arr);
        }

        // 회전 정보 받기
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            // 발생하는 총 회전 담아둘 큐
            Queue<RotateInfo> allRotation = new LinkedList<>();

            // 연쇄 회전 확인할 큐
            Queue<RotateInfo> queue = new LinkedList<>();
            queue.add(new RotateInfo(gearNum, direction));

            // 회전 확인 여부 저장할 배열
            boolean[] checked = new boolean[4];

            while(!queue.isEmpty()){
                RotateInfo currRotation = queue.poll();
                allRotation.add(currRotation);

                int num = currRotation.gearNum;
                int reverseDir = currRotation.direction == 1 ? -1 : 1;

                checked[num] = true;

                // 0번 기어가 아니면 왼쪽 확인
                if (num != 0 && !checked[num-1]){
                    boolean leftCheck = gears[num].checkLeft(gears[num-1]);
                    if (leftCheck)
                        queue.add(new RotateInfo(num-1, reverseDir));
                }

                // 3번 기어가 아니면 오른쪽 확인
                if (num != 3 && !checked[num+1]){
                    boolean rightCheck = gears[num].checkRight(gears[num+1]);
                    if (rightCheck)
                        queue.add(new RotateInfo(num+1, reverseDir));
                }
            }

            // 모든 회전 수행
            rotate(allRotation);
        }

        // 계산
        int result = calculate();
        System.out.println(result);
    }

    static int calculate(){
        int result = 0;
        for (int i = 0; i < 4; i++){
            Gear gear = gears[i];
            if (gear.list.get(0) == 1)         // S극일떄
                result += (int) Math.pow(2, i);
        }
        return result;
    }

    static void rotate(Queue<RotateInfo> queue){
        while(!queue.isEmpty()){
            RotateInfo curr = queue.poll();
            if (curr.direction == 1)
                gears[curr.gearNum].clockWise();
            else
                gears[curr.gearNum].counterClockWise();
        }
    }
}

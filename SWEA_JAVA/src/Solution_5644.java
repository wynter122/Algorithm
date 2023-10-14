import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Person{
    int x, y;
    int sum = 0;
    public Person(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(int dir){
        switch (dir){
            case 1: y--; break;
            case 2: x++; break;
            case 3: y++; break;
            case 4: x--; break;
        }
    }
}

class Info{
    int moveA, moveB;
    public Info(int moveA, int moveB){
        this.moveA = moveA;
        this.moveB = moveB;
    }
}

class AP {
    int num;
    int x, y;
    int range;
    int performance;

    public AP(int num, int performance){
        this.num = num;
        this.performance = performance;
    }

    public AP(int num, int x, int y, int range, int performance){
        this(num, performance);
        this.x = x;
        this.y = y;
        this.range = range;
    }

    public boolean checkPerson(Person person){
        if (Math.abs(this.x - person.x) + Math.abs(this.y - person.y) <= range)
            return true;
        else
            return false;
    }
}

class APCombination implements Comparable<APCombination>{
    int firstAP, secondAP;
    int firstPerform, secondPerform;
    int sum;

    public APCombination (int firstAP, int secondAP, int firstPerform, int secondPerform){
        this.firstAP = firstAP;
        this.secondAP = secondAP;

        if (firstAP == secondAP){
            this.firstPerform = firstPerform / 2;
            this.secondPerform = firstPerform / 2;
        }else {
            this.firstPerform = firstPerform;
            this.secondPerform = secondPerform;
        }

        sum = this.firstPerform + this.secondPerform;
    }

    @Override
    public int compareTo(APCombination apc){
        if (this.sum > apc.sum)
            return -1;
        else if (this.sum < apc.sum)
            return 1;
        else
            return 0;
    }
}

public class Solution_5644 {
    // 사용자 만들기
    static Person personA = new Person(0, 0);
    static Person personB = new Person(9, 9);
    static AP[] aps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());           // 사용자 이동 횟수
            int A = Integer.parseInt(st.nextToken());           // 충전기 개수

            personA = new Person(0, 0);
            personB = new Person(9, 9);

            // 충전기 관리 공간 만들기
            aps = new AP[A];

            // 사용자 이동 정보 받기
            Queue<Info> moveInfo = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++)
                moveInfo.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken())));

            // 충전기 정보 받기
            for (int a = 0; a < A; a++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                int range = Integer.parseInt(st.nextToken());
                int performance = Integer.parseInt(st.nextToken());
                aps[a] = new AP(a, x, y, range, performance);
            }

            // 충전 먼저
            charge();

            while(!moveInfo.isEmpty()){
                Info info = moveInfo.poll();

                personA.move(info.moveA);
                personB.move(info.moveB);

                charge();
            }

            int result = personA.sum + personB.sum;
            System.out.println("#" + tc + " " + result);

            // 초기화

        }
    }

    static void charge(){
        // A 가능 충전기
        ArrayList<Integer> availableAP_A = new ArrayList<>();
        // B 가능 충전기
        ArrayList<Integer> availableAP_B = new ArrayList<>();

        for (AP ap : aps){
            if (ap.checkPerson(personA))
                availableAP_A.add(ap.num);
            if (ap.checkPerson(personB))
                availableAP_B.add(ap.num);
        }

        PriorityQueue<APCombination> pQueue = new PriorityQueue<>();

        if (!availableAP_A.isEmpty() && !availableAP_B.isEmpty()){
            // 경우의 수 확인
            for (int apA : availableAP_A){
                for (int apB : availableAP_B)
                    pQueue.add(new APCombination(apA, apB, aps[apA].performance, aps[apB].performance));
            }
        }else if (availableAP_A.isEmpty()){
            for (int apB : availableAP_B)
                pQueue.add(new APCombination(-1, apB, 0, aps[apB].performance));
        }else if (availableAP_B.isEmpty()){
            for (int apA : availableAP_A)
                pQueue.add(new APCombination(apA, -1, aps[apA].performance, 0));
        }

        if (!pQueue.isEmpty()){
            APCombination combination = pQueue.poll();
            pQueue.clear();

            personA.sum += combination.firstPerform;
            personB.sum += combination.secondPerform;
        }
    }
}

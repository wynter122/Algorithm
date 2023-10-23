import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Shark{
    int num;
    boolean isAlive = true;
    int r, c;
    int dir;
    int speed;
    int size;

    public Shark(int num, int r, int c, int speed, int dir, int size){
        this.num = num;
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }

    public void move(int[][][] map, Map<Integer, Shark> sharks){
        map[r][c][0] = 0;
        int dist = speed;
        if (dir == 1 || dir == 2){      // 상 하
            int N = map.length-1;
            while(true){
                if (dir == 1){
                    if (r - dist < 1){
                        dist -= r-1;
                        r = 1;
                        dir = 2;
                    }else {
                        r -= dist;
                        break;
                    }
                }else if (dir == 2){
                    if (r + dist > N){
                        dist -= N-r;
                        r = N;
                        dir = 1;
                    }else {
                        r += dist;
                        break;
                    }
                }
            }

        }else if (dir == 3 || dir == 4){    // 좌 우
            int M = map[0].length-1;
            while(true){
                if (dir == 3){
                    if (c + dist > M){
                        dist -= M-c;
                        c = M;
                        dir = 4;
                    }else {
                        c += dist;
                        break;
                    }
                }else if (dir == 4){
                    if (c - dist < 1){
                        dist -= c-1;
                        c = 1;
                        dir = 3;
                    }else {
                        c -= dist;
                        break;
                    }
                }
            }
        }

        if (map[r][c][1] != 0){
            Shark other = sharks.get(map[r][c][1]);
            if (this.size < other.size)
                this.isAlive = false;
            else{
                map[r][c][1] = num;
                other.isAlive = false;
            }
        }else
            map[r][c][1] = num;
    }
}

class Person{
    int c;
    int score;

    public Person(){
        this.c = 0;
        this.score = 0;
    }

    public void fish(int[][][] map, Map<Integer, Shark> sharks){
        c++;
        for (int r = 1; r < map.length; r++){
            if (map[r][c][0] != 0){
                int num = map[r][c][0];
                score += sharks.get(num).size;
                sharks.get(num).isAlive = false;
                map[r][c][0] = 0;
                break;
            }
        }
    }
}

public class Main_17143 {
    static int[][][] map;
    static Map<Integer, Shark> sharks = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1][2];
        for (int num = 1; num <= P; num++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());   // 행
            int c = Integer.parseInt(st.nextToken());   // 열
            int s = Integer.parseInt(st.nextToken());   // 속력
            int d = Integer.parseInt(st.nextToken());   // 이동방향
            int z = Integer.parseInt(st.nextToken());   // 크기
            sharks.put(num, new Shark(num, r, c, s, d, z));
            map[r][c][0] = num;
        }

        Person person = new Person();

        // 시뮬레이션
        while(person.c < M){
            // 한 칸 이동 후, 낚시
            person.fish(map, sharks);

            // 상어 이동
            for (int num : sharks.keySet()){
                Shark shark = sharks.get(num);
                if (shark.isAlive)
                    shark.move(map, sharks);
            }

            // 복제
            for (int i = 1; i < map.length; i++){
                for (int j = 1; j < map[0].length; j++) {
                    map[i][j][0] = map[i][j][1];
                    map[i][j][1] = 0;
                }
            }

        }

        System.out.println(person.score);
    }
}

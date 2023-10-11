import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Cell{
    int i, j;           // 좌표
    int t;              // 시간값
    boolean isAlive = true;            // 살았음, 죽었음
    int aliveTime;
    boolean isActivated = false;        // 활성화, 비활성화
    int activatedTime;
    public Cell(int i, int j, int t){
        this.i = i;
        this.j = j;
        this.t = t;

        aliveTime = 0;
        activatedTime = 0;
    }

    public void activate(){
        activatedTime++;
        if (activatedTime >= t)         // 생성 후 t 시간 이상 되었으면
            isActivated = true;         // 활성화

    }

    public void alive(Map<Integer, Map<Integer, Integer>> tmpPlate, Map<Integer, Map<Integer, Cell>> plate){
        aliveTime++;
        // 번식
        if (aliveTime == 1){
            int[] di = {-1, 0, 1, 0};
            int[] dj = {0, 1, 0, -1};

            for (int d = 0; d < 4; d++){
                int ni = i + di[d];
                int nj = j + dj[d];

                boolean cellExist = true;           // 줄기세포 존재여부 확인
                if (!plate.containsKey(ni) || !plate.get(ni).containsKey(nj))
                    cellExist = false;

                if (!cellExist){            // 줄기세포가 없으면
                    if (!tmpPlate.containsKey(ni))
                        tmpPlate.put(ni, new HashMap<>());

                    if (!tmpPlate.get(ni).containsKey(nj) || tmpPlate.get(ni).get(nj) < t)
                        tmpPlate.get(ni).put(nj, t);
                }
            }
        }

        if (aliveTime >= t)             // 활성화 후 t 시간 이상 되었으면
            isAlive = false;            // 죽이기
    }
}

public class Solution_5653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Map<Integer, Map<Integer, Cell>> plate = new HashMap<>();         // 행, 열을 해시맵으로 표현

            Queue<Cell> unActivatedQueue = new LinkedList<>();                // 비활성화 세포 담아둘 큐
            Queue<Cell> ActivatedQueue = new LinkedList<>();                  // 활성화 세포 담아둘 큐
            // 초기상태 입력
            for (int n = 0; n < N; n++){
                plate.put(n, new HashMap<>());
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++){
                    int t = Integer.parseInt(st.nextToken());
                    if (t > 0) {
                        Cell cell = new Cell(n, m, t);
                        plate.get(n).put(m, cell);
                        unActivatedQueue.add(cell);
                    }
                }
            }

            // 시뮬레이션
            int k = 0;
            while(k < K){
                k++;

                Queue<Cell> nextUnActivated = new LinkedList<>();
                Queue<Cell> nextActivated = new LinkedList<>();

                Map<Integer, Map<Integer, Integer>> tmpPlate = new HashMap<>();

                // 비활성화 -> 활성화
                while(!unActivatedQueue.isEmpty()){
                    Cell curr = unActivatedQueue.poll();
                    curr.activate();

                    if (curr.isActivated)
                        nextActivated.add(curr);
                    else
                        nextUnActivated.add(curr);
                }

                // 활성화 -> 번식 -> 시간 다 되면 죽이기
                while(!ActivatedQueue.isEmpty()){
                    Cell curr = ActivatedQueue.poll();
                    curr.alive(tmpPlate, plate);

                    if (curr.isAlive)
                        nextActivated.add(curr);
                }

                // 번식된 세포 반영
                for (int row : tmpPlate.keySet()){
                    for (int col : tmpPlate.get(row).keySet()) {
                        Cell newCell = new Cell(row, col, tmpPlate.get(row).get(col));
                        if (!plate.containsKey(row))
                            plate.put(row, new HashMap<>());
                        plate.get(row).put(col, newCell);
                        nextUnActivated.add(newCell);
                    }
                }

                unActivatedQueue = nextUnActivated;
                ActivatedQueue = nextActivated;
            }

            int result = unActivatedQueue.size() + ActivatedQueue.size();
            System.out.println("#" + tc + " " + result);
        }
    }
}

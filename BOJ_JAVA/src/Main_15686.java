import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

abstract class Node {
    int x, y;

    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}

class House extends Node{
    public House(int x, int y){
        super(x, y);
    }
}

class Store extends Node implements Comparable<Store> {
    int sum;
    ArrayList<Integer> dist = new ArrayList<>();
    public Store(int x, int y) {
        super(x, y);
        sum = 0;
    }

    public void getSum(){
        for (int d : dist){
            sum += d;
        }
    }

    public int compareTo(Store store){
        if (this.sum < store.sum)
            return -1;
        else if (this.sum > store.sum)
            return 1;
        else
            return 0;
    }
}

public class Main_15686 {
    static ArrayList<Store> stores = new ArrayList<>();          // 치킨집 좌표 저장
    static ArrayList<House> houses = new ArrayList<>();          // 집 좌표 저장
    static int resultSum = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int curr = Integer.parseInt(st.nextToken());
                if (curr == 1)
                    houses.add(new House(i, j));
                else if (curr == 2)
                    stores.add(new Store(i, j));
            }
        }

        // 치킨집 돌면서 집 순서대로 거리 파악
        for (Store store : stores)
            getDistance(store);

        int[] distances = new int[houses.size()];
        Arrays.fill(distances, 101);

        for (int start = 0; start < stores.size(); start++)
            Recursion(start, 0, M, distances);

        System.out.println(resultSum);
    }

    static void getDistance(Store store){
        for (House house : houses){
            int dist = Math.abs(store.x - house.x) + Math.abs(store.y - house.y);
            store.dist.add(dist);
        }
        store.getSum();
    }

    static void Recursion(int start, int m, int M, int[] distances){
        Store curr = stores.get(start++);             // 현재 가게
        m++;            // 선택한 가게 개수 증가

        int afterSum = 0;
        // 각 집에 대한 현재 가게의 거리 값중 최소를 취하기
        int[] newDist = Arrays.copyOf(distances, distances.length);
        for (int d = 0; d < distances.length; d++) {
            newDist[d] = Math.min(distances[d], curr.dist.get(d));
            afterSum += newDist[d];
        }

        resultSum = Math.min(afterSum, resultSum);          // 최소값 갱신
        if (m == M)
            return;


        for (int i = start; i <= stores.size() - (M-m); i++){
            Recursion(i, m, M, newDist);
        }
    }
}

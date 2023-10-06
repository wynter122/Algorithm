import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x, y, dir;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.dir = -1;
    }

    public Node(int x, int y, int dir) {
        this(x, y);
        this.dir = dir;
    }
}

class DragonCurve{
    int generation = 0;
    ArrayList<Node> nodes = new ArrayList<>();

    public DragonCurve(Node start, int d, int[][] matrix){
        // 시작점 삽입
        nodes.add(start);

        Node end = null;
        switch (d){
            case 0:
                end = new Node(start.x+1, start.y);
                start.dir = 2;
                break;
            case 1:
                end = new Node(start.x, start.y-1);
                start.dir = 3;
                break;
            case 2:
                end = new Node(start.x-1, start.y);
                start.dir = 0;
                break;
            case 3:
                end = new Node(start.x, start.y+1);
                start.dir = 1;
                break;
        }

        // 끝 점 삽입
        nodes.add(end);
    }

    public void rotate(){
        Node prev = nodes.get(nodes.size()-1);          // 마지막 점부터
        for (int i = nodes.size()-2; i >= 0; i--){      // 거꾸로 탐색
            Node curr = nodes.get(i);

            Node next = null;
            switch (curr.dir){
                case 0:
                    next = new Node(prev.x, prev.y+1);
                    prev.dir = 1;
                    break;
                case 1:
                    next = new Node(prev.x+1, prev.y);
                    prev.dir = 2;
                    break;
                case 2:
                    next = new Node(prev.x, prev.y-1);
                    prev.dir = 3;
                    break;
                case 3:
                    next = new Node(prev.x-1, prev.y);
                    prev.dir = 0;
                    break;
            }
            nodes.add(next);
            prev = next;
        }
    }

    public void draw(int[][] matrix){
        Node prev = nodes.get(nodes.size()-1);          // 가장 마지막 점

        for (int i = nodes.size()-2; i >= 0; i--){
            Node curr = nodes.get(i);                   // 현재 점
            matrix[prev.y][prev.x] = 1;
            switch (curr.dir){
                case 0:
                    matrix[prev.y][prev.x+1] = 1;
                    break;
                case 1:
                    matrix[prev.y-1][prev.x] = 1;
                    break;
                case 2:
                    matrix[prev.y][prev.x-1] = 1;
                    break;
                case 3:
                    matrix[prev.y+1][prev.x] = 1;
                    break;
            }
            prev = curr;
        }
    }
}

public class Main_15685 {
    static int[][] matrix = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 드래곤 커브 그리기
        for (int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());           // 시작 x 좌표
            int y = Integer.parseInt(st.nextToken());           // 시작 y 좌표
            int d = Integer.parseInt(st.nextToken());           // 시작 방향
            int g = Integer.parseInt(st.nextToken());           // 세대

            // 0세대 생성
            DragonCurve dragonCurve = new DragonCurve(new Node(x, y), d, matrix);

            // 목표 세대까지 만들기
            while(dragonCurve.generation < g){
                dragonCurve.rotate();
                dragonCurve.generation++;
            }

            // 그리기
            dragonCurve.draw(matrix);
        }

        // 정사각형 개수 구하기
        int result = checkSquare();
        System.out.println(result);
    }

    static int checkSquare(){
        int cnt = 0;
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                if (matrix[i][j] == 1 && matrix[i][j+1] == 1 && matrix[i+1][j] == 1 && matrix[i+1][j+1] == 1)
                    cnt++;
            }
        }
        return cnt;
    }
}

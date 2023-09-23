import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class CCTVNode extends Node{
    int type;
    public CCTVNode(int x, int y, int type){
        super(x, y);
        this.type = type;
    }
}

public class Main_15683 {
    static int[][] map;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        result = N * M;

        Stack<CCTVNode> cctvList = new Stack<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] != 0 && map[n][m] != 6)
                    cctvList.add(new CCTVNode(n, m, map[n][m]));
            }
        }

        boolean[][] visible = new boolean[N][M];
        cctvCheck(cctvList, visible);

        System.out.println(result);

    }

    static void cctvCheck(Stack<CCTVNode> stack, boolean[][] visible) {
        if (stack.isEmpty()) {           // 더이상 꺼낼 CCTV 없을 때
            int cnt = 0;
            for (int i = 0; i < visible.length; i++) {
                for (int j = 0; j < visible[i].length; j++) {
                    if (map[i][j] == 0 && visible[i][j] == false)
                        cnt++;
                }
            }
            result = Math.min(result, cnt);
            return;
        }

        CCTVNode currCCTV = stack.pop();

        // cctv type에 따라 CCTV 생성
        CCTV cctv = null;
        switch (currCCTV.type) {
            case 1:
                cctv = new CCTV1();
                break;
            case 2:
                cctv = new CCTV2();
                break;
            case 3:
                cctv = new CCTV3();
                break;
            case 4:
                cctv = new CCTV4();
                break;
            case 5:
                cctv = new CCTV5();
                break;
        }

        // CCTV가 바라보는 경우의 수 3차원 배열 : 1차원 == 경우의수, 2차원 == 하나의 경우에서 바라봐야하는 방향, 3차원 == 방향 좌
        for (int[][] cases : cctv.directions) {
            Queue<Node> checkedArea = new LinkedList<>();
            for (int[] way : cases)
                cctv.check(map, visible, new Node(currCCTV.x, currCCTV.y), way[0], way[1], checkedArea);              // dfs로 영역 색칠하기

            boolean[][] nextVisible = visible.clone();
            cctvCheck(stack, nextVisible);                       // 다음 cctv 경우의 수 탐색

            // visible 원상복구
            while (!checkedArea.isEmpty()) {
                Node checked = checkedArea.poll();
                visible[checked.x][checked.y] = false;
            }
        }
        // stack에 다시 넣기
        stack.add(currCCTV);
    }
}

class CCTV {
    int[][][] directions;

    public CCTV(int[][][] directions) {
        this.directions = directions;
    }

    public void check(int[][] map, boolean[][] visible, Node start, int dx, int dy, Queue<Node> queue) {
        Node curr = start;
        while (true) {
            if (!visible[curr.x][curr.y])       // 이번에 새로 감시되는 영역만 큐에 삽입
                queue.add(curr);
            visible[curr.x][curr.y] = true;

            if (map[curr.x][curr.y] == 6) break;  // 벽 만나면 탐색 중지

            int nx = curr.x + dx;
            int ny = curr.y + dy;
            if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)) {
                curr = new Node(nx, ny);
            }else
                break;
        }
    }
}

class CCTV1 extends CCTV {
    public CCTV1() {
        super(new int[][][]{{{-1, 0}}, {{1, 0}}, {{0, -1}}, {{0, 1}}});
    }
}

class CCTV2 extends CCTV {
    public CCTV2() {
        super(new int[][][]{{{-1, 0}, {1, 0}}, {{0, -1}, {0, 1}}});
    }
}

class CCTV3 extends CCTV {
    public CCTV3() {
        super(new int[][][]{{{-1, 0}, {0, 1}}, {{0, 1}, {1, 0}}, {{1, 0}, {0, -1}}, {{0, -1}, {-1, 0}}});
    }
}

class CCTV4 extends CCTV {
    public CCTV4() {
        super(new int[][][]{{{-1, 0}, {0, 1}, {1, 0}}, {{0, 1}, {1, 0}, {0, -1}}, {{1, 0}, {0, -1}, {-1, 0}}, {{0, -1}, {-1, 0}, {0, 1}}});
    }
}

class CCTV5 extends CCTV {
    public CCTV5() {
        super(new int[][][]{{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}});
    }
}
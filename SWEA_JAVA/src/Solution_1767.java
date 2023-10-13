import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Core extends Node{
    boolean connected;

    public Core(int x, int y, boolean connected){
        super(x, y);
        this.connected = connected;
    }

    public boolean isConnected(){
        return connected;
    }

    public void connect(){
        connected = true;
    }

    public void disConnect(){
        connected = false;
    }
}

class ConnectInfo implements Comparable<ConnectInfo>{
    int coreCnt;
    int wireCnt;

    public ConnectInfo(int coreCnt, int wireCnt){
        this.coreCnt = coreCnt;
        this.wireCnt = wireCnt;
    }

    @Override
    public int compareTo(ConnectInfo connectInfo){
        if (this.coreCnt > connectInfo.coreCnt)
            return -1;
        else if (this.coreCnt < connectInfo.coreCnt)
            return 1;
        else {
            if (this.wireCnt < connectInfo.wireCnt)
                return -1;
            else if (this.wireCnt > connectInfo.wireCnt)
                return 1;
            else
                return 0;
        }
    }
}

public class Solution_1767 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int alreadyConnected;
    static int maxCore;
    static int minWire;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= TC; tc++){
            int N = Integer.parseInt(br.readLine().trim());

            alreadyConnected = 0;
            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            // 보드 생성
            int[][] board = new int[N][N];

            // 셀 담아둘 배열
            ArrayList<Core> cellList = new ArrayList<>();
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i == 0 || i == N-1 || j == 0 || j == N-1)
                            alreadyConnected++;
                        else
                            cellList.add(new Core(i, j, false));
                    }
                }
            }

            // 연결 시작
            connect(cellList, 0, board);

            int result = minWire;
            System.out.println("#" + tc + " " + result);
        }
    }

    static void connect(ArrayList<Core> coreList, int curr, int[][] board){
        if (curr >= coreList.size()){
            // 연결된 코어 개수
            int coreCnt = alreadyConnected;
            for (Core core : coreList){
                if (core.isConnected())
                    coreCnt++;
            }

            // 전선 개수
            int wireCnt = 0;
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board.length; j++)
                    if (board[i][j] == 2)
                        wireCnt++;
            }

            if (maxCore < coreCnt){
                maxCore = coreCnt;
                minWire = wireCnt;
            }else if (maxCore == coreCnt)
                minWire = Math.min(minWire, wireCnt);

            return;
        }

        int[][] tmpBoard = new int[board.length][board.length];

        // 연결하지 않았을 때
        for (int n = 0; n < tmpBoard.length; n++)
            tmpBoard[n] = board[n].clone();
        connect(coreList, curr+1, board);

        for(int d = 0; d < 4; d++){
            // 보드 복사
            for (int n = 0; n < tmpBoard.length; n++)
                tmpBoard[n] = board[n].clone();

            // 방향에 맞게 전선 연결
            int x = coreList.get(curr).x;
            int y = coreList.get(curr).y;
            boolean flag = true;

            while (true) {
                x += dx[d];
                y += dy[d];

                if (x < 0 || x >= tmpBoard.length || y < 0 || y >= tmpBoard.length)
                    break;
                if (tmpBoard[x][y] != 0) {
                    flag = false;
                    break;
                }
                tmpBoard[x][y] = 2;           // 전선 연결
            }

            if (flag) {
                coreList.get(curr).connect();
                connect(coreList, curr + 1, tmpBoard);
                coreList.get(curr).disConnect();
            }
        }
    }
}

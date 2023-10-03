import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class PreferNode extends Node implements Comparable<PreferNode>{
    int score;
    int blank;

    public PreferNode(int x, int y, int score, int blank){
        super(x, y);
        this.score = score;
        this.blank = blank;
    }

    @Override
    public int compareTo(PreferNode preferNode){
        if (this.score > preferNode.score)
            return -1;
        else if (this.score < preferNode.score)
            return 1;
        else {
            if (this.blank > preferNode.blank)
                return -1;
            else if (this.blank < preferNode.blank)
                return 1;
            else{
                if (this.x < preferNode.x)
                    return -1;
                else if (this.x > preferNode.x)
                    return 1;
                else {
                    if (this.y < preferNode.y)
                        return -1;
                    else if (this.y > preferNode.y)
                        return 1;
                    else
                        return 0;
                }
            }
        }
    }
}

public class Main_21608 {
    static int[][] map;
    static boolean[][] preferences;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        preferences = new boolean[N*N+1][N*N+1];

        Queue<Integer> order = new LinkedList<>();

        // 친구 정보 받기
        for (int n = 0; n < N*N; n++){
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order.add(student);

            while(st.hasMoreTokens())
                preferences[student][Integer.parseInt(st.nextToken())] = true;
        }

        // 자리 정하기
        while(!order.isEmpty())
            setSeat(order.poll());

        // 만족도 계산
        int allScore = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++)
                allScore += getSatisfied(new Node(i, j));
        }

        System.out.println(allScore);
    }

    static int getSatisfied(Node curr){
        int result = 0;

        int student = map[curr.x][curr.y];
        for (int d = 0; d < 4; d++){
            int nx = curr.x + dx[d];
            int ny = curr.y + dy[d];
            if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                int friend = map[nx][ny];
                if (preferences[student][friend])
                    result++;
            }
        }
        if (result > 1){
            result = (int) Math.pow(10, result) / 10;
        }

        return result;
    }

    static void setSeat(int student){
        // 1. 비어있는 칸 중 가장 선호도가 높은 칸 조사
        // 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        // 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

        PriorityQueue<PreferNode> candidates = new PriorityQueue<>();

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map.length; j++){

                // 상하좌우 확인 -> 점수, 빈 칸 계산
                int score = 0;
                int blank = 0;
                if (map[i][j] == 0){
                    for (int d = 0; d < 4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if ((nx >= 0 && nx < map.length) && (ny >= 0 && ny < map[nx].length)){
                            int friend = map[nx][ny];
                            if (friend == 0)
                                blank++;
                            else if (preferences[student][friend])
                                score++;
                        }
                    }
                    candidates.add(new PreferNode(i, j, score, blank));
                }
            }
        }

        PreferNode selectedNode = candidates.poll();
        map[selectedNode.x][selectedNode.y] = student;
    }
}

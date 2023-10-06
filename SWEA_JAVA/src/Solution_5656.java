import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Matrix implements Cloneable{
    int h, w;
    Block[][] blocks;

    public Matrix(int h, int w){
        this.h = h;
        this.w = w;
        blocks = new Block[h][w];
    }

    public void moveBlocks(){
        for (int j = 0; j < w; j++){
            int i = h-2;        // 마지막에서 두번째 칸 부터 시작
            while(i >= 0){
                if (blocks[i][j].v > 0){        // 벽돌이 존재하면
                    int newI = i;
                    for (int k = i+1; k < h; k++){      // 그 아래칸탐색
                        if (blocks[k][j].v > 0)
                            break;
                        newI = k;
                    }

                    if (newI != i) {
                        blocks[newI][j] = blocks[i][j];
                        blocks[i][j] = new Block(0);
                    }
                }
                i--;
            }
        }
    }

    public int countBlocks(){
        int result = 0;
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                if (blocks[i][j].v > 0)
                    result++;
            }
        }
        return result;
    }

    @Override
    public Matrix clone(){
        Matrix clone = new Matrix(this.h, this.w);
        for (int i = 0; i < this.h; i++){
            for (int j = 0; j < this.w ; j++)
                clone.blocks[i][j] = new Block(this.blocks[i][j].v);
        }
        return clone;
    }
}

class Block implements Cloneable{
    int v;

    public Block(int v){
        this.v = v;
    }

    @Override
    public Block clone(){
        Block clone = new Block(this.v);
        return clone;
    }

    public void bomb(Matrix matrix, int x, int y){
        if (v == 0)
            return;

        int n = v;
        v = 0;
        // 상
        for (int i = x-1; i > x-n; i--){
            if (i < 0 || i >= matrix.h)
                break;
            matrix.blocks[i][y].bomb(matrix, i, y);
        }

        // 하
        for (int i = x+1; i < x+n; i++){
            if (i < 0 || i >= matrix.h)
                break;
            matrix.blocks[i][y].bomb(matrix, i, y);
        }

        // 좌
        for (int j = y-1; j > y-n; j--){
            if (j < 0 || j >= matrix.w)
                break;
            matrix.blocks[x][j].bomb(matrix, x, j);
        }

        // 우
        for (int j = y+1; j < y+n; j++){
            if (j < 0 || j >= matrix.w)
                break;
            matrix.blocks[x][j].bomb(matrix, x, j);
        }
    }
}

public class Solution {
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++){
            result = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());       // 구슬 개수
            int W = Integer.parseInt(st.nextToken());       // 열 길이
            int H = Integer.parseInt(st.nextToken());       // 행 길이

            // 벽돌 정보
            Matrix matrix = new Matrix(H, W);
            for (int h = 0; h < H; h++){
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++)
                    matrix.blocks[h][w] = new Block(Integer.parseInt(st.nextToken()));
            }

            // 구슬 떨어뜨리기
            drop(matrix, 0, N);

            // 결과 출력
            System.out.println("#" + tc + " " + result);
        }
    }

    static void drop(Matrix matrix, int cnt, int N){
        if (cnt == N){      // 구슬을 모두 떨어뜨렸을 때
            int tmpResult = matrix.countBlocks();
            result = Math.min(result, tmpResult);
            return;
        }

        // 각 열에 구슬 떨어뜨리기 시뮬레이션
        for (int n = 0; n < matrix.w; n++){
            // 배열 복사
            Matrix tmp = matrix.clone();

            int x = 0;
            int y = 0;
            // 벽돌이 나올 때 까지 내려가기
            for (int i = 0; i < tmp.h; i++){
                if (tmp.blocks[i][n].v != 0){          // 벽돌을 만나면
                    x = i;
                    y = n;
                    tmp.blocks[i][n].bomb(tmp, i, n);       // 폭발
                    break;
                }
            }

            // 모든 폭발이 끝나면 벽돌 아래로 이동
            tmp.moveBlocks();

            // 다음 구슬 떨어뜨리기
            drop(tmp, cnt+1, N);
        }
    }
}

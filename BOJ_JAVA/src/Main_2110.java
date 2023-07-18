/*
도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고,
가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.

입력
첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 집 개수
        int C = Integer.parseInt(st.nextToken());       // 설치 가능한 공유기 개수

        int[] houses = new int[N];
        for (int n = 0; n < N; n++)
            houses[n] = Integer.parseInt(br.readLine());
        Arrays.sort(houses);

        int left = 0;           // 첫번째 집 ~ 첫번째 집 간격
        int right = houses[houses.length-1] - houses[0];    // 마지막 집 ~ 첫번째 집 간격

        /* mid 조정 조건
        - mid에 따라 설치한 공유기의 개수 < C => 거리 좁혀야함 => right = mid-1
        - mid에 따라 설치한 공유기의 개수 >= C => 거리 넓혀야함 => left = mid+1
         */

        int result = 0;
        while(left <= right){

            int mid = (right+left) / 2;     // mid 값을 조정하면서 모든 거리 탐색

            int cnt = 1;    // 제일 처음에 위치한 집은 설치
            int latestHouse = houses[0];

            for (int h = 1; h < houses.length; h++){
                if (houses[h] - latestHouse >= mid){
                    cnt++;
                    latestHouse = houses[h];
                }
            }

            if (cnt < C){   // 적게 설치함 -> 간격 좁혀야함 -> right 줄이기
                right = mid-1;
            }else{          // cnt >= C -> 간격 넓혀야함 -> left 늘리기
                result = mid;   // 현재 mid 값 임시저장
                left = mid+1;
            }
        }

        System.out.println(result);
    }
}

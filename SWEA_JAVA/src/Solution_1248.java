/*
이진 트리에서 임의의 두 정점의 가장 가까운 공통 조상을 찾고, 그 정점을 루트로 하는 서브 트리의 크기를 알아내는 프로그램을 작성하라.

예를 들어, 위의 이진 트리에서 정점 8과 13의 공통 조상은 정점 3과 1 두 개가 있다.
이 중 8, 13에 가장 가까운 것은 정점 3이고, 정점 3을 루트로 하는 서브 트리의 크기(서브 트리에 포함된 정점의 수)는 8이다.

[입력]
가장 첫 번째 줄에 테스트케이스의 수가 주어진다.
각 케이스의 첫 번째 줄에는 정점의 개수 V(10 ≤ V ≤ 10000)와 간선의 개수 E, 공통 조상을 찾는 두 개의 정점 번호가 주어진다.
각 케이스의 두 번째 줄에는 E개 간선이 나열된다. 간선은 항상 “부모 자식” 순서로 표기된다.
위에서 예로 든 트리에서 정점 5와 8을 잇는 간선은 “5 8”로 표기된다.
정점의 번호는 1부터 V까지의 정수이며, 루트 정점은 항상 1번이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1248 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++){
            String s = br.readLine();
            st = new StringTokenizer(s);
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int tree[] = new int[V+1];
            int cnt[] = new int[V+1];

            for (int i = 1; i <= V; i++) {
                tree[i] = i;
                cnt[i] = 1;
            }

            s = br.readLine();
            st = new StringTokenizer(s);
            for (int e = 0; e < E; e++){
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                tree[child] = parent;
                while(true){
                    cnt[parent] += cnt[child];
                    if (tree[parent] != parent)
                        parent = tree[parent];
                    else
                        break;
                }
            }

            ArrayList<Integer> parentsOfA = findParents(tree, A);
            ArrayList<Integer> parentsOfB = findParents(tree, B);

            int result_parent = 1;

            loopout:
            for (int i = 0; i < parentsOfA.size(); i++){
                int a = parentsOfA.get(i);
                for (int j = 0; j < parentsOfB.size(); j++){
                    if (a == parentsOfB.get(j)){
                        result_parent = a;
                        break loopout;
                    }
                }
            }

            System.out.println("#" + t + " " + result_parent + " " + (cnt[result_parent]));
        }
    }

    public static ArrayList findParents(int[] tree, int element){
        ArrayList<Integer> parents = new ArrayList<>();

        parents.add(element);
        while(true){
            parents.add(tree[element]);
            element = tree[element];

            if (tree[element] == element)
                break;
        }
        return parents;
    }
}



/*
10
13 12
1 2 1 3 2 4 3 5 3 6 4 7 7 12 5 9 5 8 6 10 6 11 11 13
10 9
1 2 1 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10

50 49 24 31
31 7
2 17
27 32
14 30
1 21
45 26
44 27
39 11
26 3 48 6 3 44 2 49 42 13 48 8 23 33 11 10 8 42 41 31 17 4 8 22 25 23 21 41 28 25 13 16 46 2 31 35 42 19 32 18 27 50 45 15 28 20 46 28 44 40 40 5 15 48 9 34 1 46 17 29 35 36 21 45 14 37 23 14 6 39 11 9 19 24 26 47 16 38 40 12 47 43
 */
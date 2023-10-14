import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17471 {
    static int[] population;
    static ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 인구 수 받기
        population = new int[N+1];
        st = new StringTokenizer(br.readLine());
        graph.add(new ArrayList<>());
        for (int n = 1; n <= N; n++) {
            population[n] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<>());
        }

        // 인접정보 받기
        for (int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int i = 0; i < cnt; i++)
                graph.get(n).add(Integer.parseInt(st.nextToken()));
        }

        // 조합 구해서 가능성, 인원 수 파악하기
        for (int i = 1; i < N; i++)            // 한쪽 팀에 속할 인원 수
            Combination(1, new ArrayList<>(), i);

        int result = Integer.MAX_VALUE;

        // 각 조합 돌면서 인접한지 파악
        for (ArrayList<Integer> arr : combinations){
            int tmpResult = checkArea(arr);
            result = Math.min(result, tmpResult);
        }

        if (result == Integer.MAX_VALUE)
            result = -1;
        System.out.println(result);
    }
    static int checkArea(ArrayList<Integer> group){
        // group 1 dfs
        boolean firstResult = DFS(group);

        // group 2 dfs
        ArrayList<Integer> group2 = new ArrayList<>();
        for (int i = 1; i < population.length; i++)
            if (!group.contains(i)) group2.add(i);
        boolean secondResult = DFS(group2);


        if (firstResult && secondResult){
            int first = 0;
            for (int n : group)
                first += population[n];

            int second = 0;
            for (int n : group2)
                second += population[n];

            return  Math.abs(first-second);
        }
        return Integer.MAX_VALUE;
    }

    static boolean DFS(ArrayList<Integer> group){
        boolean[] visited = new boolean[population.length];

        Stack<Integer> stack = new Stack<>();
        stack.add(group.get(0));
        while(!stack.isEmpty()){
            Integer curr = stack.pop();
            if (visited[curr]) continue;
            visited[curr] = true;

            for (int next : graph.get(curr)){
                if (group.contains(next) && !visited[next])
                    stack.add(next);
            }
        }

        for (int node : group){
            if (!visited[node])
                return false;
        }
        return true;
    }

    static void Combination(int idx, ArrayList<Integer> group, int n){
        group.add(idx);
        if (group.size() == n){
            combinations.add((ArrayList<Integer>)group.clone());
            group.remove(group.size()-1);
            return;
        }

        for (int i = idx+1; i < population.length; i++)
            Combination(i, group, n);
        group.remove(group.size()-1);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 학생 수
        int M = Integer.parseInt(st.nextToken());       // 비교 횟수

        ArrayList<ArrayList<Integer>> compareResult = new ArrayList<>();
        HashMap<Integer, Integer> students = new HashMap<>();  // 비교 결과 더 작은 학생 카운트 기록
        for (int n = 0; n <= N; n++) {
            compareResult.add(n, new ArrayList<>());
            students.put(n, 0);
        }

        for (int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            compareResult.get(a).add(b);                                // a 학생 다음에 올 수 있는 학생 추가
            students.put(b, students.get(b)+1);                         // b에 진입점 개수 추가
        }

        // 진입점이 0인 학생부터 queue에 삽입하여 정렬 시작
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        for (int i = 1; i <= N; i++){
            if (students.get(i) == 0)
                queue.add(i);
        }

        while(!queue.isEmpty()) {
            int curr = queue.poll();            // 방문할 노드 꺼내기
            if (visited[curr])
                continue;

            visited[curr] = true;               // 방문처리
            result.add(curr);                   // 결과 배열에 삽입

            for (int next : compareResult.get(curr)) {
                if (!visited[next]) {
                    students.put(next, students.get(next) - 1);           // 현재 노드가 진입하는 다음 노드 카운트 감소
                    if (students.get(next) == 0)                            // 인접 노드가 진입점이 0이면 큐에 추가
                        queue.add(next);
                }
            }
        }
        for (int r : result)
            System.out.print(r + " ");
    }
}

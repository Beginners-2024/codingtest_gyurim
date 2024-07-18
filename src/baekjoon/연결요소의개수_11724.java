package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 1,000,
 * 0 ≤ M ≤ N×(N-1)/2
 * 시간 제한: 3초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM)
 * 메모리: 141480 KB, 시간: 684 ms
 **************************************************************************************/

public class 연결요소의개수_11724 {
    private static boolean[] visit;
    private static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수

        nodes = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodes[u].add(v);
            nodes[v].add(u);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) { // O(N): 정점 개수만큼 탐색
            if (!visit[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    private static void dfs(int start) {
        for (int i = 0; i < nodes[start].size(); i++) { // O(M): 간선 개수만큼 탐색
            int next = nodes[start].get(i);

            if (!visit[next]) {
                visit[next] = true;
                dfs(next);
            }
        }
    }
}

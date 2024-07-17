package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 1,000
 * 1 ≤ M ≤ 10,000
 * 시간 제한: 2초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM)
 * 메모리: 20220 KB, 시간: 256 ms
 **************************************************************************************/

public class dfs와bfs_1260 {
    private static List<Integer>[] nodes;
    private static boolean[] visit;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].add(b);
            nodes[b].add(a);
        }

        // 여러 개의 정점이 있는 경우, 정점 번호가 작은 것 먼저 방문을 위해 오름차순 정렬 수행
        for (int i = 1; i <= n; i++) {
            Collections.sort(nodes[i]);
        }

        visit = new boolean[n + 1];
        visit[v] = true;
        sb = new StringBuilder();
        dfs(v, sb);
        System.out.println(sb);

        visit = new boolean[n + 1];
        visit[v] = true;
        sb = new StringBuilder();
        bfs(v);
        System.out.println(sb);
    }

    private static void dfs(int v, StringBuilder sb) {
        sb.append(v + " ");

        for (int i = 0; i < nodes[v].size(); i++) { // O(NM): 각 정점에 연결된 모든 간선 탐지
            int next = nodes[v].get(i);

            if (!visit[next]) {
                visit[next] = true;
                dfs(next, sb);
            }
        }
    }

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur + " ");

            for (int i = 0; i < nodes[cur].size(); i++) { // O(NM): 각 정점에 연결된 모든 간선 탐지
                int next = nodes[cur].get(i);

                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM)
 * 메모리: 14252 KB, 시간: 124 ms
 **************************************************************************************/

public class 바이러스_2606 {
    private static List<Integer>[] nodes;
    private static boolean[] visit;
    private static int virus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

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

        int start = 1;
        visit[start] = true;
        dfs(start);

        System.out.println(virus);
    }

    private static void dfs(int v) {
        for (int i = 0; i < nodes[v].size(); i++) { // O(NM): 각 정점에 연결된 모든 간선 탐지
            int next = nodes[v].get(i);

            if (!visit[next]) {
                visit[next] = true;
                virus++;
                dfs(next);
            }
        }
    }
}

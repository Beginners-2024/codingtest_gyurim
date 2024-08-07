package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N * M)
 * 메모리: 16348 KB, 시간: 204 ms
 **************************************************************************************/

public class 구슬찾기_2617 {
    private static int n, m;

    private static boolean[] visit;


    private static List<Integer>[] heavier, lighter;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        heavier = new ArrayList[n + 1];
        lighter = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            heavier[i] = new ArrayList<>();
            lighter[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {                           // O(M)
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            heavier[l].add(h);
            lighter[h].add(l);
        }

        int marbleCount = 0;

        for (int i = 1; i <= n; i++) {                          // O(N)
            visit = new boolean[n + 1];
            int heavy = dfs(i, heavier);                        // O(M)

            if (heavy > n / 2) {
                marbleCount++;
                continue;
            }

            visit = new boolean[n + 1];
            int light = dfs(i, lighter);                         // O(M)

            if (light > n / 2) {
                marbleCount++;
            }
        }

        System.out.println(marbleCount);
    }

    private static int dfs(int current, List<Integer>[] graph) {    // O(M): 최악의 경우 모든 간선을 한 번씩 방문
        visit[current] = true;
        int count = 0;

        for (int next : graph[current]) {
            if (!visit[next]) {
                count += dfs(next, graph) + 1;
            }
        }

        return count;
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 100,000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 76820 KB, 시간: 1136 ms
 **************************************************************************************/

public class 트리의_부모찾기_11725 {
    private static List<Integer>[] relation;
    private static int[] parent;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 트리의 루트: 1
        relation = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            relation[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a].add(b);
            relation[b].add(a);
        }

        visit = new boolean[n + 1];
        parent = new int[n + 1];
        setParent(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    // 트리의 노드를 각 한 번씩 방문하고, 각 간선을 한 번씩 검사함. 트리의 간선 수는 n-1개이므로 DFS 시간복잡도는 O(n)
    private static void setParent(int num) {
        for (int i = 0; i < relation[num].size(); i++) {
            int next = relation[num].get(i);

            if (!visit[next]) {
                parent[next] = num;
                visit[next] = true;
                setParent(next);
            }
        }
    }
}

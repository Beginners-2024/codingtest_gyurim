package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N ≤ 500
 * M <= N(N-1)/2
 * 시간 제한: 1초
 *
 * DFS를 활용해, 이미 방문한 노드를 또 방문할 때 사이클이 발생한다고 판단
 * 하지만 이때, 또 방문한 노드가 노드의 부모인 경우에는 사이클이라고 판단하지 않음
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(T * (N + M)) (이때, T는 무한 루프(while문) 종료되기 전까지 호출된 횟수라 가정)
 * 메모리: 61884 KB, 시간: 448 ms
 **************************************************************************************/

public class 트리_4803 {
    private static boolean[] visit;
    private static List<Integer>[] edge;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testNum = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            visit = new boolean[n + 1];
            edge = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                edge[i] = new ArrayList<>();
            }

            while (m-- > 0) { // O(M)
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 방향이 없는 간선이라면 꼭 a, b 모두 간선 정보를 저장해주기
                edge[a].add(b);
                edge[b].add(a);
            }

            int treeCount = 0;

            for (int i = 1; i <= n; i++) {  // O(N + M)
                if (visit[i]) continue;
                visit[i] = true;

                if (!dfs(i, i)) {
                    treeCount++;
                }
            }
            printTree(treeCount, testNum++);
        }
        System.out.print(sb);
    }

    public static void printTree(int treeCount, int caseNum) {
        sb.append("Case ").append(caseNum).append(": ");
        if (treeCount == 1) {
            sb.append("There is one tree.");
        } else if (treeCount > 1) {
            sb.append("A forest of ").append(treeCount).append(" trees.");
        } else {
            sb.append("No trees.");
        }
        sb.append("\n");
    }

    private static boolean dfs(int node, int parent) {
        for (Integer next : edge[node]) {
            if (next == parent) continue; // 부모 노드와의 연결은 무시

            if (visit[next]) { // 부모가 아닌 이미 방문한 노드를 만나면 사이클
                return true;
            }

            visit[next] = true;

            if (dfs(next, node)) {
                return true;
            }
        }

        return false; // 사이클을 발견하지 못함
    }
}

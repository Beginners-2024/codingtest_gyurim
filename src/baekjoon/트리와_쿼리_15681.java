package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 105
 * 1 ≤ R ≤ N
 * 1 ≤ Q ≤ 105
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(V + E) = O(N + N-1) = O(N)
 * 메모리: 78160 KB, 시간: 672 ms
 **************************************************************************************/

public class 트리와_쿼리_15681 {
    private static List<Integer>[] edge;
    private static int[] parent; // 노드의 부모 번호를 저장하는 배열
    private static int[] childs; // 자신 포함 자식의 개수를 저장하는 배열

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        childs = new int[n + 1];
        Arrays.fill(childs, 1);
        edge = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) { // O(E)
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edge[u].add(v);
            edge[v].add(u);
        }

        parent[r] = -1; // 루트의 parent를 -1로 설정
        setParent(r);

        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(childs[u]).append("\n");
        }
        System.out.print(sb);
    }

    private static int setParent(int node) { // O(V + E)
        for (Integer next : edge[node]) {
            if (parent[next] != 0) continue;

            parent[next] = node;
            childs[node] += setParent(next); // 자식의 수만큼 배열에 누적 저장
        }

        return childs[node];
    }
}

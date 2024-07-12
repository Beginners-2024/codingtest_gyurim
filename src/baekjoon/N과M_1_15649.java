package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ M ≤ N ≤ 8
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N^M) = O(8^8) = 16777216
 * 메모리: 90312 KB, 시간: 2104 ms
 **************************************************************************************/

public class N과M_1_15649 {
    private static int n;
    private static int m;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        // 1 부터 N 까지 중복없이 M개를 고른 수열
        dfs(new boolean[n], new ArrayList<>());
    }

    private static void dfs(boolean[] visit, List<Integer> select) {
        if (select.size() == m) {
            for (Integer s : select) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                List<Integer> newSelect = new ArrayList<>(select);
                newSelect.add(list.get(i));
                dfs(visit, newSelect);
                visit[i] = false;
            }
        }
    }
}
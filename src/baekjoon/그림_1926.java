package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 <= n <= 500, 1 <= m <= 500
 * 시간 제한: 2초
 *
 * N = 500이므로, N^3 = 125,000,000 => 2억 연산 이내, 따라서 O(N^3) 시간복잡도 알고리즘 수행 가능
 * bfs의 시간복잡도: O(V + E)
 *      V = 모든 정점을 방문하는 시간: NM
 *      E = 모든 간선을 확인하는 시간: 4NM (= 4V)
 * => O(NM + 4NM) = O(NM)
 *
 * dfs가 적합하지 않은 이유?
 *      또한, 모든 셀이 1로 채워져있다면, 한 점에서 깊이 우선으로 모든 셀을 방문하기 때문에 최악의 경우 N^2 개의 셀을 방문함
 *      fs는 재귀(혹은 스택)로 구현되기 때문에 재귀 호출의 깊이가 너무 깊어지면 스택 오버플로우가 발생
 * => 실제로 n = 500, m = 500 일 때, 스택오버플로우 발생한 것을 확인
 *
 * 정리
 *      2차원 배열 구현 dfs, bfs 모두 시간복잡도 O(V+E) => 시간초과 발생 X
 *      하지만 dfs의 경우, 스택으로 구현되기 때문에 최악의 경우 스택오버플로우 발생
 *      따라서 위 문제처럼 연결된 영역의 크기를 찾는 문제에서는 bfs가 효율적
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM)
 * 메모리: 43544 KB, 시간: 448ms
 **************************************************************************************/

public class 그림_1926 {
    private static int n, m;
    private static int[][] board;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int maxArea = 0; // 그림이 없는 경우, 0 출력을 위해 Integer.MIN_VALUE 사용 X

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !visit[i][j]) {
                    maxArea = Math.max(maxArea, bfs(i, j));
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    private static class Index {
        int x, y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    private static int bfs(int x, int y) {
        int area = 0;
        Queue<Index> queue = new LinkedList<>();
        queue.add(new Index(x, y));
        area++;
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Index cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!oob(nx, ny) && board[nx][ny] == 1 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    area++;
                    queue.add(new Index(nx, ny));
                }
            }
        }

        return area;
    }

    private static boolean oob(int x, int y) {
        return x >= n || y >= m || x < 0 || y < 0;
    }
}

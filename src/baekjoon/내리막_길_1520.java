package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N, M ≤ 500
 *
 * DFS로만 푼다면 => 시간초과 발생!
 *      시간초과가 발생하는 이유?
 *          - 4 방향에 대해서, 모든 경로를 다 탐색해야하기 때문에 4 X ... X 4 = 4^(MN)
 *          - 따라서, 시간초과 발생함
 *
 * 메모이제이션 + DFS 결합해 푼다면 => 시간초과 해결
 *      시간초과가 해결되는 이유?
 *          - 각 칸을 1번만 계산하기 떄문에, O(M * N) 시간이 걸리게 됨
 *          - 메모이제이션을 통해 이미 계산한 결과를 저장하고 재사용하기에 중복 연산을 피할 수 있음
 *
 *
 * [회고]
 * Q. 나는 어떤 기준으로 문제를 풀 때, 메모이제이션을 넣어야하는지 혹은 dfs만 해도 되는지 판단내리면 될까
 * A.
 *      * 메모이제이션 사용하면 좋을 문제
 *          1. 입력 크기가 크고 제한 시간이 짧다면 메모이제이션 고려
 *              - 예) N이 500 이상이고 제한 시간이 1초 내외인 경우
 *          2. 중복 부분 문제
 *              - 같은 상태나 계산이 여러 번 반복되는 경우 메모이제이션이 효과적
 *              - 예) 피보나치 수열, 최단 경로 문제
 *          3. 최적 부분 구조
 *              - 큰 문제의 해답이 작은 부분 문제들의 해답으로 구성될 때 유용
 *          4. 상태 공간의 크기
 *              - 가능한 상태의 수가 많지만, 실제로 도달 가능한 상태는 상대적으로 적을 때 효과적
 *          5. 재귀적 구조
 *              - 문제가 자연스럽게 재귀적으로 표현될 수 있는 경우 메모이제이션 고려
 *
 *      * DFS만으로 충분한 경우
 *          1. 문제의 크기가 작은 경우
 *              - 예) N이 20 이하인 경우 (예시임. 항상 20 이하의 수가 dfs 가능하다는 뜻 절대 ㄴㄴ)
 *          2. 모든 경우를 탐색해야 하는 경우
 *              - 예) 순열, 조합 문제
 *          3. 상태 공간이 트리 형태이고 중복이 없는 경우
 *          4. 메모리 제한이 매우 엄격한 경우
 *          5. 가지치기 만으로 충분히 최적화가 가능한 경우
 *
 * 문제가 요구하는 것이 '모든 경우'인지 '최적해'인지 구분해보면 좀 더 명확하게 어떻게 풀어야할지 드러날듯
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(M * N)
 * 메모리: 35484 KB, 시간: 340 ms
 **************************************************************************************/

public class 내리막_길_1520 {
    private static int[][] map;

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 초기화: -1은 아직 방문하지 않은 상태
            }
        }

        System.out.print(dfs(0, 0));
    }

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] dp;

    private static int dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (oob(nx, ny)) continue;
            if (map[nx][ny] >= map[x][y]) continue;

            dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= m || y >= n;
    }
}
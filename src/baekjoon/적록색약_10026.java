package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(4 * NN)
 * 메모리: 18656 KB, 시간: 172 ms
 **************************************************************************************/

public class 적록색약_10026 {
    private static int n;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean[][] visit;
    private static int[][] paint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1][n + 1];
        paint = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            String p = br.readLine();

            for (int j = 0; j < n; j++) {
                paint[i][j] = p.charAt(j);
            }
        }

        // 적록 색약 아닌 사람
        int count = getCount();

        // 적록 색약 사람
        visit = new boolean[n + 1][n + 1]; // visit 배열 초기화
        int rgCount = getRGCount();

        System.out.println(count + " " + rgCount);
    }

    // 적록 색약 아닌 사람 - 구역 개수 리턴
    private static int getCount() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Red 개수
                if (!visit[i][j] && paint[i][j] == 'R') {
                    visit[i][j] = true;
                    List<Character> color = new ArrayList<>();
                    color.add('R');
                    dfs(color, i, j);
                    count++;
                }

                // Green 개수
                if (!visit[i][j] && paint[i][j] == 'G') {
                    visit[i][j] = true;
                    List<Character> color = new ArrayList<>();
                    color.add('G');
                    dfs(color, i, j);
                    count++;
                }

                // Blue 개수
                if (!visit[i][j] && paint[i][j] == 'B') {
                    visit[i][j] = true;
                    List<Character> color = new ArrayList<>();
                    color.add('B');
                    dfs(color, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // 적록 색약인 사람 - 구역 개수 리턴
    private static int getRGCount() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Red + Green 개수
                if (!visit[i][j] && (paint[i][j] == 'R' || paint[i][j] == 'G')) {
                    visit[i][j] = true;
                    List<Character> color = new ArrayList<>();
                    color.add('R');
                    color.add('G');
                    dfs(color, i, j);
                    count++;
                }


                // Blue 개수
                if (!visit[i][j] && paint[i][j] == 'B') {
                    visit[i][j] = true;
                    List<Character> color = new ArrayList<>();
                    color.add('B');
                    dfs(color, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    // 구역 탐색 - dfs
    private static void dfs(List<Character> color, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (oob(nx, ny)) continue;
            if (visit[nx][ny]) continue;

            boolean isSame = false;
            for (Character c : color) {
                if (c == paint[nx][ny]) {
                    isSame = true;
                    break;
                }
            }

            if (isSame) {
                visit[nx][ny] = true;
                dfs(color, nx, ny);
            }
        }
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}

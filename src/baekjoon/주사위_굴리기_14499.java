package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 시간복잡도: O(k)
 * 메모리: 14464 KB, 시간: 140 ms
 **************************************************************************************/

public class 주사위_굴리기_14499 {
    private static int n, m;
    private static int x, y;
    private static int[][] map;

    private static int[] dx = {0, 0, -1, 1}; // (0, 1), (0, -1), (-1, 0), (1, 0)
    private static int[] dy = {1, -1, 0, 0};

    private static int[] dice = {0, 0, 0, 0, 0, 0};
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) { // O(k)
            int cmd = Integer.parseInt(st.nextToken());
            runCmd(cmd - 1);
        }

        System.out.print(sb);
    }

    private static void runCmd(int cmd) {
        int nx = x + dx[cmd];
        int ny = y + dy[cmd];

        if (oob(nx, ny)) return;

        // 주사위 회전
        rollDice(cmd);

        if (map[nx][ny] == 0) { // 주사위 바닥면에 쓰여 있는 수가 칸에 복사
            map[nx][ny] = dice[0];
        } else { // 칸에 쓰여있는 수가 주사위 바닥으로 복사되며, 칸에 쓰여있는 수는 0이 됨
            dice[0] = map[nx][ny];
            map[nx][ny] = 0;
        }

        x = nx;
        y = ny;

        sb.append(dice[5]).append("\n");
    }

    private static void rollDice(int cmd) {
        if (cmd == 0) { // 동
            int tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
        } else if (cmd == 1) { // 서
            int tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = tmp;
        } else if (cmd == 2) { // 북
            int tmp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = tmp;
        } else if (cmd == 3) { // 남
            int tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
        }
    }

    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}

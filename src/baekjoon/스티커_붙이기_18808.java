package baekjoon;

import java.io.*;
import java.util.*;

public class 스티커_붙이기_18808 {
    private static int n, m, k;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 스티커 개수

        board = new int[n][m];

        for (int sti = 0; sti < k; sti++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 스티커 붙일 수 있는지 체크
            for (int dir = 0; dir < 4; dir++) { // 90도 방향 회전
                // rotate();
                int _r = sticker.length;
                int _c = sticker[0].length;

                sticker = rotate(dir, sticker, _r, _c);

                // 붙일 수 있는지 체크
                if (canAttached(sticker)) {
                    break;
                }
            }
        }

        System.out.println(countSticker());
    }

    private static int countSticker() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) count++;
            }
        }

        return count;
    }

    private static boolean canAttached(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        // n, m 범위에 들어오는지 체크
        if (r > n) return false;
        if (c > m) return false;

        boolean canAttach = false;

        for (int i = 0; i + r <= n; i++) {
            for (int j = 0; j + c <= m; j++) {
                // i, j는 시작 지점
                if (!isDuplicated(i, j, sticker)) {
                    attached(i, j, sticker);
                    return true;
                }
            }
        }

        return canAttach;
    }

    private static void attached(int x, int y, int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) {
                    board[x + i][y + j] = 1;
                }
            }
        }
    }

    private static boolean isDuplicated(int sx, int sy, int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) {
                    if (board[sx + i][sy + j] == 1) return true; // 중복
                }
            }
        }
        return false;
    }

    private static int[][] rotate(int dir, int[][] sticker, int r, int c) {
        if (dir == 0) return sticker;

        int[][] rotated = new int[c][r];

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                rotated[i][j] = sticker[r - j - 1][i];
            }
        }

        return rotated;
    }
}
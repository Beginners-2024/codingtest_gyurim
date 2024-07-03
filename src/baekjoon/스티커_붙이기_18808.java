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
                int _r = sticker.length;
                int _c = sticker[0].length;

                sticker = rotate(dir, sticker, _r, _c);

                // 붙일 수 있는지 체크
                if (canAttached(sticker)) {
                    break; // 가장 최상단 + 가장 좌측 위치를 최우선으로 선택
                }
            }
        }
        System.out.println(countSticker());
    }

    private static int countSticker() { // 스티커가 붙은 칸의 수 리턴
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
                if (!isDuplicated(i, j, sticker)) { // 붙이고자 하는 자리에 다른 스티커 없으므로 해당 스티커를 붙일 수 있는 상태
                    attached(i, j, sticker);
                    return true;
                }
            }
        }
        return canAttach;
    }

    private static void attached(int x, int y, int[][] sticker) { // 보드에 스티커 부착
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
                    if (board[sx + i][sy + j] == 1) return true; // 이미 다른 스티커가 붙어있는 상태이므로 true 리턴
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
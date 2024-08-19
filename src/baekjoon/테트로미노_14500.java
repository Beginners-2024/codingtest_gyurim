package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 *------------------------------------------------------------------------------------
 * 메모리: 30888 KB, 시간: 456 ms
 **************************************************************************************/

public class 테트로미노_14500 {
    private static int[][] map;
    private static int n, m;

    private static int[][] type1 = {{1, 1, 1, 1}};
    private static int[][] type2 = {{1, 1}, {1, 1}};
    private static int[][] type3 = {{1, 0}, {1, 0}, {1, 1}};
    private static int[][] type4 = {{1, 0}, {1, 1}, {0, 1}};
    private static int[][] type5 = {{1, 1, 1}, {0, 1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;

        for (int i = 0; i < 2; i++) {
            max = Math.max(setTetromino(type1), max);
            type1 = rotate(type1);
        }

        max = Math.max(setTetromino(type2), max);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                max = Math.max(setTetromino(type3), max);
                type3 = rotate(type3);
            }
            // 대칭
            type3 = mirror(type3);
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                max = Math.max(setTetromino(type4), max);
                type4 = rotate(type4);
            }
            type4 = mirror(type4);
        }

        for (int i = 0; i < 4; i++) {
            max = Math.max(setTetromino(type5), max);
            type5 = rotate(type5);
        }

        System.out.println(max);
    }

    private static int setTetromino(int[][] type) {
        // 시작점(x, y)을 세팅
        int maxCount = 0;
        int r = type.length;
        int c = type[0].length;

        for (int x = 0; x < n - r + 1; x++) {
            for (int y = 0; y < m - c + 1; y++) {
                maxCount = Math.max(maxCount, getCount(x, y, type));
            }
        }

        return maxCount;
    }

    private static int getCount(int x, int y, int[][] type) {
        int count = 0;
        int r = type.length;
        int c = type[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (type[i][j] == 0) continue; // 0이면 점수 합 X
                count += map[x + i][y + j];
            }
        }
        return count;
    }

    // 회전
    private static int[][] rotate(int[][] type) {
        int r = type.length;
        int c = type[0].length;

        int[][] rotated = new int[c][r];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                rotated[i][j] = type[r - j - 1][i];
            }
        }

        return rotated;
    }

    // 반전
    private static int[][] mirror(int[][] type) {
        int r = type.length;
        int c = type[0].length;

        int[][] rotated = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                rotated[i][j] = type[i][c - j - 1];
            }
        }
        return rotated;
    }
}

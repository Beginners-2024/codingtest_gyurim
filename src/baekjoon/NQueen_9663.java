package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N < 15
 * 시간 제한: 10초
 *
 * isValid()가 없었다면, 모든 인덱스에 대해서 재귀를 수행할 것이기 떄문에 O(N^N) 시간복잡도가 걸림.
 * 하지만 isValid()를 통해, 유망한 퀸의 위치만 확인하므로 O(N*N!)으로 줄일 수 있다.
 *      - 재귀 호출 수: N!
 *      - 각 상태의 유효성 검사 시간: O(N)
 * -> 따라서, O(N*N!)
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N*N!)
 * 메모리: 14476 KB, 시간: 4832 ms
 **************************************************************************************/

public class NQueen_9663 {
    private static int n;
    private static int count;

    public static void main(String[] args) throws IOException {
        // N x N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓은 문제

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 3 * 3
        n = Integer.parseInt(st.nextToken());

        solve();
        System.out.println(count);
    }

    private static void solve() {
        int[] queens = new int[n]; // queens[i] = j: i번째 행의 퀸은 j열에 위치
        placeQueen(queens, 0);
    }

    private static void placeQueen(int[] queens, int row) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(queens, row, col)) { // (row, col) 자리에 퀸을 놓을 수 있는지 판별
                queens[row] = col;
                placeQueen(queens, row + 1);
                queens[row] = 0; // backtracking
            }
        }
    }

    private static boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col ||                 // 같은 열에 존재
                    queens[i] - i == col - row ||   // 북서~동남 방향 대각선
                    queens[i] + i == col + row      // 북동~남서 방향 대각선
            ) {
                return false;
            }
        }
        return true;
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 정답이 3보다 큰 값이라면 -1 출력, 불가능한 경우에도 -1 출력 => 0, 1, 2, 3 개의 가로선 조합만 생각하면 되므로 그리디하게 풀어도 ㄱㅊ을듯
 *
 * [첫번째 풀이]
 * 메모리: 20488 KB, 시간: 4992 ms
 * 주요 구현 방식
 *      1. 가로선이 담길 2차원 배열을 h * n-1 로 선언했음
 *          - 이렇게 구현하니 단점은 oob 관리를 하기 어렵다는 것
 *          - 따라서 배열을 원래 사이즈보다 크게 해주되, 경계선 부분은 false(or 0)로 초기화시켜 이동할 수 없게하면 됨
 *      2. 백트래킹을 이용해서 풀었는데, 빠른 종료를 적용하지 못했음 (어캐하는지 모름..)
 *          - 아예 프로그램 자체를 종료해주는 형태로 구현하면 좋겠다는 생각
 *
 * [두번쨰 풀이]
 * 메모리: 18876 KB, 440 ms
 * 주요 구현 방식
 *      1. 백트래킹 + 빠른 종료 + 조합할 가로선의 x 인덱스를 넘겨줘서 x 이상의 가로선만 탐색 => 1056 ms 로 줄임
 *      2. 배열 인덱스 크기 변경 => 736 ms 로 줄임
 *      3. 배열을 전역으로 사용하지 않고, 인자로 넘겨주는 형태로 변경 => 416 ms 로 줄임
 *
 * [회고]
 * - 인자로 넘겨주는 형태로 구현하면 메모리 이득만 있을 거라 생각했는데 시간 이득도 있구나
 * - 배열을 너무 핏하게 구현해야한다는 강박을 버리자
 **************************************************************************************/

public class 사다리_조작_15684 {
    private static boolean isManipulated;
    private static int n, h, m;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[][] line = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a 가로선, b 세로선 (인덱스 0부터 시작)
            line[a][b] = 1;
        }

        int operate = -1;

        for (int i = 0; i <= 3; i++) {
            isManipulated = false;

            // backtracking
            backTracking(i, 0, 1, line);

            if (isManipulated) {
                operate = i;
                break;
            }
        }
        System.out.println(operate);
    }

    private static void backTracking(int total, int count, int start, int[][] line) {
        if (isManipulated) return;

        if (count == total) {
            if (checkProgram(line)) {
                isManipulated = true;
            }
            return;
        }

        for (int i = start; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (line[i][j] == 1) continue;
                if (line[i][j - 1] == 1) continue;
                if (line[i][j + 1] == 1) continue;

                line[i][j] = 1;
                backTracking(total, count + 1, i, line);
                line[i][j] = 0;
            }
        }
    }

    private static boolean checkProgram(int[][] line) {
        for (int num = 1; num <= n; num++) {
            int x = 1;
            int y = num;

            while (x <= h) {
                if (line[x][y - 1] == 1) {
                    y--;
                } else if (line[x][y] == 1) {
                    y++;
                }
                x++;
            }
            if (y != num) {
                return false;
            }
        }
        return true;
    }
}

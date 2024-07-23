package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ K ≤ 100
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(K * 4 * 4)
 * 메모리: 14260 KB, 시간: 132 ms
 **************************************************************************************/

public class 톱니바퀴_14891 {
    private static List<Integer>[] chain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chain = new ArrayList[5];

        for (int i = 1; i <= 4; i++) {
            String ch = br.readLine();
            chain[i] = new ArrayList<>();

            for (int j = 0; j < 8; j++) {
                chain[i].add(ch.charAt(j) - '0');
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (k-- > 0) { // O(k * 4 * 4)
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            runChain(num, dir); // O(4 * 4)
        }
        System.out.println(getPoint());
    }

    private static int getPoint() {
        int point = 0;

        int S = 1;

        if (chain[1].get(0) == S) point += 1;
        if (chain[2].get(0) == S) point += 2;
        if (chain[3].get(0) == S) point += 4;
        if (chain[4].get(0) == S) point += 8;

        return point;
    }

    private static void runChain(int num, int dir) { // O(4 * 4)
        // 왼쪽에 있는 톱니바퀴
        int cur = num;
        int[] dirs = new int[5]; // 각 체인의 회전 방향을 저장
        dirs[cur] = dir;

        for (int i = num - 1; i >= 1; i--) {
            if (chain[cur].get(6).equals(chain[i].get(2))) {
                // 맞닿은 극이 다르다면 회전 종료
                break;
            } else {
                dir = -dir;
                dirs[i] = dir;
                cur = i;
            }
        }

        // 변수 리셋
        dir = dirs[num];
        cur = num;

        // 오른쪽에 있는 톱니바퀴
        for (int i = num + 1; i <= 4; i++) {
            if (chain[cur].get(2).equals(chain[i].get(6))) {
                // 맞닿은 극이 다르다면 회전 종료
                break;
            } else {
                dir = -dir;
                dirs[i] = dir;
                cur = i;
            }
        }

        rotateChain(dirs); // O(4)
    }

    private static void rotateChain(int[] dirs) {
        for (int i = 1; i <= 4; i++) {
            if (dirs[i] == -1) { // 반시계 방향 회전
                int first = chain[i].remove(0);
                chain[i].add(first);
            } else if (dirs[i] == 1) { // 시계 방향 회전
                int last = chain[i].remove(7);
                chain[i].add(0, last);
            }
        }
    }
}

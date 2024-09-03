package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 50
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(8! * N)
 * 메모리: 299056 KB, 시간: 776 ms
 **************************************************************************************/

public class 야구_17281 {
    private static int n;
    private static int[][] games;
    private static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        games = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                games[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        setOrder(0, new ArrayList<>(), new boolean[9]);
        System.out.println(maxScore);
    }

    private static void setOrder(int count, List<Integer> ord, boolean[] visit) {
        if (count == 8) {
            ord.add(3, 0); // 4번 자리에 1번 타자
            maxScore = Math.max(maxScore, getGrade(ord)); // O(8!)번 반복 (8!개의 순서가 나옴)
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                List<Integer> newOrd = new ArrayList(ord);
                newOrd.add(i);
                setOrder(count + 1, newOrd, visit);
                visit[i] = false;
            }
        }
    }

    private static int getGrade(List<Integer> order) {
        // 최대 점수 탐색
        int cur = 0; //0번째 순서의 선수가 올라감
        int score = 0;

        for (int inning = 0; inning < n; inning++) { // O(N)
            int out = 0;
            int[] bases = new int[3];

            while (out < 3) { // 무한 일수도 ...
                int player = order.get(cur);
                int hit = games[inning][player];

                if (hit == 0) out++;
                else if (hit == 4) {
                    score += bases[0] + bases[1] + bases[2] + 1;
                    bases = new int[3];
                } else {
                    // 해당 부분 Queue 사용해서 관리했더니 시간초과 났음 .. (큐에 넣고 큐 사이즈가 4개 이상이면 선수 뺀 뒤, 남은 선수들만 관리하는 형태)
                    for (int i = 2; i >= 0; i--) {
                        if (bases[i] == 1) {
                            if (i + hit >= 3) { // 홈 인
                                score++;
                            } else {
                                bases[i + hit] = 1; // 잔루
                            }
                            bases[i] = 0;// 기존 위치 비우기
                        }
                    }
                    bases[hit - 1] = 1; // 타자 잔루
                }
                cur = (cur + 1) % 9;
            }
        }

        return score;
    }
}

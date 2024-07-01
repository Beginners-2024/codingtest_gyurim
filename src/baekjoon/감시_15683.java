package baekjoon;

import java.io.*;
import java.util.*;

public class 감시_15683 {
    private static int n, m;
    private static int[][] board;
    private static List<List<Integer>>[] cctv; // CCTV 종류 별 감시할 수 있는 방향을 담은 배열
    private static List<CCTV> boardCCTV; // 보드 위에 존재하는 CCTV
    private static int minArea = Integer.MAX_VALUE;

    private static class CCTV {
        int x, y;
        int type; // 종류 (1~5번)

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        boardCCTV = new ArrayList<>();

        initCCTV();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    boardCCTV.add(new CCTV(i, j, board[i][j]));
                }
            }
        }
        setDir(0, new int[boardCCTV.size()]);
        System.out.println(minArea);
    }

    private static void setDir(int k, int[] cctvDir) {
        if (k == boardCCTV.size()) {
            minArea = Math.min(checkArea(cctvDir), minArea);
            return;
        }

        int cur = boardCCTV.get(k).type;

        for (int i = 0; i < cctv[cur].size(); i++) {
            cctvDir[k] = i; // 보드 위에 있는 CCTV의 방향을 지정
            setDir(k + 1, cctvDir);
        }
    }

    private static int checkArea(int[] cctvDir) {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < boardCCTV.size(); i++) {
            int type = boardCCTV.get(i).type;
            int x = boardCCTV.get(i).x;
            int y = boardCCTV.get(i).y;
            int dir = cctvDir[i];

            for (int j = 0; j < cctv[type].get(dir).size(); j++) { // cctv[type].get(dir): CCTV 방향 리스트 (1번 CCTV를 제외하고는 감시 방향이 2개 이상이기 때문)
                int d = cctv[type].get(dir).get(j);

                if (d == 1) { // 우
                    for (int k = y + 1; k < m; k++) {
                        if (temp[x][k] == 6) break;
                        if (temp[x][k] >= 1 && temp[x][k] <= 5) continue;
                        temp[x][k] = -1;
                    }
                }

                if (d == 2) { // 하
                    for (int k = x + 1; k < n; k++) {
                        if (temp[k][y] == 6) break;
                        if (temp[k][y] >= 1 && temp[k][y] <= 5) continue;
                        temp[k][y] = -1;
                    }
                }

                if (d == 3) { // 좌
                    for (int k = y - 1; k >= 0; k--) {
                        if (temp[x][k] == 6) break;
                        if (temp[x][k] >= 1 && temp[x][k] <= 5) continue;
                        temp[x][k] = -1;
                    }
                }

                if (d == 4) { // 상
                    for (int k = x - 1; k >= 0; k--) {
                        if (temp[k][y] == 6) break;
                        if (temp[k][y] >= 1 && temp[k][y] <= 5) continue;
                        temp[k][y] = -1;
                    }
                }
            }
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) area++;
            }
        }
        return area;
    }

    private static void initCCTV() {
        cctv = new ArrayList[6];

        cctv[1] = new ArrayList<>(Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4)
        ));

        cctv[2] = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(2, 4)
        ));

        cctv[3] = new ArrayList<>(Arrays.asList(
                Arrays.asList(4, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4)
        ));

        cctv[4] = new ArrayList<>(Arrays.asList(
                Arrays.asList(3, 4, 1),
                Arrays.asList(4, 1, 2),
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 3, 4)
        ));

        cctv[5] = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2, 3, 4)
        ));
    }
}
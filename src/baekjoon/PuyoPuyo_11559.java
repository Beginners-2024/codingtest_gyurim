package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * N = 12, M = 6
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(NM * NM * Mlogn) = O(1)
 *      1번째 NM = while 문 최악 수행 횟수
 *      2번째 NM = runChain() 이중 for 문 수행 시간
 *      Mlogn = runChain() 속 TreeSet의 addAll() 메서드 수행 시간
 *      * NM은 상수 시간
 * 메모리: 14304 KB, 시간: 112 ms
 **************************************************************************************/

public class PuyoPuyo_11559 {
    private static int dx[] = {0, 0, 1, -1};
    private static int dy[] = {1, -1, 0, 0};
    private static char[][] fields;
    private static boolean[][] visit;

    private static class Index implements Comparable<Index> {
        int x, y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Index o) {
            // 1순위: y가 작은 수, 2순위: x가 큰 수
            if (this.y == o.y) {
                return Integer.compare(o.x, this.x); // y가 같다면, x가 큰 것이 먼저 (내림차순)
            }
            return Integer.compare(this.y, o.y); // y가 작은 것이 먼저 (오름차순)
        }
    }

    private static class Puyo {
        int x, y;
        int count;

        Puyo(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fields = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String row = br.readLine();

            for (int j = 0; j < 6; j++) {
                fields[i][j] = row.charAt(j);
            }
        }

        int count = 0;
        while (true) {                              // 최악의 경우, NM번 실행
            visit = new boolean[12][6];             // visit 배열 초기화

            Set<Index> removed = runChain();         // 터져야할 뿌요 리스트 리턴

            if (removed.size() == 0) break;         // 터져야할 뿌요가 없다면, 프로그램 종료

            resetFields(removed);                   // 터져야할 뿌요들을 터뜨리고, 위에 있던 뿌요들이 내려오게 함
            count++;
        }
        System.out.println(count);
    }

    private static void resetFields(Set<Index> removed) { // O(NM)
        int[][] removedFields = new int[12][6];

        for (Index rm : removed) {
            removedFields[rm.x][rm.y] = -1; // 삭제해야할 위치
        }

        for (int i = 0; i < 6; i++) {
            List<Character> list = new ArrayList<>();

            for (int j = 11; j >= 0; j--) {
                if (removedFields[j][i] != -1) { // 터지지 않은 뿌요들만 저장
                    list.add(fields[j][i]);
                }
            }

            for (int j = list.size(); j < 12; j++) {
                list.add('.');
            }

            // list[0] = 11행 i열에 들어가야 할 데이터 저장
            // list[1] = 10행 i열에 들어가야 할 데이터 저장
            // ..
            // list[11] = 0행 i열에 들어가야 할 데이터 저장
            // list를 fields[i][0~11]에 덮어써야함

            for (int j = 0; j < 12; j++) {
                fields[11 - j][i] = list.get(j);
            }
        }
    }

    // 1회의 연쇄 실행 (여러 그룹이 터질 수 있음)
    private static Set<Index> runChain() { // O(NM * Mlogn)
        Set<Index> removed = new TreeSet<>();

        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (visit[i][j]) continue;
                if (fields[i][j] == '.') continue;

                removed.addAll(runPuyo(i, j, fields[i][j])); // O(Mlogn) (M: 주어진 컬렉션의 크기, n: 현재 TreeSet의 크기)
            }
        }

        return removed;
    }

    // 1개의 색깔에 대해서만 뿌요뿌요 수행 (BFS)
    private static List<Index> runPuyo(int x, int y, char color) { // O(V+E) = O(NM)
        List<Puyo> removed = new ArrayList<>();

        Queue<Puyo> queue = new LinkedList<>();
        queue.offer(new Puyo(x, y, 0));
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Puyo cur = queue.poll();

            removed.add(cur);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!oob(nx, ny) && !visit[nx][ny]) {
                    if (color == fields[nx][ny]) {
                        visit[nx][ny] = true;
                        queue.offer(new Puyo(nx, ny, cur.count + 1));
                    }
                }
            }
        }

        List<Index> index = new ArrayList<>();

        // 인접한 뿌요 개수 >= 4: 해당 뿌요들은 터져야함
        // 인접한 뿌요 개수 < 4: 터지지 않음
        if (removed.size() >= 4) {
            for (Puyo py : removed) {
                index.add(new Index(py.x, py.y));
            }
        }

        return index;
    }


    private static boolean oob(int x, int y) {
        return x < 0 || y < 0 || x >= 12 || y >= 6;
    }
}

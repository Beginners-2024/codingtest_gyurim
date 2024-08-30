package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ M ≤ 100
 * 3 ≤ N ≤ 10,000
 * 1 ≤ 행성의 크기 ≤ 1,000,000
 * 시간제한: 2초
 *------------------------------------------------------------------------------------
 * 메모리: 184576 KB, 시간: 1488 ms
 **************************************************************************************/

public class 멀티버스_18869 {
    private static int m, n;
    private static HashMap<String, Integer> map;

    private static class Index implements Comparable<Index> {
        int num;
        int originIndex;

        Index(int num, int originIndex) {
            this.num = num;
            this.originIndex = originIndex;
        }

        @Override
        public int compareTo(Index o) {
            if (this.num == o.num) {
                return Integer.compare(this.originIndex, o.originIndex);
            }
            return Integer.compare(this.num, o.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new HashMap<>();

        for (int i = 0; i < m; i++) { // 우주
            st = new StringTokenizer(br.readLine());
            int[] planets = new int[n];
            for (int j = 0; j < n; j++) { // 우주 속 행성 입력
                planets[j] = Integer.parseInt(st.nextToken());
            }

            String result = checkCondition(planets); // O(NlogN)

            if (map.containsKey(result)) { // O(1)
                map.put(result, map.get(result) + 1);
            } else map.put(result, 1);
        }

        int ans = 0;

        for (Map.Entry<String, Integer> element : map.entrySet()) {
            int count = element.getValue();
            if (count <= 1) continue;

            // nC2 계산
            ans += (count * (count - 1) / 2);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.print(sb);
    }

    private static String checkCondition(int[] planets) { // O(NlogN)
        List<Index> sorted = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sorted.add(new Index(planets[i], i));
        }

        Collections.sort(sorted); // Arrays.sort()는 최악의 경우 O(N^2) 따라서 Collections.sort() 사용

        int[] cond = new int[n]; // cond[i] 값이 정렬되었을 때의 인덱스 값이 들어감
        for (int i = 0; i < n; i++) {
            Index cur = sorted.get(i);

            if (i == 0) {
                cond[cur.originIndex] = i;
            } else {
                Index prev = sorted.get(i - 1);

                if (cur.num == prev.num) { // 현재 값이 이전 값과 같다면, 이전 값의 정렬 인덱스값과 동일한 값이 cond[현재]에 들어가야 함
                    cond[cur.originIndex] = cond[prev.originIndex];
                } else {
                    cond[cur.originIndex] = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(cond[i]);
        }

        return sb.toString();
    }
}

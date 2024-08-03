package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 *
 * [조건]
 * 1. 공주가 가장 좋아하는 계절인 3/1 ~ 11/30 까지 매일 꽃이 1가지 이상 피어있어야함
 * 2. 정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 함
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 52544 KB, 시간: 516 ms
 **************************************************************************************/

public class 공주님의_정원_2457 {
    private static boolean[] visit;
    private static Date[] flowers;
    private static int n;

    private static class Date implements Comparable<Date> {
        int start;
        int end;

        Date(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // 1순위: start 날짜가 작은
        // 2순위: end 날짜가 큰
        @Override
        public int compareTo(Date o) {
            if (this.start != o.start)
                return Integer.compare(this.start, o.start);
            else return Integer.compare(o.end, this.end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        flowers = new Date[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) { // O(N)
            st = new StringTokenizer(br.readLine());

            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Date(sMonth * 100 + sDay, eMonth * 100 + eDay);
        }

        Arrays.sort(flowers);

        System.out.println(selectFlower());
    }

    private static int selectFlower() {
        int count = 0;

        int start = 301;    // 다음 꽃이 시작되어야하는 최소 시작일을 담고 있음 (즉, 이전 꽃의 종료일)
        int endDay = 1201;
        int index = 0;
        int max = 0;        // 최적의 다음 꽃을 찾기 위해, 현 시점의 가장 늦은 종료일을 담고 있음 (즉, 더 최적의 꽃이 나온다면, 해당 꽃의 종료일로 갱신됨)

        while (start < endDay) { // O(N)
            boolean isFound = false;

            for (int i = index; i < n; i++) {
                if (flowers[i].start > start) {
                    // 1. 3/1에 꽃이 필 수 없다면, break
                    // 2. 이전 꽃의 종료일(start)보다 현재 i번째 꽃의 시작일이 늦다면, 간격이 생겼으므로, break
                    break;
                }

                if (max < flowers[i].end) {
                    // 현재 i번째 꽃의 종료일이 이전 꽃 종료일(max)보다 늦다면, 다음 꽃을 찾았다는 뜻! => max 갱신
                    isFound = true;
                    max = flowers[i].end;
                    index = i + 1;
                }
            }

            if (isFound) {
                // 다음 꽃을 찾았다면, start에 직전 꽃의 종료일을 넣어줌
                start = max;
                count++;
            } else break;
        }

        if (max < endDay) return 0; // 11/30까지 꽃이 필 수 없다면, 조건 만족 X
        return count;
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************************
 * 1 ≤ N ≤ 100,000
 * 0 <= time <= 21억 47483647
 * 시간 제한: 2초
 *
 * O(N) = 100,000 < 2 * 10^9
 * O(NlogN) = 100,000 * 5 < 2 * 10^9
 * O(N^2) = 10^10 > 2 * 10^9 => 시간초과 발생
 *
 * dfs 로 푼다면 시간초과 발생 (ㅠㅠ)
 *-------------------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 44636 KB, 시간: 480 ms
 **************************************************************************************************/

public class 회의실_배장_1931 {
    private static int n;
    private static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(start, end);
        }
        Arrays.sort(meetings); // 끝나는 시간이 빠른 순서 + 끝나는 시간이 같다면 시작하는 시간이 빠른 순서로 정렬

        int idx = 1;
        int time = meetings[0].end;
        int maxMeeting = 1;
        boolean[] visit = new boolean[n]; // 시작 시간과 끝나는 시간이 동일한 회의가 1번만 카운트되게 하기 위해서 사용
        visit[0] = true;

        while (idx < n) {
            // 다음 회의 시작 시간은 현재 진행되고 있는 회의의 종료 시간보다 같거나 커야 함
            // 이미 진행했던 회의는 다시 진행하지 않음
            if (time <= meetings[idx].start && !visit[idx]) { // 다음 진행할 회의가 정해짐
                time = meetings[idx].end;
                visit[idx] = true;
                maxMeeting++;
            } else {
                idx++;
            }
        }

        System.out.println(maxMeeting);
    }

    private static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            if (this.end != other.end) {
                return this.end - other.end; // 오름차순 정렬
            } else {
                return this.start - other.start; // 오름차순 정렬
            }
        }
    }
}

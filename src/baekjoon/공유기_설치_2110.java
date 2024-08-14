package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 이분탐색을 통해, 거리를 좁히며 최단 거리 탐색
 *------------------------------------------------------------------------------------
 * 메모리: 34316 KB, 시간: 320 ms
 **************************************************************************************/

public class 공유기_설치_2110 {
    private static int c, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        int left = 1;
        int right = list.get(n - 1) - list.get(0) + 1; // + 1 중요

        while (left < right) {
            int mid = (left + right) / 2; // 공유기 간 최소 거리

            int count = checkWifi(list, mid);
            if (count < c) {
                // 공유기 설치할 수 없음 -> 최소 거리 좁히기
                right = mid;
            } else {
                // 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치
                left = mid + 1;
            }
        }

        System.out.println(left - 1);
    }

    private static int checkWifi(List<Integer> wifi, int distance) {
        int prev = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (wifi.get(i) - wifi.get(prev) >= distance) {
                prev = i;
                count++;
            }
        }

        return count;
    }
}

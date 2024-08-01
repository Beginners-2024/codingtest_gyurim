package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2 ≤ N ≤ 30,000
 * 2 ≤ d ≤ 3,000
 * 2 ≤ k ≤ 3,000 (k ≤ N)
 * 1 ≤ c ≤ d
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 19572 KB, 시간: 240 ms
 **************************************************************************************/

public class 회전초밥_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Integer> sushi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            sushi.add(num);
        }

        int[] count = new int[d + 1];
        int type = 0;

        for (int i = 0; i < k; i++) {
            count[sushi.get(i)]++;

            if (count[sushi.get(i)] == 1 && sushi.get(i) != c) {
                type++;
            }
        }

        int left = 0;
        int right = k;
        int maxType = type;

        while (right != k - 1) { // O(N)
            if (sushi.get(left) != c) { // left가 가리키는 초밥 제거
                count[sushi.get(left)]--;
                if (count[sushi.get(left)] == 0) type--;
            }

            if (sushi.get(right) != c) { // right가 가리키는 초밥 추가
                count[sushi.get(right)]++;
                if (count[sushi.get(right)] == 1) type++;
            }

            right++;
            left++;

            maxType = Math.max(maxType, type);

            right = (right + n) % n;
            left = (left + n) % n;
        }

        System.out.println(maxType + 1); // 맨 마지막에 쿠폰 초밥을 추가 적용
    }
}

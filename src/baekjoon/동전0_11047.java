package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 <= n <= 10, 1 <= k <= 100,000,000
 * 1 <= Ai <= 1,000,000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14168 KB, 시간: 124ms
 **************************************************************************************/

public class 동전0_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n]; // 오름차순 정렬

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        int money = k;
        int needCoin = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (money < a[i]) continue;

            int divide = money / a[i];
            money -= (a[i] * divide);
            needCoin += divide;

            if (money == 0) break;
        }

        System.out.println(needCoin);
    }
}

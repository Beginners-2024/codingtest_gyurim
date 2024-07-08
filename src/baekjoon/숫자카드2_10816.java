package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 500,000
 *-10,000,000 <= 숫자 카드에 적힌 수 <= 10,000,000
 * 1 ≤ M ≤ 500,000
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(n + m)
 * 메모리: 199440 KB, 시간: 1316 ms
 **************************************************************************************/

public class 숫자카드2_10816 {
    private static int n;
    private static int[] card;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        card = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card); // O(NlogN)

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) { // O(MlogN)
            int num = Integer.parseInt(st.nextToken());
            int lowerBound = getLowerBound(num);
            int upperBound = getUpperBound(num);
            sb.append(upperBound - lowerBound).append(" ");
        }

        System.out.println(sb);
    }

    private static int getLowerBound(int num) {
        int left = 0;
        int right = n;

        while (left < right) {
            int idx = (left + right) / 2;
            if (num <= card[idx]) {
                right = idx;
            } else { // num > card[idx]
                left = idx + 1;
            }
        }
        return right;
    }

    private static int getUpperBound(int num) {
        int left = 0;
        int right = n;

        while (left < right) {
            int idx = (left + right) / 2;
            if (num < card[idx]) {
                right = idx;
            } else { // num >= card[idx]
                left = idx + 1;
            }
        }
        return right;
    }
}
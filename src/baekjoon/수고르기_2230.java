package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 * 0 ≤ M ≤ 2,000,000,000
 * 0 ≤ |A[i]| ≤ 1,000,000,000
 * 시간 제한: 2초
 *
 * 만약, 이중 for문을 이용해 구한다면 O(N^2) = O((10^10) > O(10^9) 시간초과 발생
 * 따라서, 투포인터를 이용한 O(N) 로직 필요
 *      투포인터 left와 right는 배열 a를 한 번 순회하고, 최악의 경우 배열 끝까지 가기 때문에 O(n) 시간복잡도 가짐
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(정렬 + 투포인터 탐색) = O(NlogN + N) = O(NlogN)
 * 메모리: 32108 KB, 시간: 416 ms
 **************************************************************************************/

public class 수고르기_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] a = new int[n]; // n+1로 할당했더니, sort 시, 맨 뒤에 있던 0이 포함되어 원하는 로직을 수행할 수 없었음! (주의)

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a); // O(NlogN)

        int left = 0;
        int right = 1;
        int diff = a[right] - a[left];
        int minDiff = Integer.MAX_VALUE;

        while (left <= right) { // O(N)
            if (right == n) break;
            diff = a[right] - a[left];

            if (diff < m) { // m보다 작다면
                right++;
            } else if (diff >= m) { // m보다 크다면
                minDiff = Math.min(minDiff, diff);
                left++;
            }
        }
        System.out.println(minDiff);
    }
}

package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 10 ≤ N < 100,000
 * 0 < S ≤ 100,000,000
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(투포인터 탐색) = O(N) = O(N)
 * 메모리: 23912 KB, 시간: 296 ms
 **************************************************************************************/

public class 부분합_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[left]; // 부분합

        int minLength = Integer.MAX_VALUE;

        while (left <= right) { // O(N)
            if (right == n) break;

            if (sum < s) { // sum이 s보다 작은 경우
                right++;
                if (right < n)
                    sum += arr[right];
            } else { // sum이 s보다 같거나 큰 경우
                sum -= arr[left];
                minLength = Math.min(right - left + 1, minLength); // 부분합이 s 이상인 경우, 가장 짧은 것의 길이 갱신

                if (left == right) { // 만약 left와 right가 동시에 한 개의 값을 가리키고 있다면 두 인덱스 모두 + 1
                    left++;
                    right++;
                    if (right < n)
                        sum += arr[right];
                } else { // left와 right가 서로 다른 값을 가리키고 있다면 left만 + 1 해주어 부분합을 감소시킴
                    left++;
                }
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            minLength = 0;
        }

        System.out.println(minLength);
    }
}

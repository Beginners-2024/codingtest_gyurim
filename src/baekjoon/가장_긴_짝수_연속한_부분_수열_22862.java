package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 <= N <= 1,000,000
 * 1 <= K <= 100,000
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도:  O(N + K)
 * 메모리: 91248 KB, 시간: 496 ms
 **************************************************************************************/

public class 가장_긴_짝수_연속한_부분_수열_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int oddCount = 0;
        int left = 0;
        int right = 0;
        int evenCount = 0;
        int maxEven = 0;

        while (right < n) { // O(N + K)
            if (arr[right] % 2 == 0) { // 짝수
                evenCount++;
                maxEven = Math.max(maxEven, evenCount);
                right++;
            } else { // 홀수
                if (oddCount < k) { // 연속된 수열 속 홀수의 개수가 k 개 넘지 않는 경우
                    oddCount++;
                    right++;
                } else {            // 연속된 수열 속 홀수의 개수가 k 개 넘기에, left 인덱스를 옮겨줌
                    while (true) {
                        if (arr[left] % 2 == 1) { // left 인덱스가 홀수를 만날 때까지, left 인덱스 + 1
                            oddCount--;
                            left++;
                            break;
                        } else {
                            evenCount--;
                            left++;
                        }
                    }
                }
            }
        }
        System.out.println(maxEven);
    }
}

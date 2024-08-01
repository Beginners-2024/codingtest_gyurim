package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 1 ≤ N ≤ 100,000
 * 1 ≤ arr[i] ≤ 100,000
 *
 * 시간 제한: 1초
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 35040 KB, 시간: 380 ms
 **************************************************************************************/

public class List_of_Unique_Numbers_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();            // key: 수열, value: 수열이 나온 가장 최근의 인덱스

        int[] count = new int[n];
        Arrays.fill(count, n);

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minIndex = n;
        // 1 2 2 1 1 의 경우,
        // 0번째 '1'과 동일한 '1'이 index: 3에 나왔지만
        // index: 1와 index: 2의 '2'가 중복되었기 때문에
        // 0번째 '1'은 index: 1까지만 연속할 수 있음
        // 이를 반영하기 위해 minIndex 변수 관리

        for (int i = n - 1; i >= 0; i--) { // 역순으로 체크
            if (map.containsKey(arr[i])) {
                count[i] = Math.min(map.get(arr[i]), minIndex); // 수열의 가장 최근 인덱스와 중복이 발생한 가장 최근 인덱스 중 더 작은 값을 할당 => 중복이 발생한 지점을 넘어서 연속하면 안됨
                map.put(arr[i], i);     // 최근 인덱스값 갱신
                minIndex = count[i];
            }
            else {
                count[i] = minIndex;
                map.put(arr[i], i);
            }
        }

        long numberOfCases = 0;
        for (int i = 0; i < n; i++) {
            numberOfCases += (count[i] - i);
        }
        System.out.print(numberOfCases);
    }
}

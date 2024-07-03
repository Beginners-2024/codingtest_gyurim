package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * n <= 11
 * 시간 제한: 1초
 *
 * 따라서 O(10^7) 이하의 시간복잡도를 만족하는 알고리즘 구현 필요 (하지만 10^7은 시간초과 걸릴 가능성 큼)
 * 백트래킹으로 모든 조합을 구한 뒤, n을 만족하는지 체크하는 로직으로 푼다면 (가지치기를 하지 않는다는 전제 하에) 10^10 시간복잡도를 가져 시간초과 난다고 생각
 *------------------------------------------------------------------------------------
 * 시간복잡도: O(N)
 * 메모리: 14216 KB, 시간: 124ms (0.124초)
 **************************************************************************************/

public class 일이삼_더하기_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int dp[] = new int[12]; // 최대값으로 설정해줘야함.. n = 2 입력받았을 때, 31번째 줄 dp[3] = 4 에서 런타임 오류남
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                // dp[i]에 dp[i-1] + "1" 포함
                // dp[i]에 dp[i-2] + "2" 포함
                // dp[i]에 dp[i-3] + "3" 포함
            }
            System.out.println(dp[n]);
        }
    }
}

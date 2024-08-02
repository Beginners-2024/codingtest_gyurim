package baekjoon;

import java.io.*;
import java.util.*;

/**************************************************************************************
 * 2시간 이상 걸리고 있음 ㅠㅠ .. 토요일에 이어서 풀어야지
 *------------------------------------------------------------------------------------
 **************************************************************************************/

public class 공주님의_정원_2457 {
    private static boolean[] visit;
    private static Date[] flowers;
    private static int n;

    private static class Date implements Comparable<Date> {
        int sMonth;
        int sDay;
        int eMonth;
        int eDay;

        Date(int startMonth, int startDay, int endMonth, int endDay) {
            this.sMonth = startMonth;
            this.sDay = startDay;
            this.eMonth = endMonth;
            this.eDay = endDay;
        }

        // 1순위: startMonth가 작은
        // 2순위: startDay가 작은
        // 3순위: endMonth가 작은
        // 4순위: endDay가 작은
        @Override
        public int compareTo(Date o) {
            if (this.sMonth != o.sMonth)
                return Integer.compare(this.sMonth, o.sMonth);
            else {
                if (this.sDay != o.sDay)
                    return Integer.compare(this.sDay, o.sDay);
                else {
                    if (this.eMonth != o.eMonth)
                        return Integer.compare(this.eMonth, o.eMonth);
                    else return Integer.compare(this.eDay, o.eDay);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        flowers = new Date[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Date(sMonth, sDay, eMonth, eDay);
        }

        Arrays.sort(flowers);

        System.out.println(selectFlower());
    }

    private static Date princess = new Date(3, 1, 11, 30); // 공주 선호 날짜

    private static int selectFlower() {
        int left = 0;

        // 1. 최적의 시작 left 설정 (공주가 선호하는 날짜 기간 안에 있어야 함)
        while (left < n) {
            Date start = flowers[left];

            if (princess.sMonth > start.sMonth) left++;
            else if (princess.sMonth == start.sMonth) {
                if (princess.sDay > start.sDay) left++;
                else {
                    left -= 1;
                    break;
                }
            } else {
                left -= 1;
                break;
            }
        }
        System.out.println("left: " + left);

        if (left == n) return 0;

        int count = 1;
        int right = left + 1;

        while (right < n) {
            Date prev = flowers[left];
            Date next = flowers[right];

            printDate(prev);
            printDate(next);
            System.out.println();

            if (!checkDate(prev, next) && checkDate(next, princess)) {
                left = right - 1;
                System.out.println(left + " " + right);
                count++;
            } else {
                // 현재 right 인덱스부터, right 고려 대상임 -> 따라서, 최적의 right를 찾아야 함
                right++;

                if (right == n) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printDate(Date date) {
        System.out.println(date.sMonth + "월 " + date.sDay + "일 ~ " + date.eMonth + "월 " + date.eDay + "일");
    }

    private static boolean checkDate(Date prev, Date next) { // 두 날짜 사이에 빈 간격이 있으면 안되기에 이를 체크
        if (prev.eMonth < next.sMonth) return false;
        else if (prev.eMonth == next.sMonth) {
            if (prev.eDay < next.sDay) return false;
        }

        if (prev.sMonth > next.eMonth) return false;
        else if (prev.sMonth == next.eMonth) {
            if (prev.sDay > next.eDay) return false;
        }

        return true;
    }
}

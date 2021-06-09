import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_13397_구간나누기2 {
    static int N, M, max, min, ans = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        cal();
    }

    private static void cal() {
        /**
         * 구간의 점수 : 구간에 속한 수의 [최댓값 - 최솟값]
         */
        max = Arrays.stream(arr).max().getAsInt();          // 배열의 최댓값
        min = Arrays.stream(arr).min().getAsInt();          // 배열의 최솟값

        int left = 0;               // 구간 점수의 최솟값 : 0 (구간의 크기가 0인 경우)
        int right = max - min;      // 구간 점수의 최댓값

        binarySearch(left, right);

        System.out.println(ans);
    }

    /** 이분 탐색 : O(LogN) */
    private static void binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (checked(mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    /** 해당 score(구간의 점수)를 만족시키는 구간의 개수가 M개 초과인지 판단 */
    private static boolean checked(int score) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int cnt = 0;

        for (int num : arr) {
            int tMax = Math.max(max, num);
            int tMin = Math.min(min, num);

            if (tMax - tMin <= score) {
                max = tMax;
                min = tMin;
            } else {
                max = num;
                min = num;

                cnt++;
            }

            if (cnt >= M)
                return false;
        }

        return true;
    }
}

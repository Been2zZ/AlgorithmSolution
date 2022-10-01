import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1448_삼각형만들기 {
    static int N, K = 3, maxLength = -1;
    static int[] arr;
    static boolean[] visited;
    static LinkedList<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        list = new LinkedList<>();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
//            list.add(sc.nextInt());
            arr[i] = sc.nextInt();
        }

//        visited = new boolean[N];
//        combi(0, 0);
//        Collections.sort(list);
        maxLength = sol();

        System.out.println(maxLength);
        sc.close();
    }

    /** 메모리 초과 */
    private static int sol() {
        Arrays.sort(arr);

        for (int i = N - 1; i > 1; i--)
            if (isTriangle(arr[i], arr[i - 1], arr[i - 2]))
                return arr[i] + arr[i - 1] + arr[i - 2];

        return -1;
    }

    /** 메모리 초과 */
    private static void sol_list() {
        while (true) {
            if (list.size() < 3)
                return;

            int i = list.size() - 1;

            int a = list.get(i);
            int b = list.get(i - 1);
            int c = list.get(i - 2);

            if (isTriangle(a, b, c)) {
                maxLength = a + b + c;
                break;
            } else {
                list.removeLast();
            }
        }

    }

    /** 삼각형 조건 : 최대 변의 길이 < 나머지 두 변의 길이의 합 */
    private static boolean isTriangle(int a, int b, int c) {
        return (b + c) > a;
    }

    /** 메모리 초과 */
    private static void combi(int start, int cnt) {
        if (cnt == K) {
            int max = 0, sum = 0;

            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    max = Math.max(max, arr[i]);
                    sum += arr[i];
                }
            }

            if ((sum - max) > max)
                maxLength = Math.max(maxLength, sum);

            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            combi(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
}

import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_10818_최소최대 {
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        System.out.println(min + " " + max);
    }
}

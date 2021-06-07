import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2576_홀수 {
    static int N = 7, min = Integer.MAX_VALUE, sum = 0;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        for (int i = 0; i < N; i++) {
            if (arr[i] % 2 == 1) {
                sum += arr[i];
                min = Math.min(min, arr[i]);
            }
        }

        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(sum + "\n" + min);
    }
}

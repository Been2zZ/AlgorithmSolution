import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_9613_GCD합 {
    static int T, N;
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        /**
         * 가능한 모든 쌍의 GCD(최대공약수) 합
         */
        for (int t = 0; t < T; t++) {
            long sum = 0;

            N = sc.nextInt();

            num = new int[N];
            for (int j = 0; j < N; j++)
                num[j] = sc.nextInt();

            for (int i = 0; i < N - 1; i++)
                for (int j = i + 1; j < N; j++)
                    sum += gcd_recursion(num[i], num[j]);

            System.out.println(sum);
        }

    }

    private static int gcd_recursion(int a, int b) {
        if (b == 0)
            return a;

        return gcd_recursion(b, a % b);
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int n;

        /** 유클리드 알고리즘 */
        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }
        return a;
    }
}

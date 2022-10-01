import java.util.Scanner;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2506_점수계산 {
    static int N, sum;
    static int[] arr, score;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new int[N];
        score = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        score[0] = arr[0];

        for (int i = 1; i < N; i++) {
            if (arr[i] == 1) {
                if (arr[i - 1] == 1)
                    score[i] = score[i - 1] + 1;
                else
                    score[i] = 1;
            }
        }

        for (int i = 0; i < N; i++)
            sum += score[i];

        System.out.println(sum);
    }
}

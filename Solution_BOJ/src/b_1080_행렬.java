import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1080_행렬 {
    static int N, M;
    static char[][] A;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new char[N][M];

        // origin
        for (int i = 0; i < N; i++)
            A[i] = br.readLine().toCharArray();


        int cnt = 0;
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (input[j] != A[i][j]) {
                    check[i][j] = true;     // 값이 다른 곳 True
                    cnt++;
                }
            }
        }

        /**
         * check : A vs B 값이 변경된 곳 : T
         *
         * check 배열의 T를 F로 변경!
         * 원상 복귀(check -> false) 연산횟수 = A -> B 연산 횟수
         */

        if (cnt == 0)
            System.out.println(0);
        else System.out.println(sol());

    }
    private static int sol() {
        if (N < 3 || M < 3)
            return -1;

        int cnt = 0;

        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                // 마지막 3값이 다를 경우 불가능
                if (i == N - 3 && !(check[i][j] == check[i + 1][j] && check[i][j] == check[i + 2][j]))
                    return -1;
                if (j == M - 3 && !(check[i][j] == check[i][j + 1] && check[i][j] == check[i][j + 2]))
                    return -1;

                if (check[i][j]) {
                    // A, B 값이 달라진 경우
                    reverse(i, j);
                    cnt++;
                }
            }
        }

        boolean flag = check[N - 3][M - 3];
        for (int i = N - 1; i > N - 3; i--) {
            for (int j = M - 1; j > M - 3; j--) {
                if (flag != check[i][j])
                    return -1;
            }
        }

        return cnt;
    }

    /** 3x3 배열 뒤집기 */
    private static void reverse(int x, int y) {
        for (int i = x; i < x + 3; i++)
            for (int j = y; j < y + 3; j++)
                check[i][j] = !check[i][j];
    }

}

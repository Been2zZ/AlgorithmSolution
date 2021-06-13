import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [삼성 SW 역량 테스트 기출 문제]
 */
public class b_13458_시험감독 {
    static int N;
    static long B, C, sum;
    static long[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());       // 시험장 개수

        A = new long[N];                            // 시험장 응시자 수
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            A[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        B = Long.parseLong(st.nextToken());       // 총감독관
        C = Long.parseLong(st.nextToken());       // 부감독관

        for (int i = 0; i < N; i++) {
            long temp = A[i] - B;
            if (temp < 0)
                sum += 1;
            else
                sum += sol(temp);
        }

        System.out.println(sum);
    }

    private static long sol(long temp) {
        long p = temp / C;
        long q = temp % C;

        if (q > 0)
            p += 1;

        return p + 1;
    }
}

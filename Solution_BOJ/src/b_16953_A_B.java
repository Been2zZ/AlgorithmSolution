import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_16953_A_B {
    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(sol());
    }

    private static long sol() {
        Queue<long[]> q = new LinkedList<>();
        q.add(new long[] {A, 1});

        while (!q.isEmpty()) {
            long[] poll = q.poll();
            long curr = poll[0];
            long cnt = poll[1];

            if (curr == B)
                return cnt;

            for (int i = 0; i < 2; i++) {
                long next = (i == 0) ? curr * 2 : (curr * 10) + 1;

                if (next > B)
                    break;
                else
                    q.add(new long[] {next, cnt + 1});
            }
        }
        return -1;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_10797_10부제 {
    static int D, N = 5, ans;
    static int[] car;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        D = Integer.parseInt(st.nextToken());

        car = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            car[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++)
            if (car[i] == D)
                ans++;

        System.out.println(ans);
    }
}

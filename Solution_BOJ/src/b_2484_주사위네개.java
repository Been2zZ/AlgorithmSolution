import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2484_주사위네개 {
    static int N, max = Integer.MIN_VALUE;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        map = new int[4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++)
                map[j] = Integer.parseInt(st.nextToken());

            max = Math.max(max, sol(map));
        }

        System.out.println(max);
    }

    private static int sol(int[] map) {
        int[] dice = new int[7];

        for (int i = 0; i < 4; i++)
            dice[map[i]]++;

        int cnt = 0, temp = 0, max = -1;

        for (int i = 1; i < 7; i++) {
            if (dice[i] == 4) {
                return 50000 + (i * 5000);
            } else if (dice[i] == 3) {
                return 10000 + (i * 1000);
            } else if (dice[i] == 2) {
                if (cnt == 1)       // ex. 2 2 3 3
                    return 2000 + (temp * 500) + (i * 500);
                temp = i;           // ex. temp = 2
                cnt++;              // cnt : 0 -> 1
            } else if (dice[i] == 1) {
                max = Math.max(max, i);
            }
        }

        if (cnt == 1)       // ex. 2 2 1 4
            return 1000 + (temp * 100);
        else                // ex. 1 2 3 4
            return max * 100;
    }

}

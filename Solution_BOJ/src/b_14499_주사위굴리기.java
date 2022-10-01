import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_14499_주사위굴리기 {

    static int N, M, x, y, K;

    static int[][] map;

    static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());           // 지도의 세로 크기 (행)
        M = Integer.parseInt(st.nextToken());           // 지도의 가로 크기 (열)
        x = Integer.parseInt(st.nextToken());           // 주사위 x 좌표
        y = Integer.parseInt(st.nextToken());           // 주사위 y 좌표
        K = Integer.parseInt(st.nextToken());           // 명령의 개수

        map = new int[N][M];
        move = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < M; j++)
                 map[i][j] = Integer.parseInt(st.nextToken());
        }

        /**
         * 동 : 1
         * 서 : 2
         * 북 : 3
         * 남 : 4
         */
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++)
            move[i] = Integer.parseInt(st.nextToken());

        sol();

    }

    private static void sol() {

    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static class Dice {
        int up;
        int down;

        public Dice(int up, int down) {
            this.up = up;
            this.down = down;
        }
    }
}

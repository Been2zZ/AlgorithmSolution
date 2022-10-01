import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */
public class b_7569_토마토 {
    static int M, N, H, all, input_sum, sum, cnt;
    static int[][][] box;
    static Queue<Tomato> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());       // 열
        N = Integer.parseInt(st.nextToken());       // 행
        H = Integer.parseInt(st.nextToken());       // 높이

        box = new int[H][N][M];

        /**
         *  1 : 익은 토마토
         *  0 : 익지 않은 토마토
         *  -1 : 토마토 X
         */
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < M; k++) {
                    int t = Integer.parseInt(st.nextToken());
                    box[i][j][k] = t;
                    if (t != -1) {
                        input_sum += t;
                        all++;
                    }
                }
            }
        }

        /**
         * input_sum == all : 저장될 때부터 모든 토마토가 익어있는 상태
         */
        if (input_sum != all) {
            for (int i = 0; i < H; i++)
                for (int j = 0; j < N; j++)
                    for (int k = 0; k < M; k++)
                        if (box[i][j][k] == 1)  // 익은 토마토
                            q.add(new Tomato(i, j, k));

            go();

            /**
             * 토마토가 모두 익지는 못하는 상황
             */
            if (input_sum + sum != all)
                cnt = -1;
        }

        System.out.println(cnt);
    }

    /**
     * 3차원 6 방향
     */
    static int[] dx = {0, 0, 0, 0, -1, 1};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dh = {-1, 1, 0, 0, 0, 0};

    private static void go() {
        int max = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            Tomato ct = q.poll();
            int cx = ct.x;
            int cy = ct.y;
            int ch = ct.h;

            for (int d = 0; d < 6; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                int nh = ch + dh[d];

                if (!check(nx, ny, nh))
                    continue;

                if (box[nh][nx][ny] == 0) {         // 익지 않은 토마토
                    q.add(new Tomato(nh, nx, ny));  // 큐에 추가
                    sum++;

                    /** 익은 토마토 값 = 이전의 익은 토마토 값 + 1 => 날짜 계산 */
                    box[nh][nx][ny] = box[ch][cx][cy] + 1;

                    if (box[nh][nx][ny] > max)
                        max = box[nh][nx][ny];
                }
            }
        }
        cnt = max - 1;
    }

    private static boolean check(int x, int y, int h) {
        return (x >= 0 && x < N && y >= 0 && y < M && h >= 0 && h < H);
    }

    private static void print() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(box[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("---------");
        }
    }

    private static class Tomato {
        int h;
        int x;
        int y;

        public Tomato(int h, int x, int y) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }
}

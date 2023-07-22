import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b_14502_연구소 {

    static int N, M, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());       // 지도의 세로 크기 (3 ≤ N, M ≤ 8)
        M = Integer.parseInt(st.nextToken());       // 지도의 가로 크기 (3 ≤ N, M ≤ 8)

        /**
         * 0: 빈칸
         * 1: 벽
         * 2: 바이러스
         */
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0);

        System.out.println(max);
    }

    /**
     * 벽 세우기 (3개)
     *
     * @param cnt
     */
    private static void makeWall(int cnt) {
        if (cnt == 3) {
            // 바이러스 퍼뜨리기
            max = Math.max(max, solution());
            return;
        }

        // 모든 경우의 수로 벽 세우기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    makeWall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    /**
     * 바이러스 인접한 곳으로 퍼뜨리기
     *
     * @return
     */
    private static int solution() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] viruses = new boolean[N][M];

        // 바이러스 위치 queue에 삽입 (시작위치)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    q.add(new int[]{i, j});
                    viruses[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                // 범위를 넘어서는 경우 || 바이러스가 이미 퍼진 경우 || 빈칸이 아닌 경우
                if (!checked(nx, ny) || viruses[nx][ny] || map[nx][ny] != 0)
                    continue;

                q.add(new int[]{nx, ny});
                viruses[nx][ny] = true;
            }
        }

        int safeArea = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !viruses[i][j])
                    safeArea++;
            }
        }

        return safeArea;
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static boolean checked(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}

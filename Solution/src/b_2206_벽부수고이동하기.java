import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_2206_벽부수고이동하기 {
    static int N, M, INF;
    static int[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        INF = Integer.MAX_VALUE;

        map = new int[N][M];
        visited = new int[N][M];            // 벽 부순 횟수 && 방문 여부 배열

        /**
         *  0 : 이동 가능
         *  1 : 이동 불가능 (벽)
         * (1, 1), (N, M)은 항상 0
         */
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                visited[i][j] = INF;        // 무한대 초기화
            }
        }

        System.out.println(bfs(0, 0));
    }

    private static int bfs(int sx, int sy) {
        Queue<Point> q = new LinkedList<>();

        // x좌표, y좌표, 이동 횟수, 벽 부순 여부
        q.add(new Point(sx, sy, 1, 0));
        visited[sx][sy] = 0;        // 초기 벽 부순 여부 && 방문

        while (!q.isEmpty()) {
            Point curr = q.poll();

            // 목적지 도착 -> 이동 횟수 반환 종료
            if (curr.x == N - 1 && curr.y == M - 1)
                return curr.cnt;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                /**
                 *  curr.isBreak : 0 -> 이전에 벽을 부순 적이 없는 경우
                 *  curr.isBreak : 1 -> 이전에 벽을 부순 적이 있는 경우
                 *
                 *  visited[nx][ny] : 0 -> 이전에 벽 부수지 않고 방문 한 위치
                 *  visited[nx][ny] : 1 -> 이전에 벽 부순 후, 방문 한 위치
                 *  visited[nx][ny] : INF -> 방문한 적 없는 위치
                 */
                if (!check(nx, ny) || visited[nx][ny] <= curr.isBreak)
                    continue;

                int nextCnt = curr.cnt + 1;             // 이동 횟수
                int nextBreakCnt = curr.isBreak;        // 벽을 부순 여부

                if (map[nx][ny] == 0) {
                    /** 벽이 아닌 경우 (이동 가능) */
                    visited[nx][ny] = nextBreakCnt;
                    q.add(new Point(nx, ny, nextCnt, nextBreakCnt));
                } else if (map[nx][ny] == 1 && nextBreakCnt == 0) {
                    /** 벽인 경우 -> 벽을 부순 적이 없을 때, 부수고 이동 가능 */
                    visited[nx][ny] = nextBreakCnt;
                    // 벽 부순 후, 여부 표시 (+1)
                    q.add(new Point(nx, ny, nextCnt, nextBreakCnt + 1));
                }
            }
        }
        return -1;
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static class Point {
        int x;
        int y;
        int cnt;                // 이동 횟수
        int isBreak;            // 벽 부순 여부

        public Point(int x, int y, int cnt, int isBreak) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isBreak = isBreak;
        }
    }
}

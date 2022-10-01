import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2573_빙산 {
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        /**
         * map => 빙산 각 부분 높이 정보 배열
         *
         * num : 빙산 높이 (양의 정수)
         * 0 : 바다 (빙산X)
         */
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        /**
         * 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는
         * 일년마다 그 칸에 동서남북 네 방향으로 붙어있는
         * 0이 저장된 칸의 개수만큼 줄어든다.
         *
         * 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
         */

        /** DFS + while 무한루프, 종료조건 */
        solDFS();

        /** BFS */
//        solBFS();

    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void solBFS() {
        // while 반복 횟수 (일년마다 함수가 실행되므로 무한루프(연도 카운트) -> 종료 조건 탈출)
        visited = new boolean[N][M];

        int year = 0;

        while (true) {
            visited = new boolean[N][M];        // 방문 배열 초기화
            int area = 0;                       // 빙산 덩어리 변수

            /** BFS */
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (!visited[i][j] && map[i][j] > 0) {
                        bfs(i, j);
                        area++;                 // BFS 실행 후, 덩어리 수 증가
                    }

            if (area >= 2) {
                // 두 덩어리 이상인 경우 -> 최소 년도
                ans = year;
                break;
            } else if (area == 0) {
                // 두 덩어리 이상으로 분리 X -> 0 출력
                ans = 0;
                break;
            }

            // 1년씩 증가
            year++;
        }
        System.out.println(ans);
    }

    private static void bfs(int sx, int sy) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny) || visited[nx][ny])
                    continue;

                if (map[nx][ny] == 0) {
                    // 바다와 인접해있는 경우 -> 빙산 녹임(0미만 X)
                    map[cx][cy] = map[cx][cy] < 1 ? 0 :  map[cx][cy] - 1;
                } else if (map[nx][ny] > 0) {
                    q.add(new int[] {nx, ny});

                    // 빙산 영역 방문 처리 O
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void solDFS() {
        // while 반복 횟수 (일년마다 함수가 실행되므로 무한루프(연도 카운트) -> 종료 조건 탈출)
        int year = 0;

        while (true) {
            visited = new boolean[N][M];        // 방문 배열 초기화
            int area = 0;                       // 빙산 덩어리 변수

            /** DFS */
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (!visited[i][j] && map[i][j] > 0) {
                        dfs(i, j);
                        area++;                 // DFS 실행 후, 덩어리 수 증가
                    }

            if (area >= 2) {
                // 두 덩어리 이상인 경우 -> 최소 년도
                ans = year;
                break;
            } else if (area == 0) {
                // 두 덩어리 이상으로 분리 X -> 0 출력
                ans = 0;
                break;
            }

            // 1년씩 증가
            year++;
        }

        System.out.println(ans);
    }

    private static void dfs(int cx, int cy) {
        // 빙산 영역 방문 처리 O
        visited[cx][cy] = true;

        for (int d = 0; d < 4; d++) {
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            if (!checked(nx, ny) || visited[nx][ny])
                continue;

            if (map[nx][ny] == 0) {
                // 바다와 인접해있는 경우 -> 빙산 녹임(0미만 X)
                map[cx][cy] = map[cx][cy] < 1 ? 0 :  map[cx][cy] - 1;

                // 바다 영역 방문 처리 X
                visited[nx][ny] = false;
            } else if (map[nx][ny] > 0) {
                // 빙산 영역
                dfs(nx, ny);
            }
        }
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }
}

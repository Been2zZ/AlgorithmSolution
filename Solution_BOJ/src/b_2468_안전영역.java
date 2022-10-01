import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_2468_안전영역 {
    static int N, maxH, cnt, maxArea;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        /**
         * !! 첫 줄 숫자 하나 입력 받을 때 주의 !!
         *
         * 1. br = new BufferedReader(new InputStreamReader(System.in));
         * 2. st = new StringTokenizer(br.readLine(), " ");
         * 3. int N = Integer.parseInt(st.nextToken());
         */
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j]  = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        // 물에 안잠길 수도 있다 -> 비의 높이가 0일 수도 있다. -> 비가 오지 않은 경우
        for (int h = 0; h < maxH; h++) {
            visited = new boolean[N][N];
            cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {        // 물에 잠기지 않는 영역
//                        bfs(i, j, h);
                        dfs(i, j, h);
                        cnt++;
                    }
                }
            }

            // 최대 영역 수 갱신
            maxArea = Math.max(maxArea, cnt);
        }
        System.out.println(maxArea);
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static void dfs(int cx, int cy, int h) {
        visited[cx][cy] = true;

        for (int d = 0; d < 4; d++) {
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            if (!checked(nx, ny) || visited[nx][ny])
                continue;

            if (map[nx][ny] > h)
                // 물에 잠기지 않는 영역
                dfs(nx, ny, h);

        }
    }

    private static void bfs(int x, int y, int h) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny) || visited[nx][ny])
                    continue;

                if (map[nx][ny] > h) {
                    // 물에 잠기지 않는 영역
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}

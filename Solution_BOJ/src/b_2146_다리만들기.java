import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_2146_다리만들기 {
    static int N, ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        /**
         *  0 : 바다
         *  1 : 육지
         *  항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어짐
         */
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /** 섬 번호 부여 */
        int area= 1;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (map[i][j] == 1 && !visited[i][j])
                    dfs(i, j, area++);


        /** 짧은 연결 다리 구하기 */
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (map[i][j] > 0)
                    bfs(i, j, map[i][j]);

        System.out.println(ans);
    }

    private static void dfs(int x, int y, int area) {
        visited[x][y] = true;

        if (map[x][y] == 1)
            map[x][y] = area;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx, ny) || visited[nx][ny])
                continue;

            if (map[nx][ny] == 1)
                dfs(nx, ny, area);
        }

    }

    private static void bfs(int sx, int sy, int area) {
        visited = new boolean[N][N];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            int ccnt = curr[2];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!check(nx, ny) || visited[nx][ny] || map[nx][ny] == area)
                    continue;

                visited[nx][ny] = true;

                if (map[nx][ny] == 0)       // 바다
                    q.add(new int[] {nx, ny, ccnt + 1});
                else                        // 다른 섬
                    ans = Math.min(ans, ccnt);
            }
        }

    }


    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}

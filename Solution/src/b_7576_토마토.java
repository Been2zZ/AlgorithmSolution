import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_7576_토마토 {
    static int M, N, size, tomato, empty, ans;
    static int[][] box;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());       // 열
        N = Integer.parseInt(st.nextToken());       // 행

        box = new int[N][M];
        visited = new boolean[N][M];

        size = M * N;       // 상자의 넓이

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1)         // 토마토가 들어있는 칸
                    tomato++;
                else if (box[i][j] == -1)   // 비어있는 칸
                    empty++;
            }
        }

        if (size - empty == tomato)
            ans = 0;
        else
            ans = bfs();

        System.out.println(ans);

    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (box[i][j] == 1) {
                    // 익은 토마토 모두 큐에 담음
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!checked(nx, ny) || box[nx][ny] != 0)
                    continue;

                box[nx][ny] = box[cx][cy] + 1;
                q.add(new int[] {nx, ny});

            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0)
                    return -1;
                else if (box[i][j] > 0)
                    max = Math.max(max, box[i][j]);
            }
        }
        return max - 1;
    }

    private static boolean checked(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */
public class b_14503_로봇청소기 {
    static int N, M, r, c, d, cnt;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /**
         * 청소기 좌표 (r,c)
         * 바라보는 방향 d
         * d(0) : 북
         * d(1) : 동
         * d(2) : 남
         * d(3) : 서
         */

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        /**
         * 빈칸 : 0
         * 벽 : 1
         */

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(r, c, d);

        System.out.println(cnt);
    }

    // 북, 동, 남, 서 (반시계 방향 회전)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    /**
     * !! 이미 청소되어있는 칸 중복 청소 X, 벽 통과 X !!
     * 1. 현재 위치 청소
     * 2. 현재 위치에서 현재 방향 기준으로 왼쪽부터 차례로 탐색
     * 2-a. 왼쪽 방향 청소하지 않은 공간 -> 왼쪽 회전 -> 1칸 전진 -> 1번부터 진행
     * 2-b. 왼쪽 방향 청소 공간 X -> 왼쪽 회전 -> 2번 진행
     * 2-c. 네 방향 모두 청소 O or 벽 -> 현재 바라보는 방향 유지 1칸 후진 -> 2번 진행
     * 2-d. 네 방향 모두 청소 O or 벽 and 후진 시 벽 -> 작동 멈춤
     */
    private static void go(int cx, int cy, int dir) {
        // 1. 현재 위치 청소
        if (map[cx][cy] == 0) {
            map[cx][cy] = 2;
            cnt++;
        }

        // 2. 현재 위치에서 현재 방향 기준으로 왼쪽부터 차례로 탐색
        for (int d = 0; d < 4; d++) {
            dir = (dir + 3) % 4;        /** 왼쪽 회전 */
            int nx = cx + dx[dir];      // 왼쪽 회전 전진 x좌표
            int ny = cy + dy[dir];      // 왼쪽 회전 전진 y좌표

            if (check(nx, ny) && map[nx][ny] == 0) {
                // 2-a. 왼쪽 방향 청소하지 않은 공간 -> 왼쪽 회전 -> 1칸 전진 -> 1번부터 진행
                go(nx, ny, dir);    /** 1칸 전진 */
                return;
            }
        }

        // 네 방향 모두 청소 O or 벽

        /** 후진 */
        int bd = (dir + 2) % 4;
        int bx = cx + dx[bd];
        int by = cy + dy[bd];

        // 2-c. 네 방향 모두 청소 O or 벽 -> 현재 바라보는 방향 유지 1칸 후진 -> 2번 진행
        if (check(bx, by) && map[bx][by] != 1)
            go(bx, by, dir);
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M);
    }

    private static void print(int[][] arr, int x, int y, int d) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == x && j == y) {
                    if (d == 0)
                        System.out.print("↑" + " ");
                    else if (d == 1)
                        System.out.print("←" + " ");
                    else if (d == 2)
                        System.out.print("↓" + " ");
                    else if (d == 3)
                        System.out.print("→" + " ");
                }
                else
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

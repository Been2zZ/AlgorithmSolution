import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS, BFS 추천문제]
 */
public class b_1726_로봇 {
    static int M, N;
    static int[][] map;
    static boolean[][][] visited;
    static int sx, sy, sd;
    static int dx, dy, dd;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());       // 행
        N = Integer.parseInt(st.nextToken());       // 열

        map = new int[M + 1][N + 1];
        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         *  같은 위치에 도달할 수 있는 방법이 여러개 존재할 수 있으므로 [행][열][방향] 3차원 배열을 활용해 방문을 체크한다.
         */
        visited = new boolean[M + 1][N + 1][5];

        // 출발 지점 위치, 방향
        st = new StringTokenizer(br.readLine(), " ");
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        // 도착 지점 위치, 방향
        st = new StringTokenizer(br.readLine(), " ");
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());
        dd = Integer.parseInt(st.nextToken());

        System.out.println(sol());

    }

    /**
     * BFS
     *
     * @return 최소 명령 횟수
     */
    private static int sol() {
        Queue<Robot> q = new LinkedList<>();    // queue 선언 및 생성 (BFS)

        q.add(new Robot(sx, sy, sd, 0));        // 시작점 queue에 추가
        visited[sx][sy][sd] = true;                 // 시작점 방문 체크

        while (!q.isEmpty()) {          // queue 비워질 때까지 반복
            Robot curr = q.poll();      // 가장 최근에 넣은 item remove

            if (curr.x == dx && curr.y == dy && curr.d == dd)   // 도착지점에 도달한 경우
                return curr.cnt;        // 이동 횟수 반환

            // 명령 1. Go = k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
            // 위치 이동
            for (int k = 1; k <= 3; k++) {
                int nx = curr.x + mx[curr.d] * k;
                int ny = curr.y + my[curr.d] * k;

                // 범위를 벗어난 경우 or 장애물 만난 경우
                if (!checked(nx, ny) || map[nx][ny] == 1) break;

                // 방문하지 않은 경우
                if (!visited[nx][ny][curr.d]) {
                    q.add(new Robot(nx, ny, curr.d, curr.cnt + 1));
                    visited[nx][ny][curr.d] = true;
                }
            }

            // 명령 2. Turn dir - left or right, 왼쪽 또는 오른쪽으로 90도 회전한다.
            // 방향 전환
            for (int i = 0; i < 2; i++) {
                int nd = getNextDir(curr.d, i);     // 다음 방향

                // 방문하지 않은 경우
                if (!visited[curr.x][curr.y][nd]) {
                    q.add(new Robot(curr.x, curr.y, nd, curr.cnt + 1));
                    visited[curr.x][curr.y][nd] = true;
                }
            }

        }

        return -1;
    }

    /**
     * 방향 배열
     * 동쪽(1), 서쪽(2), 남쪽(3), 북쪽(4)
     */
    static int[] mx = {0, 0, 0, 1, -1};
    static int[] my = {0, 1, -1, 0, 0};

    private static boolean checked(int x, int y) {
        return (x > 0 && x <= M && y > 0 && y <= N);
    }

    /**
     * @param dir (curr dir)
     * @param num (0: left, 1: right)
     * @return next dir
     */
    private static int getNextDir(int dir, int num) {
        switch (dir) {
            case 1:     // 동
                return num == 0 ? 4 : 3;
            case 2:     // 서
                return num == 0 ? 3 : 4 ;
            case 3:     // 남
                return num == 0 ? 1 : 2;
            case 4:     // 북
                return num == 0 ? 2 : 1;
            default:
                return 0;
        }
    }

    static class Robot {
        int x;      // x 좌표
        int y;      // y 좌표
        int d;      // 바라보는 방향
        int cnt;    // 명령 횟수

        public Robot(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }
}

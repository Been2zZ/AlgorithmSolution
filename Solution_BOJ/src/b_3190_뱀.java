import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b_3190_뱀 {

    static int N;
    static int[][] map;
    static Queue<String[]> snackDir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());       // 보드의 크기 N x N (2 ≤ N ≤ 100)
        map = new int[N][N];                        // 보드

        st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());   // 사과의 개수 (0 ≤ K ≤ 100)
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken());   // 뱀의 방향 전환 횟수 (1 ≤ L ≤ 100)

        /**
         * 뱀의 방향 변환 정보 snackDir (X, C)
         * 정수 X, 문자 C
         */
        snackDir = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            snackDir.add(new String[]{st.nextToken(), st.nextToken()});     // 왼쪽(C가 'L') 오른쪽(C가 'D')
        }

        int sx = 0, sy = 0;                 // 뱀의 시작 위치
        int ans = solution(sx, sy);

        System.out.println(ans);
    }

    private static int solution(int sx, int sy) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        map[sx][sy] = 1;

        /**
         * 0: 우
         * 1: 하
         * 2: 좌
         * 3: 상
         */
        int dir = 0;                        // 우측 이동 시작

        int time = 0;

        while (true) {
            time++;

            int[] curr = q.peekLast();
            int cx = curr[0];
            int cy = curr[1];

            int nx = cx + dx[dir];
            int ny = cy + dy[dir];

            if (!checked(nx, ny) || map[nx][ny] == 1) break;

            q.add(new int[]{nx, ny});

            if (map[nx][ny] == 0) {     // 사과가 없는 경우
                int[] tail = q.pollFirst();
                int tx = tail[0];
                int ty = tail[1];
                map[tx][ty] = 0;        // 꼬리 이동
            }

            map[nx][ny] = 1;            // 머리 이동

            String[] currDir = snackDir.peek();  // 다음 방향 변환 정보
            if (currDir != null && time == Integer.parseInt(currDir[0])) {
                // 뱀의 방향 변환
                String changeDir = currDir[1];
                if ("L".equals(changeDir))      // 좌회전
                    dir = (dir - 1 + 4) % 4;
                else                            // 우회전
                    dir = (dir + 1) % 4;

                snackDir.poll();                // 방향 변환 정보 삭제
            }
        }

        return time;
    }

    // 우하좌상 이동 배열
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    private static boolean checked(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}

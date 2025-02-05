import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Lv3_나무조경 {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 격자 크기 (2 ≤ n ≤ 4)

        map = new int[N][N];                    // 나무의 키 정보 (1 ≤ 나무의 키 ≤ 10)
        visited = new boolean[N][N];            // 방문 배열

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        K = (N == 2) ? 2 : 4;                   // 인접한(상하좌우) 두 나무를 묶는 횟수

        dfs(0, 0);

        System.out.println(answer);
    }

    // 아래, 우측
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    private static void dfs(int cnt, int sum) {
        if (cnt == K) {     // 종료 조건
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    for (int d = 0; d < 2; d++) {   // 하단, 우측 두방향 탐색
                        int nx = i + dx[d], ny = j + dy[d];

                        if (!check(nx, ny) || visited[nx][ny]) continue;

                        visited[i][j] = true;               // 현재 위치 방문
                        visited[nx][ny] = true;             // 인접 위치 방문
                        dfs(cnt + 1, sum + map[i][j] + map[nx][ny]);      // 재귀(횟수 증가, 합산)
                        visited[i][j] = false;              // 현재 위치 방문 초기화
                        visited[nx][ny] = false;            // 인접 위치 방문 초기화
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }
}

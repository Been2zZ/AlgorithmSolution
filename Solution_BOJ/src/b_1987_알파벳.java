import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_1987_알파벳 {
    static int R, C, max = 1;
    static int[][] map;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new int[26];

        String input = "";
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++)
                map[i][j] = input.charAt(j) - 'A';
        }


        dfs(0, 0, 1);

        System.out.println(max);
    }

    private static void dfs(int x, int y, int cnt) {
        int curr = map[x][y];
        visited[curr] = 1;              /** 백 트래킹 중요!! */

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!check(nx, ny))
                continue;

            int next = map[nx][ny];

            if (visited[next] == 0)
                dfs(nx, ny, cnt + 1);
        }

        visited[curr] = 0;               /** 백 트래킹 중요!! */

        max = Math.max(max, cnt);
    }



    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static boolean check(int x, int y) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }
}

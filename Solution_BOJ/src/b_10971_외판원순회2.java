import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS,BFS 추천 문제]
 */
public class b_10971_외판원순회2 {
    static int N, min;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;

        for (int s = 0; s < N; s++) {
            visited[s] = true;
            TSP(s, s, 0, 0);
            visited[s] = false;
        }

        System.out.println(min);
    }

    private static void TSP(int start, int curr, int sum, int cnt) {
        if (cnt == N - 1) {
            if (map[curr][start] != 0)
                min = Math.min(min, sum + map[curr][start]);
        }

        if (sum >= min)         // 백 트래킹 (최적화)
            return;

        for (int next = 0; next < N; next++) {
            if (visited[next])
                continue;

            if (map[curr][next] != 0) {
                visited[next] = true;
                TSP(start, next, sum + map[curr][next], cnt + 1);
                visited[next] = false;
            }
        }
    }
}

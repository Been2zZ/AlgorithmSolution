import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */
public class b_5014_스타트링크 {
    static int F, S, G, U, D, cnt, ans;
    static int[] map;
    static int[] move;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        F = Integer.parseInt(st.nextToken());       // 건물 총 층수
        S = Integer.parseInt(st.nextToken());       // 건호 위치 층수
        G = Integer.parseInt(st.nextToken());       // 스타트링크 층수
        U = Integer.parseInt(st.nextToken());       // 위로 U층 올라감
        D = Integer.parseInt(st.nextToken());       // 아래로 D층 내려감

        map = new int[F + 1];
        move = new int[] {U, -D};
        visited = new boolean[F + 1];

        map[G] = -1;

        bfs();

        if (map[G] == -1)
            System.out.println("use the stairs");
        else
            System.out.println(map[G]);

    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        if (S == G)
            map[G] = 0;
        else
            q.add(S);
        visited[S] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            cnt++;

            if (curr == G)
                return;

            for (int i = 0; i < 2; i++) {
                int next = curr + move[i];

                if (next < 1 || next > F)
                    continue;

                if (!visited[next]) {
                    q.add(next);
                    map[next] = map[curr] + 1;
                    visited[next] = true;
                }
            }
        }
    }
}

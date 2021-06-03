import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */
public class b_1697_숨바꼭질 {
    static int N, K, size;
    static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();       // 수빈 위치
        K = sc.nextInt();       // 동생 위치

        size = 100001;

        visited = new int[size];

        bfs(N);
        System.out.println(visited[K]);

    }

    private static void bfs(int sx) {
        Queue<Integer> q = new LinkedList<>();
        q.add(sx);          // 큐에 추가

        visited[sx] = 0;

        while (!q.isEmpty()) {
            int cx = q.poll();

            if (cx == K)
                return;

            int nx;
            for (int i = 0; i < 3; i++) {
                if (i == 0)
                    nx = cx - 1;
                else if (i == 1 && N < K)
                    nx = cx + 1;
                else if (i == 2 && N < K)
                    nx = cx * 2;
                else
                    break;

                if (nx < 0 || nx >= size || visited[nx] > 0)
                    continue;

                q.add(nx);
                visited[nx] = visited[cx] + 1;
            }
        }
    }
}

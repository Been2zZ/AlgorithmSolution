import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1922_네트워크연결 {
    static int N, M;
    static ArrayList<Point>[] list;
    static ArrayList<Point> edgeList;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        edgeList = new ArrayList<>();
        visited = new boolean[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[v].add(new Point(v, u, w));
            list[u].add(new Point(u, v, w));

            edgeList.add(new Point(v, u, w));
        }

        Collections.sort(edgeList);         // edge list 오름차순 정렬
//        Arrays.fill(parent, -1);        // 부모 정보 배열 -1 초기화

        /** Kruskal */
        System.out.println(sol_Kruskal());

        /** prim */
//        System.out.println(sol_Prim(1));

    }

    private static int sol_Kruskal() {
        int ans = 0;

        for (int i = 0; i < M; i++) {
            Point p = edgeList.get(i);
            int src = p.v;
            int dest = p.u;

            if (find(src) != find(dest)) {
                ans += p.w;
                union(src, dest);
            }
        }

        return ans;
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        if (x < y)
            parent[find(y)] = find(x);
        else parent[find(x)] = find(y);
    }

    /** prim */
    private static int sol_Prim(int start) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Queue<Integer> q = new LinkedList<>();

        q.add(start);

        int ans = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            visited[curr] = true;

            for (Point p : list[curr])
                if (!visited[p.u])
                    pq.add(p);

            while (!pq.isEmpty()) {
                Point p = pq.poll();

                if (!visited[p.u]) {
                    q.add(p.u);
                     visited[p.u] = true;
                     ans += p.w;
                     break;
                }
            }
        }
        return ans;
    }

    private static class Point implements Comparable<Point> {
        int v;
        int u;
        int w;

        public Point(int v, int u, int w) {
            this.v = v;
            this.u = u;
            this.w = w;
        }

        @Override
        public int compareTo(Point p) {
            return this.w - p.w;
        }
    }
}

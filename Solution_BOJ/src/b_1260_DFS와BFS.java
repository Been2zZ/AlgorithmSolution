import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [DFS+BFS 필수 문제]
 */

public class b_1260_DFS와BFS {
    static int N, M, V;
    static int[][] adjArray;
    static LinkedList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());       // 정점의 개수
        M = Integer.parseInt(st.nextToken());       // 간선의 개수
        V = Integer.parseInt(st.nextToken());       // 시작 정점 번호

        adjArray = new int[N + 1][N + 1];           // 인접 행렬
        adjList = new LinkedList[N + 1];            // 인접 리스트

        visited = new boolean[N + 1];       // 방문 배열

        // 인접 리스트 초기화
        for (int i = 0; i < N + 1; i++)
            adjList[i] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());

            /** 인접 행렬 */
            adjArray[src][dest] = 1;
            adjArray[dest][src] = 1;

            /** 인접 리스트 */
            adjList[src].add(dest);
            adjList[dest].add(src);
        }

        /** !! DFS 방문 순서를 위해 오름차순 정렬 !! */
        for (int i = 0; i < N + 1; i++)
            Collections.sort(adjList[i]);


        /** DFS - recursion */
//        dfs_recursion(V);
        /** DFS - Stack */
        dfs_stack(V);
        System.out.println();

        /** BFS */
        bfs_queue(V);

    }

    /** 재귀 사용 DFS */
    private static void dfs_recursion(int v) {
        visited[v] = true;          // 정점 방문

        System.out.print(v + " ");  // 방문 정점 출력

        /** 인접 리스트 */
        // 정점의 인접 리스트 순회
        Iterator<Integer> iter = adjList[v].listIterator();
        // 정점의 인접 리스트가 있는 경우 반복
        while (iter.hasNext()) {
            // 인접 리스트 순회
            int w = iter.next();

            // 방문하지 않은 정점인 경우
            if (!visited[w])
                dfs_recursion(w);
        }

//        /** 인접 행렬 */
//        for (int i = 1; i < N + 1; i++) {       // 열 탐색
//            if (adjArray[v][i] == 1 && !visited[i]) {
//                // 인접해있고, 방문하지 않은 정점인 경우
//                dfs_arr(i);
//            }
//        }

    }

    /** Stack 사용 DFS */
    private static void dfs_stack(int v) {
        visited = new boolean[N + 1];           // 방문 배열 초기화

        Stack<Integer> s = new Stack<>();
        s.add(v);                               // stack에 시작 정점 추가
        visited[v] = true;                      // 방문 정점 표시

        System.out.print(v + " ");              // 시작 정점 출력

        while (!s.isEmpty()) {
            int w = s.peek();
            boolean hasNext = false;            // 다음 방문 정점 여부 체크

            for (int i = 1; i < N + 1; i++) {   // 열 탐색
                if (adjArray[w][i] == 1 && !visited[i]) {
                    // 인접해있고, 방문하지 않은 정점인 경우
                    s.push(i);
                    visited[i] = true;
                    hasNext = true;             // 다음 방문 정점 존재

                    System.out.print(i + " ");  // 방문 정점 출력

                    break;              // 반복 종료
                }
            }
            if (!hasNext)       // 다음 방문 정점이 없는 경우
                s.pop();        // stack pop
        }
    }

    /** Queue 사용 BFS */
    private static void bfs_queue(int v) {
        visited = new boolean[N + 1];       // 방문 배열 초기화

        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;

        while (!q.isEmpty()) {
            int w = q.poll();
            System.out.print(w + " ");

            /** 인접 리스트 */
            Iterator<Integer> iter = adjList[w].listIterator();
            while (iter.hasNext()) {
                int i = iter.next();
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }

//            /** 인접 행렬 */
//            for (int i = 1; i < N + 1; i++) {   // 열 탐색
//                if (adjArray[w][i] == 1 && !visited[i]) {
//                    q.add(i);
//                    visited[i] = true;
//                }
//            }
        }
    }
}

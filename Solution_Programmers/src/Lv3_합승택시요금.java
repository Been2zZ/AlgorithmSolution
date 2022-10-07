import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 2021 KAKAO BLIND RECRUITMENT
 */
public class Lv3_합승택시요금 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] fares1 = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        int[][] fares2 = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        int[][] fares3 = {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}};

        System.out.println(solution.solution(6, 4, 6, 2, fares1));
        System.out.println(solution.solution(7, 3, 4, 1, fares2));
        System.out.println(solution.solution(6, 4, 5, 6, fares3));
    }

    static class Solution {

        LinkedList<Node>[] graph;

        int[] dist;         // 경로 배열

        boolean[] visited;  // 방문 배열

        public int solution(int n, int s, int a, int b, int[][] fares) {

            graph = new LinkedList[n + 1];

            for (int i = 1; i <= n; i++)
                graph[i] = new LinkedList<>();

            // 인접 리스트 생성
            for (int[] fare : fares) {
                int src = fare[0];
                int desc = fare[1];
                int cost = fare[2];

                graph[src].add(new Node(desc, cost));
                graph[desc].add(new Node(src, cost));
            }

            /**
             * 각 경로의 최단 거리 합의 최소
             * S -> P (분기점/경유지)
             * P -> A
             * P -> B
             */
            int[] routeStoP = dijkstra(s, n);
            int[] routePtoA = dijkstra(a, n);
            int[] routePtoB = dijkstra(b, n);

            /**
             * min 값이 최소가 되는 경우
             * 모든 정점을 기준으로 거리가 최소가 되는 분기점 (P == i)
             */
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++)
                min = Math.min(min, routeStoP[i] + routePtoA[i] + routePtoB[i]);

            return min;
        }

        /**
         * 다익스트라(Dijkstra) 알고리즘
         *
         * @param s 시작 정점
         * @param n 정점의 개수
         * @return 최단 거리 배열
         */
        private int[] dijkstra(int s, int n) {
            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            visited = new boolean[n + 1];

            // 우선순위 큐
            PriorityQueue<Node> pq = new PriorityQueue<>();

            dist[s] = 0;
            pq.add(new Node(s, 0));

            while (!pq.isEmpty()) {
                Node curr = pq.poll();

                if (visited[curr.to])
                    continue;

                visited[curr.to] = true;    // 현재 탐색 정점 방문 체크

                for (Node next : graph[curr.to]) {
                    if (!visited[next.to] && dist[next.to] > curr.cost + next.cost) {
                        // 인접한 정점을 방문하지 않았고,
                        // (거리 배열에 저장된 인접 정점의 거리값 > 현재 정점 거리 + 인접 정점의 거리) 인 경우
                        dist[next.to] = curr.cost + next.cost;      // 최단 거리값 갱신
                        pq.add(new Node(next.to, dist[next.to]));   // 우선 순위 큐에 인접 정점 추가
                    }
                }
            }

            return dist;        // 최단 거리 결과 배열 반환
        }

        static class Node implements Comparable<Node> {
            int to;
            int cost;

            public Node(int to, int cost) {
                this.to = to;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node e) {
                return this.cost - e.cost;
            }
        }
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lv3_여행경로 {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};

        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.solution(tickets)));
    }
}


class Solution {
    List<String> resultRoute;
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        String[] answer = {};

        // 경로가 여러개인 경우 첫 알파벳 기준으로 오름차순 정렬하기 위함
        resultRoute = new ArrayList<>();

        // 방문한 항공권인지 체크하기 위한 배열 (항공권 개수)
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 0);

        // 오름차순 정렬
        resultRoute = resultRoute.stream().sorted().collect(Collectors.toList());

        // 오름차순 정렬된 결과 리스트 중, 첫번째 값 꺼내와서 ',' split
        answer = resultRoute.get(0).split(",");

        return answer;
    }

    /**
     * DFS 풀이
     *
     * @param curr    현재 출발지
     * @param route   경로 기존 문자열에 도착지 합치며 관리
     * @param tickets 항공권 정보 배열
     * @param cnt     방문한 항공권 개수
     */
    private void dfs(String curr, String route, String[][] tickets, int cnt) {
        // 재귀 종료 조건
        if (cnt == tickets.length) {
            resultRoute.add(route);     // 만들어진 경로 List에 추가
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            // 현재 출발지 위치와 티켓의 출발지가 같으며, 방문하지 않은 항공권인 경우
            if (curr.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;      // 항공권 방문 O
                // 항공권 방문 후, 도착지를 출발지로 설정,
                // 현재 경로 + split string(,) + 다음 도착지
                // 방문 횟수 1증가
                dfs(tickets[i][1], route + "," + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;     // 항공권 방문 X
            }
        }
    }
}



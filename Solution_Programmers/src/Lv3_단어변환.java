public class Lv3_단어변환 {
    public static void main(String[] args) {

        Solution solution = new Solution();

        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution.solution("hit", "cog", words));
    }

    static class Solution {

        int answer = 0;

        boolean[] visited;

        public int solution(String begin, String target, String[] words) {
            /**
             * 1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
             * 2. words에 있는 단어로만 변환할 수 있습니다.
             */

            visited = new boolean[words.length];

            dfs(begin, target, words, 0);

            return answer;
        }

        private void dfs(String curr, String target, String[] words, int cnt) {
            if (curr.equals(target)) {
                answer = cnt;
                return;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i]) continue;

                int diffCnt = 0;
                for (int j = 0; j < curr.length(); j++) {
                    if (curr.charAt(j) != words[i].charAt(j))
                        diffCnt++;
                }

                if (diffCnt == 1) {
                    visited[i] = true;
                    dfs(words[i], target, words, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
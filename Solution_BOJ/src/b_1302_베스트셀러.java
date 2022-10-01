import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1302_베스트셀러 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String input = st.nextToken();

            if (map.containsKey(input))
                map.put(input, map.get(input) + 1);
            else
                map.put(input, 1);
        }

        int max = Integer.MIN_VALUE;
        String maxBook = "";

        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            int value = map.get(key);
            if (value > max) {
                max = value;
                maxBook = key;
            } else if (value == max && key.compareTo(maxBook) < 0) {
                maxBook = key;
            }
        }

        System.out.println(maxBook);

    }
}

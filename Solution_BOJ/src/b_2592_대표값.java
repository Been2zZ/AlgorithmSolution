import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_2592_대표값 {
    static int N = 10;
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        num = new int[N];

        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0, maxIdx = -1, maxCnt = 0;

        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();

            num[i] = n;

            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);

            sum += n;
        }

        Set keySet = map.keySet();
        Iterator<Integer> iter = keySet.iterator();
        while (iter.hasNext()) {
            int key = iter.next();

            if (maxCnt < map.get(key)) {
                maxIdx = key;
                maxCnt = map.get(key);
            }
        }

        System.out.println(sum / N);
        System.out.println(maxIdx);
    }
}

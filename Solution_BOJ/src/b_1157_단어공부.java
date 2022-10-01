import java.util.*;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_1157_단어공부 {
    static String inputString;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        inputString = sc.next();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < inputString.length(); i++) {
            char ch = inputString.charAt(i);

            if (ch > 'Z')
                ch -= (char) 32;

            if (map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else
                map.put(ch, 1);
        }

        char maxKey = ' ';
        int maxCnt = 0, cnt = 0;

        Set set = map.keySet();
        Iterator<Character> iter = set.iterator();
        while (iter.hasNext()) {
            char key = iter.next();
            int value = map.get(key);

            if (maxCnt < value) {
                maxKey = key;
                maxCnt = value;
                cnt = 0;
            } else if (maxCnt == value) {
                cnt++;
            }
        }

        if (cnt > 0)
            System.out.println("?");
        else
            System.out.println(maxKey);
    }
}

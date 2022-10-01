import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * BOJ 문제집 [기업 알고리즘 테스트 유형]
 */
public class b_10828_스택 {
    static int N;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());

        list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String comm = st.nextToken();

            if (comm.equals("push"))
                push(Integer.parseInt(st.nextToken()));
            else if (comm.equals("pop"))
                System.out.println(pop());
            else if (comm.equals("size"))
                System.out.println(size());
            else if (comm.equals("empty"))
                System.out.println(empty());
            else if (comm.equals("top"))
                System.out.println(top());
        }
    }

    private static void push(int n) {
        list.add(n);
    }

    private static int pop() {
        if (list.isEmpty())
            return -1;
        return list.removeLast();
    }

    private static int size() {
        return list.size();
    }

    private static int empty() {
        if (list.isEmpty())
            return 1;
        else
            return 0;
    }

    private static int top() {
        if (list.isEmpty())
            return -1;
        return list.getLast();
    }
}

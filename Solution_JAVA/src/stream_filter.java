import java.util.Arrays;

public class stream_filter {
    public static void main(String[] args) {
        int[] arr = {3, 7, 4, 2, 1, 8, 6, 9, 3};

        int[] newArr = Arrays.stream(arr)
                .filter(num -> num % 2 == 0)
                .sorted()
                .toArray();

        System.out.println("짝수 요소들로 걸러진 새 배열 : " + Arrays.toString(newArr));
    }
}

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTests {

    @Test
    public void streamTest() {

        int len = 10;
        int[] arr = new int[len];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        System.out.println("##################################");

        List<Integer> squared = Arrays.stream(arr)
                .map(x -> x * x)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        System.out.println(squared);
    }
}

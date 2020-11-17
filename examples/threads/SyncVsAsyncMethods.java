import java.util.concurrent.CompletableFuture;

public class SyncVsAsyncMethods {

    public static void main(String[] args) throws Exception {
        // Compute sum using synchronous methods
        int sum1 = sum(1, 10);
        int sum2 = sum(11, 20);
        System.out.println("Sum of 1 to 20 = " + (sum1 + sum2));

        // Compute sum using asynchronous methods
        CompletableFuture<Integer> t1 = sumAsync(1, 10);
        CompletableFuture<Integer> t2 = sumAsync(11, 20);
        sum1 = t1.get(); // waits for t1 to complete
        sum2 = t2.get(); // waits for t2 to complete

        System.out.println("Sum of 1 to 20 = " + (sum1 + sum2));
    }

    static int sum(int start, int end) {
        int sum = start;
        for (int i = start + 1; i <= end; ++i)
            sum += i;
        return sum;
    }

    static CompletableFuture<Integer> sumAsync(int start, int end) {
        return CompletableFuture.supplyAsync( () -> {
            int sum = start;
            for (int i = start + 1; i <= end; ++i)
                sum += i;
            return sum;
        });
    }

}


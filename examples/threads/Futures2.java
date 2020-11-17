import java.util.concurrent.CompletableFuture;

public class ThreadsSafeSum {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Long> future = Sum(1, 100000);
        // Long result = future.get();
        future.whenComplete( (result, e) -> System.out.println("Result is: " + result));
        System.out.println("Press enter to exit...");
        System.in.read();
    }

    static CompletableFuture<Long> Sum(int start, int end) {
        return CompletableFuture.supplyAsync( () -> {
            long sum = 0;
            for (int i = start; i <= end; ++i) {
                sum += i;
            }
            return sum;
        });

    }

}

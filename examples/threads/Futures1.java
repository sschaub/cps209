import java.util.concurrent.CompletableFuture;

public class Futures1 {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Long> future = sumAsync(1, 100000);
        future.thenAccept( result -> System.out.println("Result: " + result));
        System.out.println("Press enter to exit...");
        System.in.read();
    }

    static CompletableFuture<Long> sumAsync(int start, int end) {
        return CompletableFuture.supplyAsync( () -> {
            long sum = 0;
            for (int i = start; i <= end; ++i) {
                sum += i;
            }
            return sum;
        });
    }

}

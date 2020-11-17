import java.util.concurrent.CompletableFuture;

public class ThreadsSafeSum {

    public static void main(String[] args) throws Exception {

        CompletableFuture.supplyAsync( () -> { return Sum(1, 100000); } )
        .thenAccept( result -> System.out.println(result + ":"));
        System.out.println("Press enter to exit...");
        System.in.read();
    }

    static long Sum(int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; ++i) {
            sum += i;
        }
        return sum;

    }

}

import java.io.*;
import java.util.concurrent.CompletableFuture;

public class Futures2 {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Long> future = countLines("Futures2.java");
        // Long result = future.get();
        future.whenComplete( (result, e) -> {
            if (e != null) 
                System.out.println("Problem occurred: " + e);
            else
                System.out.println("Result is: " + result);
        });
        System.out.println("Press enter to exit...");
        System.in.read();
    }

    static CompletableFuture<Long> countLines(String filename) {
        return CompletableFuture.supplyAsync( () -> {
            long lines = 0;
            try (BufferedReader rd = new BufferedReader(new FileReader(filename))) {
                while (rd.readLine() != null)
                    ++lines;
            } catch (IOException ex) {
                // must wrap checked exception in an unchecked exception
                throw new RuntimeException(ex); 
            }

            return lines;
        });

    }

}

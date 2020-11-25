import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Futures3 {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Long> future = countLines("Futures2.java", 
          linesSoFar -> System.out.println("Read " + linesSoFar + " lines..."));
        
        future.whenComplete( (result, e) -> {
            if (e != null) 
                System.out.println("Problem occurred: " + e);
            else
                System.out.println("Result is: " + result);
        });
        System.out.println("Press enter to exit...");
        System.in.read();
    }

    static CompletableFuture<Long> countLines(String filename, Consumer<Long> onProgress) {
        return CompletableFuture.supplyAsync( () -> {
            long lines = 0;
            try (BufferedReader rd = new BufferedReader(new FileReader(filename))) {
                while (rd.readLine() != null) {
                    ++lines;
                    if (lines % 10 == 0)
                        onProgress.accept(lines);
                }
                    
            } catch (IOException ex) {
                // must wrap checked exception in an unchecked exception
                throw new RuntimeException(ex); 
            }

            return lines;
        });

    }

}

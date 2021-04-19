import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Searcher {

    private boolean pleaseStop;

    public CompletableFuture<Integer> doSearch(String filename, String word) {
        return CompletableFuture.supplyAsync( () -> {

            int occurrences = 0;
            try (var rd = new BufferedReader(new FileReader(filename))) {
                String line = rd.readLine();
                int lines = 0;
                while (line != null && !pleaseStop) {
                    int delay = 0;

                    while (delay < 1000) System.out.print(delay++);
                    ++lines;
                    if (lines % 10 == 0) {
                        // ("Processed " + lines + "...");
                    }
                    if (line.contains(word)) {
                        ++occurrences;                    
                    }
                    line = rd.readLine();
                }

                return occurrences;

            } catch (IOException e) {
                //( "Error: " + e.getMessage() );

            }

            return -1;
        });
    }

    
}

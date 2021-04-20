import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Searcher {

    private boolean pleaseStop;

    public CompletableFuture<Integer> doSearch(String filename, String word) {
        return CompletableFuture.supplyAsync( () -> {

            int occurrences = 0;
            try (var rd = new BufferedReader(new FileReader(filename))) {
                String line = rd.readLine();
                while (line != null && !pleaseStop) {
                    int delay = 0;

                    while (delay < 1000) System.out.print(delay++);
                    if (line.contains(word)) {
                        ++occurrences;                    
                    }
                    line = rd.readLine();
                }

                if (pleaseStop) {
                    return -1;
                }

                return occurrences;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public boolean isPleaseStop() {
        return pleaseStop;
    }

    public void setPleaseStop(boolean pleaseStop) {
        this.pleaseStop = pleaseStop;
    }

    
    
}

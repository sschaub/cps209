import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class Searcher2 extends GUIWorker<Integer> {
    private String filename, word;

    public Searcher2(String filename, String word) {
        this.filename = filename;
        this.word = word;
    }

    public Integer run() throws IOException {
        int occurrences = 0;
        try (var rd = new BufferedReader(new FileReader(filename))) {
            String line = rd.readLine();
            int lines = 0;
            while (line != null && !pleaseStop) {
                int delay = 0;

                while (delay < 1000)
                    System.out.print(delay++);
                ++lines;
                if (lines % 10 == 0) {
                    onProgress.accept("Processed " + lines + "...");
                }
                if (line.contains(word)) {
                    ++occurrences;
                }
                line = rd.readLine();
            }

            if (pleaseStop) {
                onCancelled.accept("Stopped as requested.");
                return -1;
            }

            return occurrences;
        }
    }

}

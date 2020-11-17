import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class Searcher1 {
    private String filename, word;

    public Searcher1(String filename, String word) {
        this.filename = filename;
        this.word = word;
    }

    private boolean pleaseStop;

    private Consumer<String> onProgress;
    private Consumer<String> onCancelled;
    private Consumer<Integer> onCompleted;
    private Consumer<Throwable> onError;

    public Searcher1 start() {
        CompletableFuture.supplyAsync(() -> {
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

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })
        .whenComplete( (result, e) -> {
            if (e != null) 
                onError.accept(e);
            else
                onCompleted.accept(result);
        });
        return this;
    }

    public void setPleaseStop(boolean pleaseStop) {
        this.pleaseStop = pleaseStop;
    }

    public Searcher1 onProgress(Consumer<String> callback) {
        this.onProgress = callback;
        return this;
    }

    public Searcher1 onCancelled(Consumer<String> callback) {
        this.onCancelled = callback;
        return this;
    }

	public Searcher1 onError(Consumer<Throwable> callback) {
        this.onError = callback;
		return this;
	}

	public Searcher1 onCompleted(Consumer<Integer> callback) {
        this.onCompleted = callback;
		return this;
	}

}

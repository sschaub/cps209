import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface CallbackAction {
    void callback(String msg);
}

public class Searcher {

    private boolean pleaseStop;

    private CallbackAction onProgress;
    private CallbackAction onFinished;
    private CallbackAction onCancelled;
    private CallbackAction onError;

    public void doSearch(String filename, String word) {
        int occurrences = 0;
        try (var rd = new BufferedReader(new FileReader(filename))) {
            String line = rd.readLine();
            int lines = 0;
            while (line != null && !pleaseStop) {
                int delay = 0;

                while (delay < 1000) System.out.print(delay++);
                ++lines;
                if (lines % 10 == 0) {
                    onProgress.callback("Processed " + lines + "...");
                }
                if (line.contains(word)) {
                    ++occurrences;                    
                }
                line = rd.readLine();
            }

            if (pleaseStop) {
                onCancelled.callback("Stopped as requested.");
                return;
            }

            final int finalOccurrences = occurrences;
            onFinished.callback("Found " + finalOccurrences + " occurrences.");
            
        } catch (IOException e) {
            onError.callback( "Error: " + e.getMessage() );

        }
    }

    public void setPleaseStop(boolean pleaseStop) {
        this.pleaseStop = pleaseStop;
    }

	public void setOnProgress(CallbackAction onProgress) {
        this.onProgress = onProgress;
    }

    public CallbackAction getOnFinished() {
        return onFinished;
    }

    public void setOnFinished(CallbackAction onFinished) {
        this.onFinished = onFinished;
    }

    public void setOnCancelled(CallbackAction onCancelled) {
        this.onCancelled = onCancelled;
    }
    public void setOnError(CallbackAction onError) {
        this.onError = onError;
    }
    
    
}

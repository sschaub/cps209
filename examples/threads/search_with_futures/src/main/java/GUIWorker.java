import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javafx.application.Platform;

public abstract class GUIWorker<T> {
    protected boolean pleaseStop;

    protected Consumer<String> onProgress;
    protected Consumer<String> onCancelled;
    protected Consumer<T> onCompleted;
    protected Consumer<Throwable> onError;
    
    public GUIWorker<T> start() {
        CompletableFuture.supplyAsync(() -> {
            try {
                return run();
            } catch (Throwable e) {
                 throw new RuntimeException(e);
            }
         })
         .exceptionallyAsync( e -> {
             onError.accept(e);
             return null;
         }, Platform::runLater)
         .whenCompleteAsync( (result, e) -> {             
            if (e != null) 
                onError.accept(e);
            else
                onCompleted.accept(result);

         }, Platform::runLater);
         return this;
    }

    abstract T run() throws Throwable;

    public void setPleaseStop(boolean pleaseStop) {
        this.pleaseStop = pleaseStop;
    }

    public GUIWorker<T> onProgress(Consumer<String> callback) {
        this.onProgress = callback;
        return this;
    }

    public GUIWorker<T> onCancelled(Consumer<String> callback) {
        this.onCancelled = callback;
        return this;
    }

	public GUIWorker<T> onError(Consumer<Throwable> callback) {
        this.onError = callback;
		return this;
	}

	public GUIWorker<T> onCompleted(Consumer<T> callback) {
        this.onCompleted = callback;
		return this;
	}

}

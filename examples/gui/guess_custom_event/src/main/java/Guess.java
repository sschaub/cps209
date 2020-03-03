import java.util.*;

// Guess class
public class Guess {
    private int secret;
    private int maxSecret;

    private HintEvent onHint;

    private Guess() {
        
    }

    public void pickSecretNumber() {
        secret = new Random().nextInt(maxSecret) + 1;
    }

    public void check(int guess) {
        String msg;
        if (guess < secret) {
            msg = "Too Low.";
        } else if (guess > secret) {
            msg = "Too High.";
        } else {
            msg = "Correct!";
        }

        onHint.handle(msg);
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int newSecret) {
        if (newSecret > maxSecret)
            throw new IllegalArgumentException();
        secret = newSecret;
    }

    public void setMaxSecret(int maxSecret) {
        this.maxSecret = maxSecret;
    }

    public int getMaxSecret() {
        return maxSecret;
    }

    public void setOnHint(HintEvent onHint) {
        this.onHint = onHint;
    }

    private static Guess instance = new Guess();

    public static Guess getInstance() { 
        return instance;
    }
}

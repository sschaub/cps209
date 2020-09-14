import java.util.*;

// Guess class
public class Guess {
    private int secret;
    private int maxSecret;

    private Guess() {
        
    }

    public void pickSecretNumber() {
        secret = new Random().nextInt(maxSecret) + 1;
    }

    public String check(int guess) {
        if (guess < secret) {
            return "Too Low.";
        } else if (guess > secret) {
            return "Too High.";
        } else {
            return "Correct!";
        }
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

    private static Guess instance = new Guess();

    public static Guess getInstance() { 
        return instance;
    }
}

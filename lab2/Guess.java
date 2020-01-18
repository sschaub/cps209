import java.util.*;

public class Guess {
    private int secret;

    public Guess() {
        var rand = new Random();
        secret = rand.nextInt(10);
    }

    public Guess(int secret) {
        this.secret = secret;
    }

    public String check(int guess) {
        if (guess < secret) {
            return "Too Low.";
        } else if (guess >= secret) {
            return "Too High.";
        } else {
            return "Correct!";
        }
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int newSecret) {
        secret = newSecret;
    }
}

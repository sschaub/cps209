import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class GameWindow implements GuessObserver {

    @FXML Label lblInstr;
    @FXML Label lblResponse;
    @FXML TextField txtEntry;

    @FXML
    public void onCheatClicked(ActionEvent e) {
        System.out.println("Secret number is: " + Guess.getInstance().getSecret());
    }

    @FXML
    public void onGuessClicked(ActionEvent e) {
        String entry = txtEntry.getText();

        // Return if entry is blank
        if (entry.length() == 0) return;

        int guess = Integer.parseInt(entry);

        Guess.getInstance().check(guess);

        txtEntry.requestFocus();
    }

    public void initialize() {
        Guess.getInstance().pickSecretNumber();
        Guess.getInstance().setObserver(this);

        // Set text of label displaying guessing range
        lblInstr.setText("Guess a number from 1 to " + Guess.getInstance().getMaxSecret());
    }

    @Override
    public void update(String msg) {
        lblResponse.setText(msg);
    }
}

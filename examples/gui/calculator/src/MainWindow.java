import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;


public class MainWindow {

    public Label lblDisplay;

    public int accum = 0;
    public boolean newEntry = true;

    public void onButtonClicked(ActionEvent e) {

        Button sender = (Button)e.getSource();
        digitEntered(sender.getText().charAt(0));
    }

    void digitEntered(char digit) {
        if (newEntry) {
            lblDisplay.setText("");
            newEntry = false;
        }
        lblDisplay.setText(lblDisplay.getText() + digit);

    }

    public void onPlusClicked(ActionEvent e) {
        accum += Integer.parseInt(lblDisplay.getText());
        lblDisplay.setText(Integer.toString(accum));
        newEntry = true;
    }

    public void onClearClicked(ActionEvent e) {
        lblDisplay.setText("0");
        accum = 0;
        newEntry = true;
    }

    public void keyPressed(KeyEvent e) {
        String text = e.getText();
        if (text.length() == 1 && text.charAt(0) >= '0' && text.charAt(0) <= '9') {
            digitEntered(text.charAt(0));
        }
    }
}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MainWindow {

    public Label lblDisplay;

    public int accum = 0;
    public boolean newEntry = true;

    public void onButtonClicked(ActionEvent e) {
        if (newEntry) {
            lblDisplay.setText("");
            newEntry = false;
        }

        Button sender = (Button)e.getSource();
        lblDisplay.setText(lblDisplay.getText() + sender.getText());
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

}

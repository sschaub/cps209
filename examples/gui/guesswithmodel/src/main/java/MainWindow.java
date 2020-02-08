import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;


public class MainWindow {

    @FXML Label difDisplay;

    @FXML TextField txtMaxSecret;

    @FXML
    public void onStartClicked(ActionEvent e) throws IOException {

        int maxSecret = Integer.parseInt(txtMaxSecret.getText());
        Guess.getInstance().setMaxSecret(maxSecret);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));

        Stage gameWindow = new Stage();
        gameWindow.setScene(new Scene(loader.load()));

        gameWindow.show();



    }
}

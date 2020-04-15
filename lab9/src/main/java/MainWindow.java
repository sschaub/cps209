import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class MainWindow {

    @FXML Button btnConnect;
    @FXML TextField txtServername;
    @FXML TextField txtInput;

    @FXML TextArea txtMessage;

    final int PORT = 6000;

    @FXML
    void onConnectClicked() {

    }

    @FXML
    public void onInputKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // TODO: Transmit txtInput text to server and read response

        }
    }
}

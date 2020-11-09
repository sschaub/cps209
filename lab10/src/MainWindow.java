import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class MainWindow {

    @FXML Button btnConnect;
    @FXML TextField txtServername;
    @FXML TextField txtHandle;
    @FXML TextField txtInput;

    @FXML TextArea txtMessage;

    final int PORT = 7000;

    private Socket socket;
    private PrintWriter writer;
    private Scanner reader;

    private boolean isConnected = false;

    @FXML
    void onConnectClicked() {
        if (isConnected) {
            try {
                socket.close();
            } catch (IOException e) {

            }
            btnConnect.setText("Connect");
            isConnected = false;
            return;
        }

        // TODO: Call the following method using a Thread
        doConnect(txtServername.getText(), txtHandle.getText());
    }

    void doConnect(String servername, String handle) {
        try {
            txtMessage.appendText("Connecting!...\n");
            var addr = InetAddress.getByName(txtServername.getText());
            socket = new Socket(addr, PORT);

            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);

            reader.nextLine(); // read server greeting
            writer.println(txtHandle.getText()); // transmit our handle to server

            txtMessage.appendText("Connection succeeded!\n");
            isConnected = true;
            btnConnect.setText("Disconnect");
            txtInput.requestFocus();    

            // TODO: Read messages from the server and append them to txtMessage

        } catch (IOException ex) {
            txtMessage.appendText("Connection failed: " + ex.getMessage() + "\n");
        }

    }

    @FXML
    public void onInputKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String input = txtInput.getText();
            txtInput.setText("");
            if (input.trim().length() > 0) {
                writer.println(input);
            }

        }
    }
}

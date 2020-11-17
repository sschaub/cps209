import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;


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

        btnConnect.setDisable(true);
        txtMessage.appendText("Connecting!...\n");

        CompletableFuture.runAsync(() -> {
            try {
                doConnect(txtServername.getText(), txtHandle.getText());
                isConnected = true;

                Platform.runLater( () -> {
                    txtMessage.appendText("Connection succeeded!\n");
                    btnConnect.setDisable(false);
                    btnConnect.setText("Disconnect");
                    txtInput.requestFocus();         
                });

                receiveMessages();
            } catch (Exception ex) {
                txtMessage.appendText("Network error: " + ex.getMessage() + "\n");

            } finally {
                Platform.runLater( () -> {
                    btnConnect.setText("Connect");
                    btnConnect.setDisable(false);
                });
            }
        });
    }

    void doConnect(String servername, String handle) {
        
        try {
            var addr = InetAddress.getByName(txtServername.getText());
            socket = new Socket(addr, PORT);

            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);

            reader.nextLine(); // read server greeting
            writer.println(txtHandle.getText()); // transmit our handle to server
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void receiveMessages() throws IOException {
        while (reader.hasNext()) {
            String line = reader.nextLine();
            txtMessage.appendText(line + "\n");
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

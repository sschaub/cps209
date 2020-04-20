import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MainWindow {
    @FXML Label lbl;

    @FXML TextField txtFilename;
    @FXML TextField txtWord;

    @FXML Button btnSearchWithThreads;

    @FXML
    void onCounterWithoutThreadsClicked() {
        lbl.setText("");
        for (int i = 0; i < 5; ++i) {
            // Following causes a crash:
            lbl.setText(String.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (Exception e) { }
        }
    }

    @FXML
    void onCounterClicked() {
        var thread = new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                // Following causes a crash:
                // lbl.setText(String.valueOf(i));
                final int currentI = i;
                Platform.runLater(() -> lbl.setText(String.valueOf(currentI)));
                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        });
        thread.start();

    }

    // Note: Try testing with data from http://mattmahoney.net/dc/textdata.html

    @FXML
    void onSearchWithoutThreadsClicked() {
        lbl.setText(""); // no visible effect
        String filename = txtFilename.getText();
        String word = txtWord.getText();
        int occurrences = 0;
        try (var rd = new BufferedReader(new FileReader(filename))) {
            String line = rd.readLine();
            while (line != null) {
                if (line.contains(word)) {
                    ++occurrences;
                }
                line = rd.readLine();
            }
            lbl.setText("Found " + occurrences + " occurrences.");
        } catch (IOException e) {
            var alert = new Alert(AlertType.ERROR, "Error: " + e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }

    }

    @FXML
    void onSearchWithThreadsClicked() {
        lbl.setText(""); // no visible effect

        btnSearchWithThreads.setDisable(true);

        var thread = new Thread(() -> doSearchInThread(txtFilename.getText(), txtWord.getText()));
        thread.start();
    }


    private void doSearchInThread(String filename, String word) {
        int occurrences = 0;
        try (var rd = new BufferedReader(new FileReader(filename))) {
            String line = rd.readLine();
            while (line != null) {
                if (line.contains(word)) {
                    ++occurrences;
                    if (occurrences % 100 == 0) {
                        final int finalOccurrences = occurrences;
                        Platform.runLater(() -> {
                            lbl.setText("Found " + finalOccurrences + " occurrences.");
                        });
            
                    }
                }
                line = rd.readLine();
            }
            final int finalOccurrences = occurrences;
            Platform.runLater(() -> {
                btnSearchWithThreads.setDisable(false);
                lbl.setText("Found " + finalOccurrences + " occurrences.");
            });
            
        } catch (IOException e) {
            Platform.runLater( () -> {
                var alert = new Alert(AlertType.ERROR, "Error: " + e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            });
        }
    }

}

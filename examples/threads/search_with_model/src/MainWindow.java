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

    @FXML Button btnSearch;
    @FXML Button btnCancel;

    Searcher searcher;


    @FXML
    void onSearchClicked() {
        btnSearch.setDisable(true);
        btnCancel.setDisable(false);
        lbl.setText(""); // no visible effect
        String filename = txtFilename.getText();
        String word = txtWord.getText();

        searcher = new Searcher();
        searcher.setOnProgress(this::showMessage);
        searcher.setOnCancelled( msg -> {
            Platform.runLater( () -> {
              showMessage(msg);
              btnSearch.setDisable(false);
              btnCancel.setDisable(true);
            });
        });
        searcher.setOnFinished( msg -> {
            Platform.runLater( () -> {
                showMessage(msg);
                btnSearch.setDisable(false);
                btnCancel.setDisable(true);
            });
        });
        searcher.setOnError( (msg) -> {
            Platform.runLater( () -> {
                var alert = new Alert(AlertType.ERROR, msg);
                alert.setHeaderText(null);
                alert.show();
            });
        });

        var thread = new Thread( () -> searcher.doSearch(filename, word) );
        thread.start();
    }

    void showMessage(String msg) {
        Platform.runLater( () -> lbl.setText(msg) );
    }

    public void onCancelClicked()
    {
        searcher.setPleaseStop(true);
    }


}

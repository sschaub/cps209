import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<Integer> future = searcher.doSearch(filename, word);
        future.whenComplete( (Integer result, Throwable error) ->      
            Platform.runLater( () -> {
                if (error != null) {
                    // exception occurred
                    var alert = new Alert(AlertType.ERROR, error.getMessage());
                    alert.setHeaderText(null);
                    alert.show();
                } else {
                    // normal completion or cancellation
                    if (searcher.isPleaseStop()) {
                        // cancelled
                        lbl.setText("Search cancelled.");
                    } else {
                        // normal completion
                        lbl.setText("Found " + result + " occurrences.");
                    }
                }
                btnSearch.setDisable(false);
                btnCancel.setDisable(true);                
            })
        );
    }


    public void onCancelClicked()
    {
        // request stop
        searcher.setPleaseStop(true);
    }


}

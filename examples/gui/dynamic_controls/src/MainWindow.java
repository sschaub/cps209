import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;


public class MainWindow {

    @FXML HBox hbox;

    int numButtons;

    @FXML
    void onClicked() {
        ++numButtons;
        String label = String.valueOf(numButtons);
        var btn = new Button(label);
        // btn.setStyle("-fx-font-size: 50; -fx-padding: 20");
        btn.getStyleClass().add("bigbutton");
        hbox.getChildren().add(btn);
        btn.setOnAction(e -> onClickMeClicked(e));
    }

    @FXML
    void onClickMeClicked(ActionEvent event) {
        Button b = (Button)event.getSource();
        b.setText(":)");
    }
}

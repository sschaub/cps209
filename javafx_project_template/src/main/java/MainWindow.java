import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class MainWindow {

    @FXML
    Label lblGreet;

    @FXML
    TextField txtName;

    @FXML
    void onGreetClicked(ActionEvent event) {
        var alert = new Alert(AlertType.INFORMATION, "Hello, world!");
        alert.setHeaderText(null);
        alert.show();

        String name = txtName.getText();
        lblGreet.setText("Hello, " + name + "!");
    }
}

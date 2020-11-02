import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class MainWindow {

    @FXML
    TextField txtName;

    @FXML
    Label lblMessage;

    @FXML
    Label lblLength;

    @FXML
    Slider slider;

    @FXML
    Label lblSliderValue;

    StringProperty name = new SimpleStringProperty();
    StringProperty message = new SimpleStringProperty();

    @FXML
    void initialize() {
        lblMessage.textProperty().bind(message);
        name.bindBidirectional(txtName.textProperty());
        lblSliderValue.textProperty().bind(
            Bindings.createStringBinding(() -> String.valueOf(slider.getValue()), slider.valueProperty())
        );
        lblLength.textProperty().bind(
             Bindings.createStringBinding(() -> String.valueOf(txtName.getText().length()), txtName.textProperty()));
    }

    @FXML
    void onGreetClicked(ActionEvent event) {
        message.set("Hello, " + name.get());
        name.set("");

    }
}

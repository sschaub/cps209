
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUIApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        var scene = new Scene(FXMLLoader.load(getClass().getResource("MainWindow.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator"); // Title of main window
        primaryStage.show();
    }

}

import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class MainWindow {

    @FXML ImageView imgView;

    @FXML Label lblTime;

    Timeline timeline;


    @FXML
    void initialize() {
        var keyFrame = new KeyFrame(Duration.millis(1000),
            e -> lblTime.setText(new Date().toString()));
        var clockTimeline = new Timeline(keyFrame);
            
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }


    @FXML
    void onBrokenStartClicked(ActionEvent event) {
        for (int i = 0; i < 20; ++i) {
            updateImage();
            try { Thread.sleep(200); } catch (Exception e) { }
        }
    }

    @FXML
    void onStartClicked() {
        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> updateImage()));
        timeline.setCycleCount(50);
        timeline.play();
    }

    @FXML
    void onStopClicked() {
        timeline.stop();
    }

    void updateImage() {
        imgView.setX(imgView.getX() + 4);
    }
}

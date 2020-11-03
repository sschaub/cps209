import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

public class MainWindow {

    @FXML
    Pane pane;

    @FXML
    Slider slider;

    @FXML
    Label lblSize;

    @FXML
    Label lblCoord;

    int numButtons;

    int balloonSize = 50;

    boolean newBalloonCreated = false;

    double deltaX;
    double deltaY;

    private List<Balloon> balloons = new ArrayList<Balloon>();

    final Image IMG_BALLOON = new Image("/balloon.png");

    @FXML
    void initialize() {        
        lblSize.textProperty().addListener((o, oldVal, newVal) -> onTextChanged(newVal));
        slider.valueProperty().addListener((o, oldVal, newVal) -> onSliderChanged(newVal.intValue()));

        var keyFrame = new KeyFrame(Duration.millis(1000),
            e -> updateBalloons());
        var clockTimeline = new Timeline(keyFrame);
            
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }

    @FXML
    void onMouseClicked(MouseEvent value) {

        double x = value.getX();
        double y = value.getY();

        var img = new ImageView(IMG_BALLOON);
        img.setPreserveRatio(true);
        img.setFitWidth(balloonSize);
        img.relocate(x, y);
        var balloon = new Balloon(x, y);
        balloons.add(balloon);

        // TODO: Bind img's fitWidth property to slider's value property

        // TODO: Bind balloon's x and y properties to img's layoutX and layoutY properties

        makeDraggable(img);

        pane.getChildren().add(img);
    }

    @FXML
    void onMouseMoved(MouseEvent event) {
        lblCoord.setText(String.format("(%d, %d)", (int) event.getX(), (int) event.getY()));
    }

    void onTextChanged(String newVal) {
        try {
            int val = Integer.parseInt(newVal);
            if (val >= slider.getMin() && val <= slider.getMax()) {
                slider.setValue(val);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value: " + newVal);
        }
    }

    void onSliderChanged(int newVal) {
        balloonSize = newVal;
        lblSize.setText(String.valueOf(newVal));

        for (Node n : pane.getChildren()) {
            ((ImageView) n).setFitWidth(balloonSize);
        }
    }

    void updateBalloons() {
        if (balloons.size() > 0) {
            var balloon = balloons.get(new Random().nextInt(balloons.size()));
            balloon.move();
        }
    }

    // From https://stackoverflow.com/questions/17312734/how-to-make-a-draggable-node-in-javafx-2-0/46696687,
    // with modifications by S. Schaub
    private void makeDraggable(Node node) {
        final Delta dragDelta = new Delta();

        node.setOnMouseEntered(me -> node.getScene().setCursor(Cursor.HAND) );
        node.setOnMouseExited(me -> node.getScene().setCursor(Cursor.DEFAULT) );
        node.setOnMousePressed(me -> {
            dragDelta.x = me.getX();
            dragDelta.y = me.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });
        node.setOnMouseDragged(me -> {
            node.setLayoutX(node.getLayoutX() + me.getX() - dragDelta.x);
            node.setLayoutY(node.getLayoutY() + me.getY() - dragDelta.y);
        });
        node.setOnMouseReleased(me -> node.getScene().setCursor(Cursor.HAND) );

        // Prevent mouse clicks on img from propagating to the pane and
        // resulting in creation of a new image
        node.setOnMouseClicked(me -> me.consume());
    }

    private class Delta {
        public double x;
        public double y;
    }
}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import model.CellType;
import model.Minefield;

public class MainWindow {

    private Minefield game;

    private final int SIZE = 8;

    @FXML
    HBox hbox;

    @FXML
    Label lblLives;

    @FXML
    Label lblMsg;

    final Image IMG_GRASSFACE = new Image("/images/grassface.png");
    final Image IMG_GRASS = new Image("/images/grass.png");
    final Image IMG_MINE = new Image("/images/mine.png");

    final AudioClip AUDIO_EXPLODE = new AudioClip(getClass().getResource("/media/explosion.mp3").toString()); 

    @FXML
    void initialize() {
        game = new Minefield(SIZE);
        for (int i = 0; i < game.getSize(); ++i) {
            var btn = new Button();
            btn.setOnAction(e -> onCellClicked(e));
            btn.setGraphic(new ImageView((i == 0) ? IMG_GRASSFACE : IMG_GRASS));
            btn.setUserData(i); // remember which position in the minefield this button represents
            hbox.getChildren().add(btn);
        }
    }

    @FXML
    void onCellClicked(ActionEvent event) {
        if (game.isGameOver())
            return;
        var btn = (Button) event.getSource();
        int position = (int)btn.getUserData(); // determine which position in the minefield this button represents
        if (game.isValidMove(position)) {
            Button curPositionButton = positionToButton(game.getCurrentPosition());
            curPositionButton.setGraphic(new ImageView(IMG_GRASS));
            game.move(position);
            curPositionButton = positionToButton(game.getCurrentPosition());
            curPositionButton.setGraphic(new ImageView(game.getCurrentCellType() == CellType.Mine ? IMG_MINE : IMG_GRASSFACE));
            if (game.getCurrentCellType() == CellType.Mine) {
                AUDIO_EXPLODE.play();
            }
            if (game.isGameOver()) {
                if (game.getLives() > 0) {
                    lblMsg.setText("Game Over. You won!");
                } else {
                    lblMsg.setText("Game Over. You lost!");
                }
            } else if (game.getCurrentCellType() == CellType.Mine) {
                lblMsg.setText("Ouch!");
            } else if (game.getCurrentCellType() == CellType.Powerup) {
                lblMsg.setText("Powerup!");
            } else {
                lblMsg.setText("");
            }

            lblLives.setText("" + game.getLives());
        } else {
            lblMsg.setText("You can't move there!");
        }

    }

    private Button positionToButton(int pos) {
        return (Button) hbox.getChildren().get(pos);
    }
}

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.CellType;
import model.MinefieldGame;

public class MainWindow {

    private MinefieldGame game;

    @FXML
    HBox hbox;

    @FXML
    Label lblLives;

    @FXML
    Label lblMsg;

    @FXML
    void initialize() {
        game = new MinefieldGame(10);
    }

    @FXML
    void onCellClicked(ActionEvent event) {
        if (game.isGameOver())
            return;
        var btn = (Button) event.getSource();

        int position = buttonToPosition(btn);
        if (game.isValidMove(position)) {
            Button curPositionButton = positionToButton(game.getCurrentPosition());
            curPositionButton.setText("");
            game.move(position);
            curPositionButton = positionToButton(game.getCurrentPosition());
            curPositionButton.setText(game.getCurrentCellType() == CellType.Mine ? "***" : ":)");
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

    private int buttonToPosition(Button btn) {
        ObservableList<Node> list = hbox.getChildren();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) == btn) {
                return i;
            }
        }
        return -1;
    }

    private Button positionToButton(int pos) {
        return (Button) hbox.getChildren().get(pos);
    }
}

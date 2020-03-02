import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Coordinate;
import model.Player;
import model.TicTacToe;


public class MainWindow {

    // Model variables
    TicTacToe model;

    // GUI controls

    @FXML VBox vbox;

    @FXML Label lblMessage;

    // Images

    private static final  Image imgX = new Image("/x.png");

    private static final  Image imgO = new Image("/o.png");

    // FXML Event Handlers

    @FXML 
    void initialize() {
        model = new TicTacToe();

        var hbox = new HBox();
        hbox.getChildren().add(makeButton(0, 0));
        hbox.getChildren().add(makeButton(0, 1));
        hbox.getChildren().add(makeButton(0, 2));
        vbox.getChildren().add(hbox);

        hbox = new HBox();
        hbox.getChildren().add(makeButton(1, 0));
        hbox.getChildren().add(makeButton(1, 1));
        hbox.getChildren().add(makeButton(1, 2));
        vbox.getChildren().add(hbox);

        hbox = new HBox();
        hbox.getChildren().add(makeButton(2, 0));
        hbox.getChildren().add(makeButton(2, 1));
        hbox.getChildren().add(makeButton(2, 2));
        vbox.getChildren().add(hbox);
    }

    void onButtonClicked(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();

        Coordinate coord = (Coordinate)btnClicked.getUserData();
        model.processMove(coord.getRow(), coord.getCol());
        Player player = model.getCell(coord.getRow(), coord.getCol()).getPlayer();

        // TODO: In Part Three, remove the code below
        if (player != Player.NONE) {
            btnClicked.setGraphic(new ImageView(player == Player.X ? imgX : imgO));
        }
        // TODO: In Part One, remove the code below
        if (model.getWinner() != Player.NONE) {
            lblMessage.setText(model.getWinner().name() + " Wins!");
        }

    }

    // Other methods

    Button makeButton(int row, int col) {
        var btn = new Button();
        btn.getStyleClass().add("cellbutton");
        btn.setOnAction(e -> onButtonClicked(e) );
        btn.setUserData(new Coordinate(row, col));

        return btn;
    }

}

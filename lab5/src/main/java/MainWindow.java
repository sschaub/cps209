import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MainWindow {

    // Model variables

    private char[][] grid;

    private char currentPlayer = 'X';
    private char winner = ' ';

    // GUI controls

    @FXML VBox vbox;

    @FXML Label lblMessage;

    // FXML Event Handlers

    @FXML 
    void initialize() {
        grid = new char[][] {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
        };
    }

    @FXML
    void onButtonClicked(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();

        ObservableList<Node> hboxList = vbox.getChildren();
        for (int row = 0; row < grid.length; ++row) {
            HBox hbox = (HBox) hboxList.get(row);
            ObservableList<Node> btnList = hbox.getChildren();
            for (int col = 0; col < grid[row].length; ++col) {
                if (btnClicked == btnList.get(col)) {
                    processMove(row, col);
                    btnClicked.setText(String.valueOf(grid[row][col]));
                    if (winner != ' ') {
                        lblMessage.setText(winner + " Wins!");
                    }
                }
            }
        }

    }

    // Other methods

    void processMove(int row, int col) {
        if (winner != ' ')
            return;

        if (grid[row][col] != ' ')
            return;

        grid[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        
        if (grid[0][0] == 'X' && grid[0][1] == 'X' && grid[0][2] == 'X') {
            winner = 'X';
        }
    }

}

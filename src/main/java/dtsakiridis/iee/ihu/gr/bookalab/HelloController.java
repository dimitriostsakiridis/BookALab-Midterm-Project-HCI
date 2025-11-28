package dtsakiridis.iee.ihu.gr.bookalab;
//Comment
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.control.ScrollPane;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ScrollPane roomPane; // Make sure this matches the fx:id in Scene Builder



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {

    }
}
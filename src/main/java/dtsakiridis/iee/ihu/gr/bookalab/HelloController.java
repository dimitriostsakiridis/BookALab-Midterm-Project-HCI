package dtsakiridis.iee.ihu.gr.bookalab;
//Comment
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.control.ScrollPane;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tab4;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if(newTab == tab4)
                Tab4Controller.onSelected();
        });
    }
}
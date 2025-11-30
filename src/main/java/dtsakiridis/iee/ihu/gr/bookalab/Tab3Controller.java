package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Tab3Controller implements Initializable {

    // 1. FXML Injected Variables (Must match fx:id in FXML)
    @FXML private AnchorPane Table1;
    @FXML private AnchorPane Table2;
    @FXML private AnchorPane Table3;
    @FXML private AnchorPane Table4;
    @FXML private AnchorPane Table5;
    @FXML private AnchorPane Table6;
    @FXML private AnchorPane Table7;
    @FXML private AnchorPane Table8;
    @FXML private AnchorPane Table9;
    @FXML private AnchorPane Table10;

    // 2. Class Field for iteration (Initialized in initialize)
    private AnchorPane[] Tables;

    // 3. Define color constants ONCE as class fields
    private final String GREEN_STYLE = "-fx-background-color: #99DBA5;";
    private final String GREY_STYLE = "-fx-background-color: #D9D9D9;";

    /**
     * Initializes the controller. This is where @FXML variables are guaranteed to be set.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tables = new AnchorPane[]{
                Table1, Table2, Table3, Table4, Table5,
                Table6, Table7, Table8, Table9, Table10
        };
    }

    /**
     * Handles the click event for any of the table AnchorPanes, implementing a single-selection toggle.
     */
    @FXML
    private void handlePaneClick(MouseEvent event) {

        // Safely cast the source of the click (using Java 16+ pattern matching)
        if (event.getSource() instanceof AnchorPane clickedPane) {

            String currentStyle = clickedPane.getStyle();

            // Step 1: Check if the clicked pane is ALREADY green before clearing all styles
            boolean isCurrentlySelected = currentStyle != null && currentStyle.contains(GREEN_STYLE);

            // Step 2: Deselect All: Iterate through the array and set every table to grey.
            for (AnchorPane table : Tables) {
                if (table != null) {
                    table.setStyle(GREY_STYLE);
                }
            }

            // Step 3: Select/Toggle: If it was NOT selected before the clear, select it now.
            // If it WAS selected, it was just cleared to grey, completing the toggle.
            if (!isCurrentlySelected) {
                clickedPane.setStyle(GREEN_STYLE);
            }
        }
        // If the source wasn't an AnchorPane, the method simply returns.
    }
}
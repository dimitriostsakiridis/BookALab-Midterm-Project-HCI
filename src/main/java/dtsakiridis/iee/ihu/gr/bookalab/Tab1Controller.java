package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;


public class Tab1Controller implements Initializable{
    @FXML AnchorPane A1_Room;
    @FXML AnchorPane A3_Room;
    @FXML AnchorPane C1_Room;
    @FXML AnchorPane C2_Room;
    @FXML AnchorPane D1_Room;

    public String selectedTable;
    private AnchorPane[] Panes;

    private final String GREEN_STYLE = "-fx-background-color: #99DBA5;";
    private final String GREY_STYLE = "-fx-background-color: #D9D9D9;";
    private final String RED_STYLE = "-fx-background-color: #red;";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Panes = new AnchorPane[]{A1_Room,A3_Room,C1_Room,C2_Room,D1_Room};
    }

    public String getSelectedTable(){return selectedTable;}

    public void handlePaneClick(MouseEvent event){
        toggleColor(event);
    }

    public void toggleColor(MouseEvent event){
        if (event.getSource() instanceof AnchorPane clickedPane) {

            String currentStyle = clickedPane.getStyle();

            boolean isCurrentlySelected = currentStyle != null && currentStyle.contains(GREEN_STYLE);

            for (AnchorPane table : Panes) {
                if(table.getStyle().contains(RED_STYLE)) return;
                if (table != null) {
                    String APPLIED_STYLE = table.getStyle().replace(GREEN_STYLE, "");
                    APPLIED_STYLE += GREY_STYLE;
                    table.setStyle(APPLIED_STYLE);
                }
            }

            if (!isCurrentlySelected) {
                Node roomNode = clickedPane.getChildren().get(0);

                // 2. Safely cast the Node to a Label
                if (roomNode instanceof Label roomLabel) {

                    // 3. Get the text value (e.g., "1", "2", "10")
                    String selectedRoom = roomLabel.getText();
                    DBFile.SEL_ROOM = selectedRoom;

                    System.out.println("Selected Table Number: " + selectedTable);

                    String APPLIED_STYLE = clickedPane.getStyle().replace(GREY_STYLE, "");
                    APPLIED_STYLE += GREEN_STYLE;

                    clickedPane.setStyle(APPLIED_STYLE);
                    System.out.println(selectedTable);
                }
            }
        }
    }

}
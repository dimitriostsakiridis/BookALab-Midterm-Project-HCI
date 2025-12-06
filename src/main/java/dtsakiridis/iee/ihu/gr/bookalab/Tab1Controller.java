package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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

    private AnchorPane[] Panes;

    private final String GREEN_STYLE = "-fx-background-color: #99DBA5;";
    private final String GREY_STYLE = "-fx-background-color: #D9D9D9;";
    private final String RED_STYLE = "-fx-background-color: #red;";

    public static void onSelected() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Panes = new AnchorPane[]{A1_Room,A3_Room,C1_Room,C2_Room,D1_Room};
    }


    public void handlePaneClick(MouseEvent event){
        if (event.getSource() instanceof AnchorPane clickedPane) {
            clearSelection();
            toggleColor(clickedPane);
            Tab2Controller.populateSlots();
        }
    }

    public void toggleColor(AnchorPane clickedPane){
            String currentStyle = clickedPane.getStyle();
            boolean isCurrentlySelected = currentStyle != null && currentStyle.contains(GREEN_STYLE);

            for (AnchorPane table : Panes) {
                if(table.getStyle().contains(RED_STYLE)) return;
                String APPLIED_STYLE = table.getStyle().replace(GREEN_STYLE, "");
                APPLIED_STYLE += GREY_STYLE;
                table.setStyle(APPLIED_STYLE);
            }

            if (!isCurrentlySelected) {
                Node roomNode = clickedPane.getChildren().get(0);

                // 2. Safely cast the Node to a Label
                if (roomNode instanceof Label roomLabel) {

                    System.out.println("Selected Table Number: " + roomLabel.getText());
                    DBFile.getInstance().setSelectedRoom(roomLabel.getText());

                    String APPLIED_STYLE = clickedPane.getStyle().replace(GREY_STYLE, "");
                    APPLIED_STYLE += GREEN_STYLE;

                    clickedPane.setStyle(APPLIED_STYLE);
                }
        }
    }

    public void clearSelection(){
        DBFile.getInstance().clearSelection();
        Tab2Controller.clearPopulation();
        Tab3Controller.clearPopulation();
    }

}
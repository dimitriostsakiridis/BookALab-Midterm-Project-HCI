package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class Tab3Controller implements Initializable {

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

    private static AnchorPane[] Tables;
    private static final Random random = new Random();

    private static final String GREEN_STYLE = "-fx-background-color: #99DBA5;";
    private static final String GREY_STYLE = "-fx-background-color: #D9D9D9;";
    private static final String RED_STYLE = "-fx-background-color: #ffcccc;";

    public static void onSelected() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tables = new AnchorPane[]{
                Table1, Table2, Table3, Table4, Table5,
                Table6, Table7, Table8, Table9, Table10
        };
    }

    @FXML
    private void handlePaneClick(MouseEvent event) {
        clearSelection();
        if (event.getSource() instanceof AnchorPane clickedPane)
            if(!clickedPane.getStyle().contains(RED_STYLE)) {
                clickedPane.setStyle(clickedPane.getStyle() + GREEN_STYLE);
                Node labelNode = clickedPane.getChildren().get(0);
                if(labelNode instanceof Label tableLabel){
                    DBFile.getInstance().setSelectedTable(tableLabel.getText());
                }
            }
    }

    public static void populateSlots() {
        String date = DBFile.getInstance().getSelectedDay();
        String time = DBFile.getInstance().getSelectedTimeSlot();
        String room = DBFile.getInstance().getSelRoom();

        String key = room.concat(date).concat(time);
        String[] slotstyles = DBFile.getInstance().tableslots.get(key);
        System.out.println(key);
        boolean found = slotstyles != null;
        if(found)
            savedPopulation(slotstyles);
        else
            randomPopulation();
    }

    public static void randomPopulation(){
        String[] slotarr = new String[Tables.length];
        int i=0;
        for(AnchorPane slot : Tables) {
            String APPLIED_STYLE = slot.getStyle();
            if (random.nextInt(10) < 2) {
                APPLIED_STYLE += RED_STYLE;
                slot.setStyle(APPLIED_STYLE);
            }
            slotarr[i++] = APPLIED_STYLE;
        }
        String date = DBFile.getInstance().getSelectedDay();
        String time = DBFile.getInstance().getSelectedTimeSlot();
        String room = DBFile.getInstance().getSelRoom();

        String key = room.concat(date).concat(time);
        DBFile.getInstance().tableslots.put(key, slotarr);
    }

    public static void savedPopulation(String[] slotstyles){
        int i=0;
        for(AnchorPane slot : Tables) {
            slot.setStyle(slotstyles[i++]);
        }
    }

    public static void clearSelection(){
            for(AnchorPane slot : Tables){
                String CURRENT_STYLE = slot.getStyle();
                if(CURRENT_STYLE.contains(GREEN_STYLE))
                    slot.setStyle(CURRENT_STYLE.replace(GREEN_STYLE,GREY_STYLE));
            }
            DBFile.getInstance().setSelectedTable(DBFile.DEFAULT_ROOM);
    }

    public static void clearPopulation(){
        for(AnchorPane slot : Tables){
            String CURRENT_STYLE = slot.getStyle();
            if(CURRENT_STYLE.contains(RED_STYLE))
                slot.setStyle(CURRENT_STYLE.replace(RED_STYLE,""));

        }
    }
}
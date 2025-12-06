package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.toList;

public class Tab2Controller implements Initializable {
    //Header Slot Panes
    @FXML private AnchorPane mondayPane,tuesdayPane,wednesdayPane,thursdayPane,fridayPane;

    //Time Slot Panes
    @FXML private AnchorPane slotpane_01,slotpane_11,slotpane_21,slotpane_31,slotpane_41;
    @FXML private AnchorPane slotpane_02,slotpane_12,slotpane_22,slotpane_32,slotpane_42;
    @FXML private AnchorPane slotpane_03,slotpane_13,slotpane_23,slotpane_33,slotpane_43;
    @FXML private AnchorPane slotpane_04,slotpane_14,slotpane_24,slotpane_34,slotpane_44;
    @FXML private AnchorPane slotpane_05,slotpane_15,slotpane_25,slotpane_35,slotpane_45;

    private static AnchorPane[][] slotpanes;
    private static AnchorPane[] headerpanes;
    private static final Random random = new Random(); // Initialize Random once
    private static final String RED_STYLE = "-fx-background-color: #ffcccc;";
    private static final String GREEN_STYLE = "-fx-background-color: #99DBA5;";
    private final String GREY_STYLE = "-fx-background-color: #D9D9D9;";

    public static void onSelected() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        headerpanes = new AnchorPane[] {mondayPane,tuesdayPane,wednesdayPane,thursdayPane,fridayPane};

        slotpanes = new AnchorPane[][]{
                {slotpane_01,slotpane_11,slotpane_21,slotpane_31,slotpane_41},
                {slotpane_02,slotpane_12,slotpane_22,slotpane_32,slotpane_42},
                {slotpane_03,slotpane_13,slotpane_23,slotpane_33,slotpane_43},
                {slotpane_04,slotpane_14,slotpane_24,slotpane_34,slotpane_44},
                {slotpane_05,slotpane_15,slotpane_25,slotpane_35,slotpane_45}
        };

        populateDates();
    }

    // Ensure this matches the FXML definition onMouseClicked="#handleClick"
    public void handleClick(MouseEvent event) {
        clearSelection();
        if (event.getSource() instanceof AnchorPane clickedPane) {
            if (!clickedPane.getStyle().contains(RED_STYLE)) {
                clickedPane.setStyle(clickedPane.getStyle() + GREEN_STYLE);
                Tab3Controller.clearPopulation();
                Tab3Controller.populateSlots();
                Node datelabel = clickedPane.getChildren().get(0);
                Node timelabel = clickedPane.getChildren().get(1);
                if (datelabel instanceof Label dlabel && timelabel instanceof Label tlabel) {
                    String SELECTED_DATE = dlabel.getText();
                    String SELECTED_TIMESLOT = tlabel.getText();
                    DBFile.getInstance().setSelectedDay(SELECTED_DATE);
                    DBFile.getInstance().setSelectedTimeSlot(SELECTED_TIMESLOT);
                    DBFile.getInstance().setSelectedTable(DBFile.DEFAULT_TABLE);
                }
            }
            Tab3Controller.populateSlots();
        }
    }

    public static void populateDates() {
        LocalDate today = LocalDate.now();

        // Define the pattern: dd (day), MM (month), yyyy (year)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] workWeek = Arrays.stream(DayOfWeek.values())
                .filter(day -> day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)
                .map(today::with)
                .map(date -> date.format(formatter))
                .toArray(String[]::new);


        for(int i=0;i< headerpanes.length;i++){
            Label datelabel = (Label) headerpanes[i].getChildren().get(1);
            datelabel.setText(workWeek[i]);
        }

        for(int i=0;i<slotpanes.length;i++){
            for(int j=0;j<slotpanes[i].length;j++){
                Label datelabel = (Label) slotpanes[i][j].getChildren().get(0);
                datelabel.setText(workWeek[j]);
            }
        }
    }

    public static void populateSlots(){
        String[][] slotstyles = DBFile.getInstance().timeslots.get(DBFile.getInstance().getSelRoom());
        boolean found = slotstyles != null;
        if(found){
            savedPopulation(slotstyles);
        }else{
            randomPopulation();
        }

    }

    public static void savedPopulation(String[][] slotstyles){
        int i=0,j=0;
        for(AnchorPane[] rowArray : slotpanes){
            j=0;
            for(AnchorPane slot : rowArray){
                slot.setStyle(slotstyles[i][j++]);
            }
            i++;
        }
    }

    public static void randomPopulation(){
        String[][] slotarr = new String[slotpanes.length][slotpanes[0].length];
        int i=0,j=0;
        for(AnchorPane[] rowArray : slotpanes){
            j=0;
            for(AnchorPane slot : rowArray){
                String APPLIED_STYLE = slot.getStyle();
                if (random.nextInt(10) < 5) {
                    APPLIED_STYLE += RED_STYLE;
                    slot.setStyle(APPLIED_STYLE);
                }
                slotarr[i][j++] = APPLIED_STYLE;
            }
            i++;
        }

        for(String[] row : slotarr){
            System.out.println(Arrays.toString(row));
        }

        DBFile.getInstance().timeslots.put(DBFile.getInstance().getSelRoom(), slotarr);
    }

    public static void clearPopulation(){
        for(AnchorPane[] rowArray : slotpanes){
            for(AnchorPane slot : rowArray){
                String CURRENT_STYLE = slot.getStyle();
                if(CURRENT_STYLE.contains(RED_STYLE))
                    slot.setStyle(CURRENT_STYLE.replace(RED_STYLE, ""));
            }
        }
    }

    public static void clearSelection(){
        for(AnchorPane[] rowArray : slotpanes){
            for(AnchorPane slot : rowArray){
                String CURRENT_STYLE = slot.getStyle();
                if(CURRENT_STYLE.contains(GREEN_STYLE))
                    slot.setStyle(CURRENT_STYLE.replace(GREEN_STYLE, ""));
            }
        }
    }
}
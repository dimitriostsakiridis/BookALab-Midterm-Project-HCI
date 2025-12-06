package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Tab4Controller implements Initializable {
    private static Tab4Controller instance;
    @FXML private Label selRoom,selTable,selDate,selTimeslot;
    @FXML private TextField lnameInput,fnameInput,amInput,emailInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
    }

    public static void onSelected(){
        String SELECTED_ROOM = DBFile.getInstance().getSelRoom();
        String SELECTED_TABLE = DBFile.getInstance().getSelectedTable();
        String SELECTED_DATE  = DBFile.getInstance().getSelectedDay();
        String SELECTED_TIMESLOT = DBFile.getInstance().getSelectedTimeSlot();


        instance.selRoom.setText(SELECTED_ROOM);
        instance.selTable.setText(SELECTED_TABLE);
        instance.selDate.setText(SELECTED_DATE);
        instance.selTimeslot.setText(SELECTED_TIMESLOT);
        instance.lnameInput.setText("");
        instance.fnameInput.setText("");
        instance.amInput.setText("");
        instance.emailInput.setText("");
    }

    public void setSelRoom(String value){
        selRoom.setText(value);
    }

    public static void submitCheck(){
        if(instance.selRoom.getText().equals(DBFile.DEFAULT_ROOM)){
            String ALERT_MESSAGE = "Παρακαλώ επιλέξτε μία εργαστηριακή αίθουσα.";
            createAlert(ALERT_MESSAGE);
            return;
        }


        if(instance.selDate.getText().equals(DBFile.DEFAULT_DATE)){
            String ALERT_MESSAGE = "Παρακαλώ επιλέξτε μία ημερομηνία.";
            createAlert(ALERT_MESSAGE);
            return;
        }

        if(instance.selTimeslot.getText().equals(DBFile.DEFAULT_TIMESLOT)){
            String ALERT_MESSAGE = "Παρακαλώ επιλέξτε μία ώρα.";
            createAlert(ALERT_MESSAGE);
            return;
        }

        if(instance.selTable.getText().equals(DBFile.DEFAULT_TABLE)){
            String ALERT_MESSAGE = "Παρακαλώ επιλέξτε έναν εργαστηριακό πάγκο.";
            createAlert(ALERT_MESSAGE);
            return;
        }

        if(instance.lnameInput.getText().isEmpty()){
            String ALERT_MESSAGE = "Παρακαλώ συμπληρώστε το επώνυμό σας.";
            createAlert(ALERT_MESSAGE);
            return;
        }else if(instance.lnameInput.getText().length()<3){
            String ALERT_MESSAGE = "Παρακαλώ πληκτρολογήστε τουλάχιστον 3 χαρακτήρες για το επώνυμό σας.";
            createAlert(ALERT_MESSAGE);
            return;
        }

        if(instance.fnameInput.getText().isEmpty()){
            String ALERT_MESSAGE = "Παρακαλώ συμπληρώστε το όνομά σας";
            createAlert(ALERT_MESSAGE);
            return;
        }else if(instance.fnameInput.getText().length()<3){
            String ALERT_MESSAGE = "Παρακαλώ πληκτρολογήστε τουλάχιστον 3 χαρακτήρες για το όνομά σας.";
            createAlert(ALERT_MESSAGE);
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Επιτυχής Καταχώρηση");
        alert.setHeaderText("Επιτυχής Καταχώρηση");
        alert.setContentText("Η αίτηση καταχωρήθηκε επιτυχώς");
        alert.showAndWait();
        DBFile.getInstance().clearSelection();
        onSelected();

    }

    public static void createAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Σφάλμα");
        alert.setHeaderText("Σφάλμα Αίτησης");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

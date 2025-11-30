package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class Tab4Controller implements Initializable {
    @FXML public Label selRoom,selTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void onSelected(){

    }

    public void setSelRoom(String value){
        selRoom.setText(value);
    }
}

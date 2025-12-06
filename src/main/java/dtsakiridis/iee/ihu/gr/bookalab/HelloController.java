package dtsakiridis.iee.ihu.gr.bookalab;
//Comment
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class HelloController {
    @FXML private TabPane tabPane;
    @FXML private Tab tab1,tab2,tab3,tab4;
    @FXML private Button prevBtn,nextBtn;
    @FXML private Tab[]  tabs;
    private int selTab = 0;
    private Tab currentTab;

    @FXML
    public void initialize() {
        tabs = new Tab[] {tab1,tab2,tab3,tab4};

        prevBtn.setVisible(false);
        prevBtn.setDisable(true);

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            currentTab = newTab;
            if(newTab == tab1){
                Tab1Controller.onSelected();
                prevBtn.setVisible(false);
                prevBtn.setDisable(true);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab2){
                Tab2Controller.onSelected();
                // FIX 2: Make it visible and enabled for tab2
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab3) {
                Tab3Controller.onSelected();
                // FIX 2: Make it visible and enabled for tab3
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab4) {
                Tab4Controller.onSelected();
                // FIX 2: Make it visible and enabled for tab4
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setText("ΟΛΟΚΛΗΡΩΣΗ");
            }
        });

    }

    public void handleBtnClick(MouseEvent event) {

        if(event.getSource() == prevBtn){
            if(selTab>0){
                tabPane.getSelectionModel().select(tabs[--selTab]);
            }
        }else if(event.getSource() == nextBtn){
            if(currentTab==tab4){
                Tab4Controller.submitCheck();
                return;
            }

            if(selTab<tabs.length-1){
                tabPane.getSelectionModel().select(tabs[++selTab]);
            }
        }
    }
}
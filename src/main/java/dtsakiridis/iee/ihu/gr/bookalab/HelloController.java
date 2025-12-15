package dtsakiridis.iee.ihu.gr.bookalab;
//Comment
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HelloController {
    @FXML private TabPane tabPane;
    @FXML private Tab tab0,tab1,tab2,tab3,tab4;
    @FXML private Button prevBtn,nextBtn,startBtn;
    @FXML private Tab[]  tabs;
    @FXML HBox startPane,ctrlPane;
    private int selTab = 0;
    private Tab currentTab;

    @FXML
    public void initialize() {
        tabs = new Tab[] {tab0,tab1,tab2,tab3,tab4};

        prevBtn.setVisible(false);
        prevBtn.setDisable(true);

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            currentTab = newTab;
            if(newTab == tab0) {
                ctrlPane.setVisible(false);
                prevBtn.setVisible(false);
                nextBtn.setVisible(false);
                startPane.setVisible(true);
                startBtn.setVisible(true);
            }
            else if(newTab == tab1){
                startBtn.setVisible(false);
                startPane.setVisible(false);
                Tab1Controller.onSelected();
                ctrlPane.setVisible(true);
                prevBtn.setVisible(false);
                prevBtn.setDisable(true);
                nextBtn.setVisible(true);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab2){
                startBtn.setVisible(false);
                startPane.setVisible(false);
                Tab2Controller.onSelected();
                ctrlPane.setVisible(true);
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setVisible(true);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab3) {
                Tab3Controller.onSelected();
                startBtn.setVisible(false);
                startPane.setVisible(false);
                ctrlPane.setVisible(true);
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setVisible(true);
                nextBtn.setText("ΕΠΟΜΕΝΟ");
            }
            else if(newTab == tab4) {
                startBtn.setVisible(false);
                startPane.setVisible(false);
                Tab4Controller.onSelected();
                ctrlPane.setVisible(true);
                prevBtn.setVisible(true);
                prevBtn.setDisable(false);
                nextBtn.setVisible(true);
                nextBtn.setText("ΟΛΟΚΛΗΡΩΣΗ");
            }
        });

    }

    public void handleBtnClick(MouseEvent event) {

        if(event.getSource() == startBtn) {
            selTab=0;
            tabPane.getSelectionModel().select(tabs[++selTab]);
        }
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
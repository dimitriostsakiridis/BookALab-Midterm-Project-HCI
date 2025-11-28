package dtsakiridis.iee.ihu.gr.bookalab;
//Empty Comment for initial commit
import javafx.scene.layout.AnchorPane;

public class ClassRoom {
    private String name;
    private AnchorPane[] desks;
    private String floor;

    public ClassRoom() {}
    public ClassRoom(String name, String floor, AnchorPane[] desks) {
        this.name = name;
        this.floor = floor;
        this.desks = desks;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public AnchorPane[] getDesks() {return desks;}
    public void setDesks(AnchorPane[] desks) {this.desks = desks;}
    public String getFloor() {return floor;}
    public void setFloor(String floor) {this.floor = floor;}

}

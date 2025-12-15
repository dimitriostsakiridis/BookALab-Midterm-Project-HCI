package dtsakiridis.iee.ihu.gr.bookalab;

import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class DBFile {

    private static final DBFile instance = new DBFile();

    private DBFile() {}

    public static DBFile getInstance() {
        return instance;
    }

    //Hash Map for availability slots
    public static HashMap<String,String[][]> timeslots = new HashMap<String, String[][]>();
    public static HashMap<String,String[]> tableslots = new HashMap<String, String[]>();

    //-- Default Values--
    static final String DEFAULT_ROOM = "ΕΠΙΛΕΞΤΕ ΑΙΘΟΥΣΑ";
    static final String DEFAULT_TABLE = "ΕΠΙΛΕΞΤΕ ΠΑΓΚΟ ΕΡΓΑΣΙΑΣ";
    static final String DEFAULT_DATE = "ΕΠΙΛΕΞΤΕ ΗΜΕΡΟΜΗΝΙΑ";
    static final String DEFAULT_TIMESLOT = "ΕΠΙΛΕΞΤΕ ΩΡΑ";

    // --- State Fields ---
    private String selectedRoom = DEFAULT_ROOM;
    private String selectedDay = DEFAULT_DATE;
    private String selectedTimeSlot = DEFAULT_TIMESLOT;
    private String selectedTable = DEFAULT_TABLE;

    // --- Time Slots Population ---

    // --- Getters ---
    public String getSelRoom() { return selectedRoom; }
    public String getSelectedDay() { return selectedDay; }
    public String getSelectedTimeSlot() { return selectedTimeSlot; }
    public String getSelectedTable() { return selectedTable; }

    // --- Setters ---
    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
        System.out.println("DBFile updated: Room -> " + selectedRoom);
    }
    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
        System.out.println("DBFile updated: Day -> " + selectedDay);
    }
    public void setSelectedTimeSlot(String selectedTimeSlot) {
        this.selectedTimeSlot = selectedTimeSlot;
        System.out.println("DBFile updated: Time Slot -> " + selectedTimeSlot);
    }
    public void setSelectedTable(String selectedTable) {
        this.selectedTable = selectedTable;
        System.out.println("DBFile updated: Table -> " + selectedTable);
    }

    public void clearSelection(){
        this.setSelectedRoom(DEFAULT_ROOM);
        this.setSelectedTable(DEFAULT_TABLE);
        this.setSelectedDay(DEFAULT_DATE);
        this.setSelectedTimeSlot(DEFAULT_TIMESLOT);
    }
}
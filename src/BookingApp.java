import java.util.HashMap;
import java.util.Map;

// Domain Model: Defines what a room is
abstract class Room {
    protected String type;
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(String type, int numberOfBeds, int squareFeet, double pricePerNight) {
        this.type = type;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public String getType() { return type; }
    public abstract void displayRoomDetails();
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single", 1, 250, 1500.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println("Single Room:\nBeds: " + numberOfBeds + "\nSize: " + squareFeet + " sqft\nPrice per night: " + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double", 2, 400, 2500.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println("Double Room:\nBeds: " + numberOfBeds + "\nSize: " + squareFeet + " sqft\nPrice per night: " + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite", 3, 750, 5000.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println("Suite Room:\nBeds: " + numberOfBeds + "\nSize: " + squareFeet + " sqft\nPrice per night: " + pricePerNight);
    }
}

// Inventory: Acts as the Single Source of Truth for availability
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3); // Setting to 3 so it shows in search
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

// Search Service: Handles read-only access and filtering
class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory, Room singleRoom, Room doubleRoom, Room suiteRoom) {
        System.out.println("Room Search\n");
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Validation Logic: Only display if availability > 0
        if (availability.get("Single") > 0) {
            singleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single") + "\n");
        }

        if (availability.get("Double") > 0) {
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double") + "\n");
        }

        if (availability.get("Suite") > 0) {
            suiteRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Suite"));
        }
    }
}

// Main Class: Application Entry Point
public class BookingApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        // Initialize Room Objects
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Execute Search (System state remains unchanged)
        searchService.searchAvailableRooms(inventory, single, doubleRm, suite);
    }
}
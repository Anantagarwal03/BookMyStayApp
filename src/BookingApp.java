import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Domain Model
abstract class Room {
    protected String type;
    public Room(String type) { this.type = type; }
    public String getType() { return type; }
}

class SingleRoom extends Room { public SingleRoom() { super("Single"); } }

// Centralized Inventory with Allocation Logic
class RoomInventory {
    private Map<String, Integer> roomAvailability = new HashMap<>();

    public RoomInventory() {
        // Initializing with limited Single rooms to show a FAILED case
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 1);
        roomAvailability.put("Suite", 1);
    }

    public boolean allocateRoom(String type) {
        int currentCount = roomAvailability.getOrDefault(type, 0);
        if (currentCount > 0) {
            roomAvailability.put(type, currentCount - 1); // Inventory Mutation
            return true;
        }
        return false;
    }

    public int getCount(String type) {
        return roomAvailability.getOrDefault(type, 0);
    }
}

// Guest Intent
class Reservation {
    private String guestName;
    private String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

public class BookingApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // FIFO Order: Abhi -> Subha -> Vanmathi
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Single"));

        System.out.println("Processing Room Allocations\n");

        while (!bookingQueue.isEmpty()) {
            Reservation request = bookingQueue.poll();
            boolean success = inventory.allocateRoom(request.getRoomType());

            if (success) {
                System.out.println("SUCCESS: Room allocated for " + request.getGuestName() +
                        " (" + request.getRoomType() + ")");
            } else {
                System.out.println("FAILED: No " + request.getRoomType() +
                        " rooms left for " + request.getGuestName());
            }
        }

        System.out.println("\nFinal Inventory Status:");
        System.out.println("Single Rooms remaining: " + inventory.getCount("Single"));
        System.out.println("Double Rooms remaining: " + inventory.getCount("Double"));
        System.out.println("Suite Rooms remaining: " + inventory.getCount("Suite"));
    }
}
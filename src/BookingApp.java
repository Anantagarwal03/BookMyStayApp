import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

public class BookingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // 1. Intake Stage: Collect multiple requests
        System.out.print("How many booking requests? ");
        int count = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.print("Enter Guest Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Room Type: ");
            String type = sc.nextLine();
            bookingQueue.addRequest(new Reservation(name, type));
        }

        // 2. Output Stage: Match the exact format from your images
        System.out.println("\nBooking Request Queue");
        while (bookingQueue.hasPendingRequests()) {
            Reservation current = bookingQueue.getNextRequest();
            System.out.println("Processing booking for Guest: " + current.getGuestName() +
                    ", Room Type: " + current.getRoomType());
        }

        sc.close();
    }
}
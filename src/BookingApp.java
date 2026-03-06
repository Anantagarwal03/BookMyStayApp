import java.util.Scanner;

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public abstract void displayRoomDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Double Room:");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

public class BookingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hotel Room Initialization\n");

        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.print("Enter available Single Rooms: ");
        int singleAvailability = sc.nextInt();

        System.out.print("Enter available Double Rooms: ");
        int doubleAvailability = sc.nextInt();

        System.out.print("Enter available Suite Rooms: ");
        int suiteAvailability = sc.nextInt();

        System.out.println("\n--- Current Inventory ---");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailability + "\n");

        doubleRm.displayRoomDetails();
        System.out.println("Available: " + doubleAvailability + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailability);

        sc.close();
    }
}
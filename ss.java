class TrainCompartment {
    int compartmentNumber;
    int availableSeats;
    String type; 
    TrainCompartment next;

    public TrainCompartment(int number, int seats, String type) {
        this.compartmentNumber = number;
        this.availableSeats = seats;
        this.type = type;
        this.next = null;
    }
}

class Train {
    private TrainCompartment head;

    public void addCompartment(int number, int seats, String type) {
        TrainCompartment newCompartment = new TrainCompartment(number, seats, type);
        if (head == null || head.compartmentNumber > number) {
            newCompartment.next = head;
            head = newCompartment;
            return;
        }

        TrainCompartment current = head;
        while (current.next != null && current.next.compartmentNumber < number) {
            current = current.next;
        }

        newCompartment.next = current.next;
        current.next = newCompartment;
    }

    public void removeCompartment(int number) {
        if (head == null) return;

        if (head.compartmentNumber == number) {
            head = head.next;
            return;
        }

        TrainCompartment current = head;
        while (current.next != null && current.next.compartmentNumber != number) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public String getCompartmentDetails(int number) {
        TrainCompartment current = head;
        while (current != null) {
            if (current.compartmentNumber == number) {
                return "Compartment " + number + ": " + current.availableSeats + " seats, " + current.type;
            }
            current = current.next;
        }
        return "Compartment " + number + " not found.";
    }

    public void displayTrain() {
        TrainCompartment current = head;
        while (current != null) {
            System.out.println("Compartment " + current.compartmentNumber + ": " + current.availableSeats + " seats, " + current.type);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        train.addCompartment(102, 50, "AC");
        train.addCompartment(101, 40, "Non-AC");
        train.addCompartment(103, 60, "AC");

        System.out.println("Train Compartments:");
        train.displayTrain();

        System.out.println("\nCompartment Details:");
        System.out.println(train.getCompartmentDetails(102));

        train.removeCompartment(101);
        System.out.println("\nAfter Removing Compartment 101:");
        train.displayTrain();
    }
}

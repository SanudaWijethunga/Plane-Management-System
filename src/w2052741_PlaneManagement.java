import java.util.*;
public class w2052741_PlaneManagement {
    // create variable to store tickets that sold
    static  int ticket_count = 0;
    // creating input object
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // calling the array of seat plan
        String[][] seat_array = create_seat_plan();
        // Array for save ticket objects
        Ticket[] tickets = new Ticket[52];

        boolean exit = false;
        while (!exit) {
            // print user menu and getting options
            System.out.println("""
                    
                    Welcome to the Plane Management application
                ***************************************************
                *                  MENU OPTIONS                   *
                ***************************************************
                            
                    1) Buy a seat
                    2) Cancel a seat
                    3) Find first available seat
                    4) Show seating plan
                    5) Print tickets information and total sales
                    6) Search ticket
                    0) Quit
                    
                ***************************************************
                """);
            System.out.print("Enter here from the menu : ");
            String operation = input.next();
            // handling user options
            switch (operation) {
                case "1":
                    // buy a seat
                    // storing return values
                    String return_row_val_1 = get_user_row_letter();
                    int int_row_val = convert_row_letter(return_row_val_1);
                    int return_seat_val = get_user_seat_number(return_row_val_1);
                    buy_seat(int_row_val, return_seat_val, return_row_val_1, seat_array,tickets);
                    break;

                case "2":
                    // cancel a seat
                    // storing return values
                    String return_row_val_2 = get_user_row_letter();
                    int int_row_val_2 = convert_row_letter(return_row_val_2);
                    int return_seat_val_2 = get_user_seat_number(return_row_val_2);
                    cancel_seat(int_row_val_2, return_seat_val_2, seat_array,tickets,return_row_val_2);
                    break;

                case "3":
                    // Find first available seat
                    find_first_available(seat_array);
                    break;

                case "4":
                    // Show seating plan
                    display_seat_plan(seat_array);
                    break;

                case "5":
                    // Print tickets information and total sales
                    print_ticket_info(tickets);
                    break;

                case "6":
                    // Search ticket
                    String return_row_val_3 = get_user_row_letter();
                    int return_seat_val_3 = get_user_seat_number(return_row_val_3);
                    search_ticket(return_seat_val_3,tickets,return_row_val_3);
                    break;

                case "0":
                    // Quit
                    System.out.println("Thank You for choosing us.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid operation");
            }
        }
    }
    /**
     * Creating the array to hold seat plan
     * @return seat_plan_array
     */
    public static String[][] create_seat_plan() {
        String[][] seat_plan_array = new String[4][];
        seat_plan_array[0] = new String[14];
        seat_plan_array[1] = new String[12];
        seat_plan_array[2] = new String[12];
        seat_plan_array[3] = new String[14];
        return seat_plan_array;
    }

    /**
     * Get and validate row letter from user
     * @return row
     */
    public static String get_user_row_letter() {
        while (true) {
            System.out.print("Enter row letter here    : ");
            String row = input.next();
            row = row.toUpperCase();
            String[] row_array = {"A", "B", "C", "D"};
            boolean correct_row = false;
            for (String item : row_array) {
                if (item.equals(row)) {
                    correct_row = true;
                    break;
                }
            }
            // if row letter is not correct
            if (!correct_row) {
                System.out.println("Enter valid row number");
                continue;
            }
            return row;
        }
    }

    /**
     * Convert row letter to specific number
     * @param row the row letter
     * @return row_val
     */
    public static int convert_row_letter(String row) {
        int row_val = -1;
        switch (row) {
            case "A":
                row_val = 0;
                break;
            case "B":
                row_val = 1;
                break;
            case "C":
                row_val = 2;
                break;
            case "D":
                row_val = 3;
                break;
        }
        return row_val;
    }

    /**
     * Get and validate seat number from user
     * @param row the row letter
     * @return seat_number
     */
    public static int get_user_seat_number(String row) {
        while (true) {
            try {
                System.out.print("Enter seat number here   : ");
                int seat_number = input.nextInt();
                boolean correct_seat = false;
                if (row.equalsIgnoreCase("A") || (row.equalsIgnoreCase("D"))) {
                    if (seat_number >= 1 && seat_number <= 14) {
                        correct_seat = true;
                    }
                } else {
                    if (seat_number >= 1 && seat_number <= 12) {
                        correct_seat = true;
                    }
                }
                // if set number is not correct
                if (!correct_seat) {
                    System.out.println("Enter a valid seat number.");
                    continue;
                }
                return seat_number;
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                input.nextLine();
            }
        }
    }

    /**
     * Booking seat and getting user details
     * @param row the converted row letter
     * @param seat the seat number
     * @param row_val the row letter
     * @param array the array that holds seat plan
     * @param tickets the array that hold ticket information
     */
    public static void buy_seat(int row, int seat, String row_val, String[][] array,Ticket[] tickets) {
        if (array[row][seat - 1] == "X") {
            System.out.println("Already booked that seat");
        } else {
            array[row][seat - 1] = "X";
            // getting personal details
            System.out.println();
            System.out.println("Enter person information");
            System.out.print("Enter name    : ");
            String name = input.next();
            System.out.print("Enter surname : ");
            String surname = input.next();
            System.out.print("Enter email   : ");
            String email = input.next();
            System.out.println("Seat booking successful.");
            System.out.println("Ticket information saved to " + row_val+seat+".txt");

            // selecting ticket price
            double price = 0.0;
            if (seat <= 5) {
                price = 200.0;
            }
            else if (seat <= 9) {
                price = 150.0;
            }
            else if (seat <= 14) {
                price = 180.0;
            }

            // create person object
            Person person = new Person(name, surname, email);
            person.setName(name);
            person.setSurname(surname);
            person.setEmail(email);

            // create ticket object
            Ticket ticket = new Ticket(row_val, seat, price, person);
            System.out.println();
            // displaying ticket information and saving to a text file
            ticket.print_ticket_information();
            tickets[ticket_count++] = ticket;
        }
    }

    /**
     * Canceling the booked seat.
     * @param row the converted row letter
     * @param seat the seat number
     * @param array the array that holds seat plan
     * @param tickets the array that hold ticket information
     */
    public static void cancel_seat(int row, int seat, String[][] array,Ticket[] tickets,String row_letter) {
        if (array[row][seat - 1] == null) {
            System.out.println("You haven't book that seat yet.");
        } else {
            array[row][seat - 1] = null;
            for (int i = 0; i< tickets.length; i++){
                Ticket ticket = tickets[i];
                if (ticket!= null && ticket.getRow().equals(row_letter) && ticket.getSeat() == seat){
                    tickets[i] = null;
                    System.out.println("Seat cancelling successful.");
                    return;
                }
            }
        }
    }
    /**
     * Search for the first available seat.
     * @param array the array that holds seat plan
     */
    public static void find_first_available(String[][] array) {
        String message = "First available seat is  : ";
        for (int y = 0; y < 4; y++) {
            for (int z = 0; z < array[y].length; z++) {
                if (array[y][z] == null) {
                    switch (y) {
                        case 0:
                            System.out.println(message + "Row - A  Seat - " + (z + 1));
                            break;
                        case 1:
                            System.out.println(message + "Row - B  Seat - " + (z + 1));
                            break;
                        case 2:
                            System.out.println(message + "Row - C  Seat - " + (z + 1));
                            break;
                        case 3:
                            System.out.println(message + "Row - D  Seat - " + (z + 1));
                            break;
                    }
                    return;
                }
            }
        }
        System.out.println("No available seats.");
    }

    /**
     * Displaying the seat plan
     * @param x the array that holds seat plan
     */
    public static void display_seat_plan(String[][] x) {
        System.out.println("Seat plan : ");
        System.out.println();
        // loop through the seat_plan_Array
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (x[i][j] == null) {
                    System.out.print("O ");
                } else {
                    System.out.print(x[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    /**
     * display the ticket information from the tickets array for all tickets that sold.
     * @param tickets array that holds ticket information
     */
    public static void print_ticket_info(Ticket[] tickets) {
        double total_sales_price = 0.0;
        for (Ticket ticket : tickets){
            if (ticket != null) {
                ticket.print_ticket_information();
                total_sales_price += ticket.getPrice();
                System.out.println();
            }
        }
        System.out.println("Total tickets sale : " + total_sales_price + "£");
    }
    /**
     * Search for the index of the array and display availability or ticket information.
     * @param seat the seat number
     * @param tickets array that holds ticket information
     * @param row the row letter
     */
    public static void search_ticket(int seat,Ticket[] tickets,String row){
        boolean search_ticket = false;
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getRow().equals(row) && ticket.getSeat() == seat) {
                System.out.println("Seat is booked already.");
                System.out.println();
                ticket.print_ticket_information();
                search_ticket = true;
                break;
            }
        }
        // if not sold the seat
        if (!search_ticket){
            System.out.println("Seat is available.");
        }
    }
}
import java.io.FileWriter;
import java.io.IOException;
// creating the ticket class
public class Ticket {
    // instance variables
    private String row;
    private int seat;
    private double price;
    private  Person person;

    // creating the constructor
    public Ticket(String row, int seat,double price,Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // creating setters and getters
    public String getRow(){
        return row;
    }
    public void setRow(String row){
        this.row = row;
    }

    public int getSeat(){
        return seat;
    }
    public void setSeat(int seat){
        this.seat = seat;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    /**
     * printing the ticket information and saving information to text file
     */
    public void print_ticket_information(){
        System.out.println("Ticket Information : ");
        System.out.println("Row   : " + row);
        System.out.println("Seat  : " + seat);
        System.out.println("Price : " + price+"£");
        System.out.println("Person Information : ");
        System.out.println("Name    : " + person.getName());
        System.out.println("Surname : " + person.getSurname());
        System.out.println("Email   : " + person.getEmail());
        // saving information to text file
        save();
    }
    /**
     * Create and write ticket information to a text file
     */
    public void save(){
        String file_name = row + seat;
        try {
            FileWriter fileWriter = new FileWriter(file_name + ".txt");
            fileWriter.write("Ticket Information : \n");
            fileWriter.write("Row   : " + row + "\n");
            fileWriter.write("Seat  : " + seat + "\n");
            fileWriter.write("Price : " + price+"£" + "\n");
            fileWriter.write("Person Information : " + "\n");
            fileWriter.write("Name    : " + person.getName() + "\n");
            fileWriter.write("Surname : " + person.getSurname() + "\n");
            fileWriter.write("Email   : " + person.getEmail());
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("Error! while saving ticket information");
        }
    }
}

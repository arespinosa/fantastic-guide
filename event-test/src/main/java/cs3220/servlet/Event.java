package cs3220.servlet;
import java.time.LocalDate;


public class Event {
    private String name;
    private LocalDate date;
    private String creator;

    // Constructor
    public Event(String name, LocalDate date, String creator) {
        this.name = name;
        this.date = date;
        this.creator = creator;
    }

    // Getters
    public String getEName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCreator() {
        return creator;
    }
}

package day22.workshop.models;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class Rsvp {

    private String id;
    private String name;
    private String email;
    private int phone;
    private Date confirmationDate;
    private Boolean response;
    private String comments;

    public Rsvp() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }

    
}

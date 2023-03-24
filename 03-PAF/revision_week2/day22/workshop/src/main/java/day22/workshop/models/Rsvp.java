package day22.workshop.models;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rsvp {

    private String id;
    private String name;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;

    public Rsvp() {
        this.id = UUID.randomUUID().toString().substring(0,8);
    }
    
}

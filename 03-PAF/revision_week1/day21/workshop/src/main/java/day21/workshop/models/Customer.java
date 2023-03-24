package day21.workshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private int id;
    private String company;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String address;
    private String city;
    private String stateProvince;
    
}

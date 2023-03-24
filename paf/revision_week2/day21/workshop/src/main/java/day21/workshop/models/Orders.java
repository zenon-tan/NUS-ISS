package day21.workshop.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private int id;
    private Date orderDate;
    private String shipAddress;
    private String shipCity;
    private String shipCountryRegion;
    
}

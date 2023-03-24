package day21.workshop.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;
    private int customerId;
    private Date orderDate;
    private Date shippedDate;
    private String shipAddress;
    private String shipCity;
    private String ShipStateProvince;
    
}
